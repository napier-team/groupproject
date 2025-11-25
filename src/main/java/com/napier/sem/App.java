package com.napier.sem;

import com.napier.sem.dao.CityDAO;
import com.napier.sem.dao.CountryDAO;
import com.napier.sem.dao.ICityDAO;
import com.napier.sem.dao.ICountryDAO;
import com.napier.sem.db.DatabaseConnection;
import com.napier.sem.models.City;
import com.napier.sem.models.Country;
import com.napier.sem.reports.ReportGenerator;

import java.util.List;

/**
 * The main application class.
 * Orchestrates the database connection and report generation.
 */
public class App {

    /**
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Determine database location from environment or default to container internal name
        String dbHost = System.getenv().getOrDefault("DB_HOST", "db");
        String location = dbHost + ":3306";
        int delay = 10000;

        // Connect to database
        DatabaseConnection.connect(location, delay);

        try {
            // Setup DAOs and Report Generator using the established connection
            ICountryDAO countryDAO = new CountryDAO(DatabaseConnection.getConnection());
            ICityDAO cityDAO = new CityDAO(DatabaseConnection.getConnection());
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

        } finally {
            // Ensure clean disconnection
            DatabaseConnection.disconnect();
        }
    }
}