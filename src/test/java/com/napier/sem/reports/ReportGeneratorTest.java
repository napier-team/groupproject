package com.napier.sem.reports;

import com.napier.sem.models.City;
import com.napier.sem.models.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the ReportGenerator class.
 * These tests do not require a database connection.
 */
public class ReportGeneratorTest {

    // The class we are testing
    private ReportGenerator reportGenerator;

    // These are used to capture System.out.println() output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Set up before each test.
     * This redirects System.out to our byte stream to capture output.
     */
    @BeforeEach
    public void setUp() {
        reportGenerator = new ReportGenerator();
        // Redirect System.out to our stream
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Clean up after each test.
     * This restores System.out to its original state.
     */
    @AfterEach
    public void tearDown() {
        // Restore System.out
        System.setOut(originalOut);
    }

    /**
     * Test for displayCountries with a null list.
     */
    @Test
    public void testDisplayCountriesNull() {
        reportGenerator.displayCountries(null);
        // We use trim() to remove any trailing newlines
        assertEquals("No data to display.", outContent.toString().trim());
    }

    /**
     * Test for displayCountries with a list containing one country.
     */
    @Test
    public void testDisplayCountries() {
        // 1. Arrange: Create a sample list
        List<Country> countries = new ArrayList<>();
        Country c = new Country();
        c.code = "GBR";
        c.name = "United Kingdom";
        c.continent = "Europe";
        c.region = "British Islands";
        c.population = 67000000;
        c.capital = 456;
        countries.add(c);

        // 2. Act: Call the method
        reportGenerator.displayCountries(countries);

        // 3. Assert: Check if the output is exactly correct
        // We build the expected header and row
        String header = String.format("%-5s %-45s %-15s %-25s %-12s %-10s",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        String row = String.format("%-5s %-45s %-15s %-25s %-12d %-10d",
                "GBR", "United Kingdom", "Europe", "British Islands", 67000000, 456);

        // We use System.lineSeparator() to handle newlines correctly on any OS
        String expectedOutput = header + System.lineSeparator() + row;

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    /**
     * Test for displayCities with a null list.
     */
    @Test
    public void testDisplayCitiesNull() {
        reportGenerator.displayCities(null);
        assertEquals("No data to display.", outContent.toString().trim());
    }

    /**
     * Test for displayCities with a list containing one city.
     */
    @Test
    public void testDisplayCities() {
        // 1. Arrange: Create a sample list
        List<City> cities = new ArrayList<>();
        City c = new City();
        c.id = "101";
        c.name = "London";
        c.code = "GBR";
        c.district = "England";
        c.population = 8000000;
        cities.add(c);

        // 2. Act
        reportGenerator.displayCities(cities);

        // 3. Assert
        String header = String.format("%-10s %-35s %-15s %-25s %-12s",
                "ID", "Name", "Country Code", "District", "Population");
        String row = String.format("%-10s %-35s %-15s %-25s %-12d",
                "101", "London", "GBR", "England", 8000000);
        String expectedOutput = header + System.lineSeparator() + row;

        assertEquals(expectedOutput, outContent.toString().trim());
    }
}