package com.napier.sem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the connection to the MySQL database.
 * This class handles the driver loading and connection attempts with retries.
 */
public class DatabaseConnection {
    /**
     * The active database connection.
     */
    private static Connection con = null;

    /**
     * Maximum number of connection retries.
     */
    private static final int MAX_RETRIES = 10;

    /**
     * Connects to the MySQL database.
     *
     * @param location The database location (e.g., "localhost:33060" or "db:3306").
     * @param delay    The delay in milliseconds to wait before attempting connection.
     */
    public static void connect(String location, int delay) {
        try {
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
     * Retrieves the current database connection.
     * @return The active SQL Connection object.
     */
    public static Connection getConnection() {
        return con;
    }
}