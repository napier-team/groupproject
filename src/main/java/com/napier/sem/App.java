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
 * The main application class.
 * Responsible for managing the database connection and executing reports.
 */
public class App {
    /**
     * The static database connection.
     * Publicly accessible to allow for integration testing.
     */
    public static Connection con = null;

    /**
     * Maximum number of connection retries before terminating.
     */
    private static final int MAX_RETRIES = 10;

    /**
     * Connects to the MySQL database.
     *
     * @param location The database location (e.g., "localhost:33060" or "db:3306").
     * @param delay    The delay in milliseconds to wait before attempting connection (useful for Docker startup).
     */
    public static void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection details - usually provided by environment or defaults
        String dbUser = System.getenv().getOrDefault("DB_USER", "root");
        String dbPassword = System.getenv().getOrDefault("DB_PASSWORD", "example");
        String connectionUrl = String.format("jdbc:mysql://%s/world?useSSL=false&allowPublicKeyRetrieval=true", location);

        for (int i = 0; i < MAX_RETRIES; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait for DB to start
                Thread.sleep(delay);
                // Attempt connection
                con = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
                System.out.println("Successfully connected");
                return;
            } catch (SQLException sqle) {
                System.err.printf("Failed to connect to database on attempt %d of %d%n", i + 1, MAX_RETRIES);
                System.err.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.err.println("Thread interrupted? Should not happen.");
            }
        }

        // Exit if connection fails after retries
        System.err.println("Could not connect to the database after " + MAX_RETRIES + " attempts.");
        System.exit(1);
    }

    /**
     * Disconnects from the MySQL database.
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
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Determine database location from environment or default to container internal name
        String dbHost = System.getenv().getOrDefault("DB_HOST", "db");
        String location = dbHost + ":3306";

        // Connect to database
        connect(location, 10000);

        // Setup DAOs and Report Generator
        ICountryDAO countryDAO = new CountryDAO(con);
        ICityDAO cityDAO = new CityDAO(con);
        ReportGenerator reportGenerator = new ReportGenerator();

        // --- Generate Reports ---

        // 1. All Countries
        List<Country> countries = countryDAO.getAllCountries();
        System.out.println("\nAll countries in the world by population:");
        reportGenerator.displayCountries(countries);

        System.out.println("\n\n--- A Space to Breathe ---\n\n");

        // 2. All Cities
        List<City> cities = cityDAO.getAllCities();
        System.out.println("\nAll cities in the world by population:");
        reportGenerator.displayCities(cities);

        // Cleanup
        disconnect();
    }
}