package com.napier.sem.reports;

import com.napier.sem.models.CapitalCity;
import com.napier.sem.models.City;
import com.napier.sem.models.Country;
import com.napier.sem.models.LanguageReport;
import com.napier.sem.models.PopulationReport;

import java.util.List;

/**
 * Handles the formatting and display of reports.
 */
public class ReportGenerator {

    /**
     * Displays a list of countries.
     */
    public void displayCountries(List<Country> countries) {
        if (countries == null) {
            System.out.println("No data to display.");
            return;
        }
        System.out.println(String.format("%-5s %-45s %-15s %-25s %-12s %-10s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        for (Country country : countries) {
            if (country == null) continue;
            System.out.println(String.format("%-5s %-45s %-15s %-25s %-12d %-10d",
                    country.getCode(), country.getName(), country.getContinent(),
                    country.getRegion(), country.getPopulation(), country.getCapital()));
        }
    }

    /**
     * Displays a list of cities.
     */
    public void displayCities(List<City> cities) {
        if (cities == null) {
            System.out.println("No data to display.");
            return;
        }
        System.out.println(String.format("%-10s %-35s %-15s %-25s %-12s",
                "ID", "Name", "Country Code", "District", "Population"));

        for (City city : cities) {
            if (city == null) continue;
            System.out.println(String.format("%-10s %-35s %-15s %-25s %-12d",
                    city.getId(), city.getName(), city.getCountryCode(),
                    city.getDistrict(), city.getPopulation()));
        }
    }

    /**
     * Displays a list of capital cities.
     */
    public void displayCapitalCities(List<CapitalCity> cities) {
        if (cities == null) {
            System.out.println("No data to display.");
            return;
        }
        // Header matches the Specification requirements
        System.out.println(String.format("%-35s %-40s %-12s",
                "Name", "Country", "Population"));

        for (CapitalCity city : cities) {
            if (city == null) continue;
            System.out.println(String.format("%-35s %-40s %-12d",
                    city.getName(), city.getCountry(), city.getPopulation()));
        }
    }
    /**
     * Displays population reports showing urban vs rural distribution.
     */
    public void displayPopulationReports(List<PopulationReport> reports) {
        if (reports == null || reports.isEmpty()) {
            System.out.println("No reports found.");
            return;
        }
        System.out.printf("%-30s %15s %20s %20s%n",
                "Name", "Total Pop", "Urban Pop (%)", "Rural Pop (%)");
        System.out.println("=".repeat(90));
        for (PopulationReport r : reports) {
            System.out.printf("%-30s %,15d %,15d (%.1f%%) %,15d (%.1f%%)%n",
                    r.getName(), r.getTotalPopulation(),
                    r.getUrbanPopulation(), r.getUrbanPercentage(),
                    r.getRuralPopulation(), r.getRuralPercentage());
        }
    }

    /**
     * Displays language report showing speakers and world percentage.
     */
    public void displayLanguageReport(List<LanguageReport> reports) {
        if (reports == null || reports.isEmpty()) {
            System.out.println("No language data found.");
            return;
        }
        System.out.printf("%-15s %20s %15s%n", "Language", "Speakers", "World %");
        System.out.println("=".repeat(55));
        for (LanguageReport r : reports) {
            System.out.printf("%-15s %,20d %14.2f%%%n",
                    r.getLanguageName(), r.getTotalSpeakers(), r.getWorldPercentage());
        }
    }
}