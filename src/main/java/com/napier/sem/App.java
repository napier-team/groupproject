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

    public static void main(String[] args) {
        String dbHost = System.getenv().getOrDefault("DB_HOST", "db");
        String location = dbHost + ":3306";
        int delay = 10000;

        if (args.length > 0) {
            location = args[0];
            delay = 0;
        }

        DatabaseConnection.connect(location, delay);

        try {
            ICountryDAO countryDAO = new CountryDAO(DatabaseConnection.getConnection());
            ICityDAO cityDAO = new CityDAO(DatabaseConnection.getConnection());
            ReportGenerator reportGenerator = new ReportGenerator();

            // --- Existing Reports ---
            System.out.println("\nReport: All countries in the world");
            reportGenerator.displayCountries(countryDAO.getAllCountries());

            // --- NEW: Top N Reports ---
            int n = 5;

            System.out.println("\nReport: Top " + n + " populated countries in the world");
            System.out.println("==================================================");
            List<Country> topCountries = countryDAO.getTopNCountries(n);
            reportGenerator.displayCountries(topCountries);

            System.out.println("\nReport: Top " + n + " populated countries in 'Asia'");
            System.out.println("==================================================");
            List<Country> topAsia = countryDAO.getTopNCountriesByContinent("Asia", n);
            reportGenerator.displayCountries(topAsia);

            System.out.println("\nReport: Top " + n + " populated countries in 'Caribbean'");
            System.out.println("==================================================");
            List<Country> topCaribbean = countryDAO.getTopNCountriesByRegion("Caribbean", n);
            reportGenerator.displayCountries(topCaribbean);

            // ... City reports placeholder ...

        } finally {
            DatabaseConnection.disconnect();
        }
    }
}