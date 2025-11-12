package com.napier.sem;

import com.napier.sem.models.Country;
import com.napier.sem.models.City;
import com.napier.sem.dao.CountryDAO;
import com.napier.sem.dao.CityDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {

    @BeforeAll
    static void init() {
        String dbHost = System.getenv("DB_HOST");
        if (dbHost == null) {
            // If running locally via docker-compose, the port mapped to localhost is likely 3307
            // as per the docker-compose.yml config provided earlier.
            dbHost = "localhost:3307";
        }

        // Connect to the database using the specified host and a delay to ensure DB readiness
        App.connect(dbHost, 30000);
    }

    @Test
    void testGetCountry() {
        // App.con is now public, so we can access it directly
        CountryDAO countryDAO = new CountryDAO(App.con);
        List<Country> countries = countryDAO.getAllCountries();

        assertNotNull(countries, "Country list should not be null");
        assertTrue(countries.size() > 0, "Country list should contain at least one country");

        // Assuming the database is populated correctly, China is typically the most populous
        assertEquals("CHN", countries.get(0).code, "First country should be China (CHN)");
    }

    @Test
    void testGetCity() {
        CityDAO cityDAO = new CityDAO(App.con);
        List<City> cities = cityDAO.getAllCities();

        assertNotNull(cities, "City list should not be null");
        assertTrue(cities.size() > 0, "City list should contain at least one city");
    }
}