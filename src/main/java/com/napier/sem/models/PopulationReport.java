package com.napier.sem.models;

/**
 * Represents a population report containing statistics about urban vs rural population.
 */
public class PopulationReport {
    private String name;
    private long totalPopulation;
    private long urbanPopulation;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getTotalPopulation() { return totalPopulation; }
    public void setTotalPopulation(long totalPopulation) { this.totalPopulation = totalPopulation; }

    public long getUrbanPopulation() { return urbanPopulation; }
    public void setUrbanPopulation(long urbanPopulation) { this.urbanPopulation = urbanPopulation; }

    // Computed properties for reporting
    public long getRuralPopulation() {
        return totalPopulation - urbanPopulation;
    }

    public float getUrbanPercentage() {
        if (totalPopulation == 0) return 0;
        return ((float) urbanPopulation / totalPopulation) * 100;
    }

    public float getRuralPercentage() {
        if (totalPopulation == 0) return 0;
        return ((float) getRuralPopulation() / totalPopulation) * 100;
    }
}