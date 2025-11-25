package com.napier.sem;

import com.napier.sem.dao.CityDAO;
import com.napier.sem.dao.CountryDAO;
import com.napier.sem.db.DatabaseConnection;
import com.napier.sem.models.City;
import com.napier.sem.models.Country;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {

    @BeforeAll
    static void init() {
        String dbHost = System.getenv("DB_HOST");
        if (dbHost == null) {
            // If running locally via docker-compose (mapped port)
            dbHost = "localhost:3307";
        }

        // Connect to the database
        DatabaseConnection.connect(dbHost, 30000);
    }

    @AfterAll
    static void tearDown() {
        DatabaseConnection.disconnect();
    }

    @Test
    void testGetCountry() {
        CountryDAO countryDAO = new CountryDAO(DatabaseConnection.getConnection());
        List<Country> countries = countryDAO.getAllCountries();

        assertNotNull(countries, "Country list should not be null");
        assertTrue(countries.size() > 0, "Country list should contain at least one country");
        assertEquals("CHN", countries.get(0).getCode(), "First country should be China (CHN)");
    }

    @Test
    void testGetCity() {
        CityDAO cityDAO = new CityDAO(DatabaseConnection.getConnection());
        List<City> cities = cityDAO.getAllCities();

        assertNotNull(cities, "City list should not be null");
        assertTrue(cities.size() > 0, "City list should contain at least one city");
    }
}