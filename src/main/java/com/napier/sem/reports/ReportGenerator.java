package com.napier.sem.reports;

import com.napier.sem.models.Country;

import java.util.List;

/**
 * Handles the formatting and display of reports.
 */
public class ReportGenerator {

    /**
     * Displays a list of countries in a formatted table.
     * @param countries The list of countries to display.
     */
    public void displayCountries(List<Country> countries) {
        if (countries == null) {
            System.out.println("No data to display.");
            return;
        }

        // Print table header
        System.out.println(String.format("%-5s %-45s %-15s %-25s %-12s %-10s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Print each country's details
        for (Country country : countries) {
            if (country == null) continue;
            String countryString =
                    String.format("%-5s %-45s %-15s %-25s %-12d %-10d",
                            country.code, country.name, country.continent, country.region, country.population, country.capital);
            System.out.println(countryString);
        }
    }
}