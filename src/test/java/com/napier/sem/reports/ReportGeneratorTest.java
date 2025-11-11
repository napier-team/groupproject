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


public class ReportGeneratorTest {

    private ReportGenerator reportGenerator;

    // Capture System.out.println() output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        reportGenerator = new ReportGenerator();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Test for Countries with a null list
     */
    @Test
    public void testDisplayCountriesNull() {
        reportGenerator.displayCountries(null);
        assertEquals("No data to display.", outContent.toString().trim());
    }

    /**
     * Test for Countries with a list containing one country
     */
    @Test
    public void testDisplayCountries() {
        // Create a sample list
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

        // Checks if the output is the same (probably don't need this)
        String header = String.format("%-5s %-45s %-15s %-25s %-12s %-10s",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        String row = String.format("%-5s %-45s %-15s %-25s %-12d %-10d",
                "UA", "Ukraine", "Europe", "Eastern Europe", 67000000, 410);

        String expectedOutput = header + System.lineSeparator() + row;

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    /**
     * Test for Cities with a null list
     */
    @Test
    public void testDisplayCitiesNull() {
        reportGenerator.displayCities(null);
        assertEquals("No data to display.", outContent.toString().trim());
    }

    /**
     * Test for Cities with a list containing one city
     */
    @Test
    public void testDisplayCities() {
        // Create a sample list
        List<City> cities = new ArrayList<>();
        City c = new City();
        c.id = "101";
        c.name = "Edinburgh";
        c.code = "EDI";
        c.district = "Scotland";
        c.population = 506520;
        cities.add(c);

        reportGenerator.displayCities(cities);

        // Checks if the output is the same (probably don't need this)
        String header = String.format("%-10s %-35s %-15s %-25s %-12s",
                "ID", "Name", "Country Code", "District", "Population");
        String row = String.format("%-10s %-35s %-15s %-25s %-12d",
                "101", "Edinburgh", "EDI", "Scotland", 506520);
        String expectedOutput = header + System.lineSeparator() + row;

        assertEquals(expectedOutput, outContent.toString().trim());
    }
}