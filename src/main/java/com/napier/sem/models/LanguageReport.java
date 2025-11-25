package com.napier.sem.models;

/**
 * Represents a language statistic report.
 * Contains data about total speakers and their percentage of the world population.
 */
public class LanguageReport {
    private String languageName;
    private long totalSpeakers;
    private float worldPercentage;

    // Getters and Setters
    public String getLanguageName() { return languageName; }
    public void setLanguageName(String languageName) { this.languageName = languageName; }

    public long getTotalSpeakers() { return totalSpeakers; }
    public void setTotalSpeakers(long totalSpeakers) { this.totalSpeakers = totalSpeakers; }

    public float getWorldPercentage() { return worldPercentage; }
    public void setWorldPercentage(float worldPercentage) { this.worldPercentage = worldPercentage; }
}