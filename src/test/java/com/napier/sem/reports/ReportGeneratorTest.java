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
        // println adds a newline, so we expect the string + lineSeparator
        assertEquals("No data to display." + System.lineSeparator(), outContent.toString());
    }

    /**
     * Test for Countries with a list containing one country
     */
    @Test
    public void testDisplayCountries() {
        // Create a sample list
        List<Country> countries = new ArrayList<>();
        Country c = new Country();
        c.setCode("UA");
        c.setName("Ukraine");
        c.setContinent("Europe");
        c.setRegion("Eastern Europe");
        c.setPopulation(67000000);
        c.setCapital(410);
        countries.add(c);

        reportGenerator.displayCountries(countries);

        String header = String.format("%-5s %-45s %-15s %-25s %-12s %-10s",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        String row = String.format("%-5s %-45s %-15s %-25s %-12d %-10d",
                "UA", "Ukraine", "Europe", "Eastern Europe", 67000000, 410);

        // Construct expected output: Header + Newline + Row + Newline
        String expectedOutput = header + System.lineSeparator() + row + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Test for Cities with a null list
     */
    @Test
    public void testDisplayCitiesNull() {
        reportGenerator.displayCities(null);
        assertEquals("No data to display." + System.lineSeparator(), outContent.toString());
    }

    /**
     * Test for Cities with a list containing one city
     */
    @Test
    public void testDisplayCities() {
        // Create a sample list
        List<City> cities = new ArrayList<>();
        City c = new City();
        c.setId(101); // Integer ID
        c.setName("Edinburgh");
        c.setCountryCode("GBR"); // Using ISO code usually, but 'EDI' was in your mock, changed to GBR for realism
        c.setDistrict("Scotland");
        c.setPopulation(506520);
        cities.add(c);

        reportGenerator.displayCities(cities);

        String header = String.format("%-10s %-35s %-15s %-25s %-12s",
                "ID", "Name", "Country Code", "District", "Population");
        String row = String.format("%-10s %-35s %-15s %-25s %-12d",
                "101", "Edinburgh", "GBR", "Scotland", 506520);

        String expectedOutput = header + System.lineSeparator() + row + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}