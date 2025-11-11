package com.napier.sem;

import com.napier.sem.dao.CityDAO;
import com.napier.sem.dao.CountryDAO;
import com.napier.sem.dao.ICityDAO;
import com.napier.sem.dao.ICountryDAO;
import com.napier.sem.models.City;
import com.napier.sem.models.Country;
import com.napier.sem.reports.ReportGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * The main application class. It orchestrates the process of connecting to the database,
 * fetching data, and generating reports.
 */
public class App {
    /**
     * The database connection, managed as a static resource for the application's lifetime.
     * Public to allow access for integration testing.
     */
    public static Connection con = null;

    /**
     * The maximum number of times to retry the database connection.
     */
    private static final int MAX_RETRIES = 10;

    /**
     * Establishes a connection to the MySQL database.
     *
     * @param location The database location (host:port).
     * @param delay    The delay in milliseconds before the first connection attempt (useful for waiting for DB startup).
     */
    public static void connect(String location, int delay) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load SQL driver");
            System.exit(-1);
        }

        String dbUser = System.getenv().getOrDefault("DB_USER", "root");
        String dbPassword = System.getenv().getOrDefault("DB_PASSWORD", "example");
        String connectionUrl = String.format("jdbc:mysql://%s/world?useSSL=false&allowPublicKeyRetrieval=true", location);

        for (int i = 0; i < MAX_RETRIES; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Attempt to establish the connection.
                con = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
                System.out.println("Successfully connected");
                return; // Exit the loop on a successful connection.
            } catch (SQLException sqle) {
                System.err.printf("Failed to connect to database on attempt %d of %d%n", i + 1, MAX_RETRIES);
                System.err.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.err.println("Thread interrupted? Should not happen.");
            }
        }

        // If all retries fail, print an error and exit the application.
        System.err.println("Could not connect to the database after " + MAX_RETRIES + " attempts.");
        System.exit(1);
    }

    /**
     * Closes the active database connection.
     */
    public static void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Successfully disconnected");
            } catch (SQLException e) {
                System.err.println("Error closing connection to database: " + e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Read connection details from environment variables, with defaults for local development.
        String dbHost = System.getenv().getOrDefault("DB_HOST", "db");
        // Default port for MySQL inside the container is 3306
        String location = dbHost + ":3306";

        // Establish the database connection
        connect(location, 10000);

        // Instantiate the necessary components for data access and report generation
        ICountryDAO countryDAO = new CountryDAO(con);
        ICityDAO cityDAO = new CityDAO(con); // Added for cities
        ReportGenerator reportGenerator = new ReportGenerator();

        // --- Country Report ---
        // Retrieve the data for the report
        List<Country> countries = countryDAO.getAllCountries();

        // Generate and display the report
        System.out.println("\nAll countries in the world by population:");
        reportGenerator.displayCountries(countries);

        // --- Space before listing Cities ---
        System.out.println();
        System.out.println();
        System.out.println("\n---A Space to Breathe---");
        System.out.println();
        System.out.println();

        // --- City Report ---
        // Retrieve the data for the report
        List<City> cities = cityDAO.getAllCities();

        // Generate and display the report
        System.out.println("\nAll cities in the world by population:");
        reportGenerator.displayCities(cities);

        // Disconnect from the database
        disconnect();
    }
}