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
 * Unit tests for ReportGenerator.
 * Uses output capturing to verify console printing.
 */
public class ReportGeneratorTest {

    private ReportGenerator reportGenerator;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        reportGenerator = new ReportGenerator();
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        // Restore original System.out
        System.setOut(originalOut);
    }

    @Test
    public void testDisplayCountriesNull() {
        reportGenerator.displayCountries(null);
        assertEquals("No data to display.", outContent.toString().trim());
    }

    @Test
    public void testDisplayCountries() {
        List<Country> countries = new ArrayList<>();
        Country c = new Country();
        c.code = "UA";
        c.name = "Ukraine";
        c.continent = "Europe";
        c.region = "Eastern Europe";
        c.population = 67000000;
        c.capital = 410;
        countries.add(c);

        reportGenerator.displayCountries(countries);

        String header = String.format("%-5s %-45s %-15s %-25s %-12s %-10s",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        String row = String.format("%-5s %-45s %-15s %-25s %-12d %-10d",
                "UA", "Ukraine", "Europe", "Eastern Europe", 67000000, 410);

        // Use System.lineSeparator() for cross-platform compatibility
        String expectedOutput = header + System.lineSeparator() + row;

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void testDisplayCitiesNull() {
        reportGenerator.displayCities(null);
        assertEquals("No data to display.", outContent.toString().trim());
    }

    @Test
    public void testDisplayCities() {
        List<City> cities = new ArrayList<>();
        City c = new City();
        c.id = "101";
        c.name = "Edinburgh";
        c.code = "EDI";
        c.district = "Scotland";
        c.population = 506520;
        cities.add(c);

        reportGenerator.displayCities(cities);

        String header = String.format("%-10s %-35s %-15s %-25s %-12s",
                "ID", "Name", "Country Code", "District", "Population");
        String row = String.format("%-10s %-35s %-15s %-25s %-12d",
                "101", "Edinburgh", "EDI", "Scotland", 506520);

        String expectedOutput = header + System.lineSeparator() + row;

        assertEquals(expectedOutput, outContent.toString().trim());
    }
}