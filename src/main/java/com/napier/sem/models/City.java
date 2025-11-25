package com.napier.sem.models;

/**
 * Represents a city.
 */
public class City {
    /**
     * City's ID.
     */
    private int id;

    /**
     * City's name.
     */
    private String name;

    /**
     * Country code where the city is located.
     */
    private String countryCode;

    /**
     * District where the city is located.
     */
    private String district;

    /**
     * The population of the city.
     */
    private int population;

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }
}