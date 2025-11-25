package com.napier.sem.models;

/**
 * Represents a country.
 */
public class Country {
    /**
     * Country's code (e.g., 'GBR').
     */
    private String code;

    /**
     * Country's name.
     */
    private String name;

    /**
     * Continent where the country is located.
     */
    private String continent;

    /**
     * Region where the country is located.
     */
    private String region;

    /**
     * The population of the country.
     */
    private int population;

    /**
     * The capital city's ID.
     */
    private int capital;

    // Getters and Setters

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }

    public int getCapital() { return capital; }
    public void setCapital(int capital) { this.capital = capital; }
}