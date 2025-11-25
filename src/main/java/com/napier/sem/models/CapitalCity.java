package com.napier.sem.models;

/**
 * Represents a Capital City report entity.
 * Contains specific fields required for capital city reports.
 */
public class CapitalCity {
    /**
     * City Name.
     */
    private String name;

    /**
     * Country Name (not Code).
     */
    private String country;

    /**
     * City Population.
     */
    private int population;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }
}