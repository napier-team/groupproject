package com.napier.sem;

import com.napier.sem.dao.CityDAO;
import com.napier.sem.dao.CountryDAO;
import com.napier.sem.dao.ICityDAO;
import com.napier.sem.dao.ICountryDAO;
import com.napier.sem.db.DatabaseConnection;
import com.napier.sem.reports.ReportGenerator;

/**
 * The main application class.
 */
public class App {

    public static void main(String[] args) {
        // Environment setup
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

            // --- Country Reports (Brief check) ---
            System.out.println("Generating Country Reports...");
            reportGenerator.displayCountries(countryDAO.getTopNCountries(3));

            // --- City Reports ---
            int n = 5;

            System.out.println("\nReport: Top " + n + " populated cities in the world");
            System.out.println("==================================================");
            reportGenerator.displayCities(cityDAO.getTopNCitiesWorld(n));

            System.out.println("\nReport: Top " + n + " populated cities in 'Asia'");
            System.out.println("==================================================");
            reportGenerator.displayCities(cityDAO.getTopNCitiesByContinent("Asia", n));

            System.out.println("\nReport: Top " + n + " populated cities in 'Caribbean'");
            System.out.println("==================================================");
            reportGenerator.displayCities(cityDAO.getTopNCitiesByRegion("Caribbean", n));

            System.out.println("\nReport: Top " + n + " populated cities in 'United Kingdom' (GBR)");
            System.out.println("==================================================");
            reportGenerator.displayCities(cityDAO.getTopNCitiesByCountry("GBR", n));

            System.out.println("\nReport: Top " + n + " populated cities in 'Scotland'");
            System.out.println("==================================================");
            reportGenerator.displayCities(cityDAO.getTopNCitiesByDistrict("Scotland", n));

        } finally {
            DatabaseConnection.disconnect();
        }
    }
}