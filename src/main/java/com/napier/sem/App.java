package com.napier.sem;

import com.napier.sem.dao.CountryDAO;
import com.napier.sem.models.Country;
import com.napier.sem.reports.ReportGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * The main application class that orchestrates the report generation.
 */
public class App {
    /**
     * The database connection, managed as a static resource for the application.
     */
    private static Connection con = null;

    /**
     * Establishes a connection to the MySQL database.
     * It will attempt to connect multiple times before failing.
     */
    public static void connect() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for the database to start
                Thread.sleep(10000);
                // Attempt to connect to the database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true", "root", "example");
                System.out.println("Successfully connected");
                return; // Exit the loop on successful connection
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + (i + 1));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Closes the connection to the database.
     */
    public static void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Successfully disconnected");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * The main entry point for the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Establish the database connection
        connect();

        // Create instances of the DAO and the report generator
        CountryDAO countryDAO = new CountryDAO(con);
        ReportGenerator reportGenerator = new ReportGenerator();

        // --- Execute Application Logic ---
        // Retrieve all countries from the database
        List<Country> countries = countryDAO.getAllCountries();

        // Generate and display the report
        System.out.println("\nAll countries in the world by population:");
        reportGenerator.displayCountries(countries);

        // Disconnect from the database
        disconnect();
    }
}