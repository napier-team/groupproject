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
        // Determine database location from environment or default
        String dbHost = System.getenv().getOrDefault("DB_HOST", "db");
        String location = dbHost + ":3306";
        int delay = 10000;

        // Connect to database
        DatabaseConnection.connect(location, delay);

        try {
            ICountryDAO countryDAO = new CountryDAO(DatabaseConnection.getConnection());
            ICityDAO cityDAO = new CityDAO(DatabaseConnection.getConnection());
            ReportGenerator reportGenerator = new ReportGenerator();

            // --- Country Reports ---

            // 1. All countries in the world
            System.out.println("\nReport: All countries in the world by population");
            System.out.println("==================================================");
            List<Country> countries = countryDAO.getAllCountries();
            reportGenerator.displayCountries(countries);

            // 2. All countries in a continent (e.g., Europe)
            System.out.println("\nReport: All countries in 'Europe' by population");
            System.out.println("==================================================");
            List<Country> countriesByContinent = countryDAO.getCountriesByContinent("Europe");
            reportGenerator.displayCountries(countriesByContinent);

            // 3. All countries in a region (e.g., Caribbean)
            System.out.println("\nReport: All countries in 'Caribbean' by population");
            System.out.println("==================================================");
            List<Country> countriesByRegion = countryDAO.getCountriesByRegion("Caribbean");
            reportGenerator.displayCountries(countriesByRegion);


            // --- City Reports (Placeholder for now) ---
            System.out.println("\nReport: All cities in the world by population (Limit 10 for brevity)");
            System.out.println("==================================================");
            // Just for demonstration until we implement specific city reports
            List<City> cities = cityDAO.getAllCities();
            // In a real scenario, we might want to limit this list in the SQL,
            // but for now we just display what we have.
            reportGenerator.displayCities(cities);

        } finally {
            DatabaseConnection.disconnect();
        }
    }
}