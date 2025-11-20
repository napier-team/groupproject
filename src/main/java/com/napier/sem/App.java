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
 */
public class App {
    public static Connection con = null;
    private static final int MAX_RETRIES = 10;

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
        System.exit(1);
    }

    public static void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection to database: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String dbHost = System.getenv().getOrDefault("DB_HOST", "db");
        String location = dbHost + ":3306";

        connect(location, 10000);

        ICountryDAO countryDAO = new CountryDAO(con);
        ICityDAO cityDAO = new CityDAO(con);
        ReportGenerator reportGenerator = new ReportGenerator();

        // --- Existing Reports ---
        System.out.println("\nAll countries in the world by population:");
        List<Country> countries = countryDAO.getAllCountries();
        reportGenerator.displayCountries(countries);

        System.out.println("\nAll cities in the world by population:");
        List<City> cities = cityDAO.getAllCities();
        reportGenerator.displayCities(cities);

        System.out.println("\nTop 5 populated cities in the world:");
        List<City> topCities = cityDAO.getTopNCities(5);
        reportGenerator.displayCities(topCities);

        disconnect();
    }
}