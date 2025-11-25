package com.napier.sem;

import com.napier.sem.dao.impl.CapitalCityDAO;
import com.napier.sem.dao.impl.CityDAO;
import com.napier.sem.dao.impl.CountryDAO;
import com.napier.sem.dao.impl.LanguageDAO;
import com.napier.sem.dao.impl.PopulationDAO;
import com.napier.sem.dao.ICapitalCityDAO;
import com.napier.sem.dao.ICityDAO;
import com.napier.sem.dao.ICountryDAO;
import com.napier.sem.dao.ILanguageDAO;
import com.napier.sem.dao.IPopulationDAO;
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
            ICapitalCityDAO capitalCityDAO = new CapitalCityDAO(DatabaseConnection.getConnection());
            IPopulationDAO populationDAO = new PopulationDAO(DatabaseConnection.getConnection());
            ILanguageDAO languageDAO = new LanguageDAO(DatabaseConnection.getConnection());
            ReportGenerator reportGenerator = new ReportGenerator();

            // --- Country Reports (1-6) ---
            System.out.println("=== Country Reports ===");
            System.out.println("\nTop 3 populated countries in the world:");
            reportGenerator.displayCountries(countryDAO.getTopNCountries(3));

            // --- City Reports (7-16) ---
            int n = 5;
            System.out.println("\n=== City Reports ===");
            System.out.println("\nTop " + n + " populated cities in the world:");
            reportGenerator.displayCities(cityDAO.getTopNCitiesWorld(n));

            System.out.println("\nTop " + n + " populated cities in 'Asia':");
            reportGenerator.displayCities(cityDAO.getTopNCitiesByContinent("Asia", n));

            System.out.println("\nTop " + n + " populated cities in 'Caribbean':");
            reportGenerator.displayCities(cityDAO.getTopNCitiesByRegion("Caribbean", n));

            System.out.println("\nTop " + n + " populated cities in 'United Kingdom' (GBR):");
            reportGenerator.displayCities(cityDAO.getTopNCitiesByCountry("GBR", n));

            System.out.println("\nTop " + n + " populated cities in 'Scotland':");
            reportGenerator.displayCities(cityDAO.getTopNCitiesByDistrict("Scotland", n));

            // --- Capital City Reports (17-22) ---
            System.out.println("\n=== Capital City Reports ===");
            System.out.println("\nTop 5 capital cities in the world:");
            reportGenerator.displayCapitalCities(capitalCityDAO.getTopNCapitalCitiesWorld(5));

            System.out.println("\nTop 5 capital cities in 'Europe':");
            reportGenerator.displayCapitalCities(capitalCityDAO.getTopNCapitalCitiesByContinent("Europe", 5));

            // --- Population Reports (23-25) ---
            System.out.println("\n=== Population Reports ===");
            System.out.println("\nPopulation by Continent (in/not in cities):");
            reportGenerator.displayPopulationReports(populationDAO.getPopulationReportsByContinent());

            // --- Population Queries (26-31) ---
            System.out.println("\n=== Population Queries ===");
            System.out.println("World Population: " + String.format("%,d", populationDAO.getWorldPopulation()));
            System.out.println("Asia Population: " + String.format("%,d", populationDAO.getContinentPopulation("Asia")));
            System.out.println("Caribbean Population: " + String.format("%,d", populationDAO.getRegionPopulation("Caribbean")));
            System.out.println("United Kingdom Population: " + String.format("%,d", populationDAO.getCountryPopulation("GBR")));
            System.out.println("Scotland Population: " + String.format("%,d", populationDAO.getDistrictPopulation("Scotland")));
            System.out.println("London Population: " + String.format("%,d", populationDAO.getCityPopulation("London")));

            // --- Language Report (32) ---
            System.out.println("\n=== Language Report ===");
            System.out.println("\nTop 5 spoken languages in the world:");
            reportGenerator.displayLanguageReport(languageDAO.getLanguageReport());

            System.out.println("\n=== ALL 32 REPORTS COMPLETED ===");

        } finally {
            DatabaseConnection.disconnect();
        }
    }
}