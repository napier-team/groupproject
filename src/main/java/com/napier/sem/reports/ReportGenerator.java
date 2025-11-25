package com.napier.sem.reports;

import com.napier.sem.models.City;
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
                            country.getCode(), country.getName(), country.getContinent(),
                            country.getRegion(), country.getPopulation(), country.getCapital());
            System.out.println(countryString);
        }
    }

    /**
     * Displays a list of cities in a formatted table.
     * @param cities The list of cities to display.
     */
    public void displayCities(List<City> cities) {
        if (cities == null) {
            System.out.println("No data to display.");
            return;
        }

        // Print table header
        System.out.println(String.format("%-10s %-35s %-15s %-25s %-12s",
                "ID", "Name", "Country Code", "District", "Population"));

        // Print each city's details
        for (City city : cities) {
            if (city == null) continue;
            String cityString =
                    String.format("%-10s %-35s %-15s %-25s %-12d",
                            city.getId(), city.getName(), city.getCountryCode(),
                            city.getDistrict(), city.getPopulation());
            System.out.println(cityString);
        }
    }
}