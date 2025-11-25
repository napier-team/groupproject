package com.napier.sem.dao;

import com.napier.sem.models.City;
import java.util.List;

/**
 * Interface defining data access operations for City entities.
 */
public interface ICityDAO {
    // --- Existing Methods ---
    List<City> getAllCities();
    List<City> getCitiesByContinent(String continent);
    List<City> getCitiesByRegion(String region);
    List<City> getCitiesByCountry(String countryCode);
    List<City> getCitiesByDistrict(String district);

    // --- NEW: Top N Methods ---

    /**
     * Retrieves the top N populated cities in the world.
     * @param n The number of cities to return.
     * @return List of City objects.
     */
    List<City> getTopNCitiesWorld(int n);

    /**
     * Retrieves the top N populated cities in a specific continent.
     * @param continent The continent name.
     * @param n The number of cities to return.
     * @return List of City objects.
     */
    List<City> getTopNCitiesByContinent(String continent, int n);

    /**
     * Retrieves the top N populated cities in a specific region.
     * @param region The region name.
     * @param n The number of cities to return.
     * @return List of City objects.
     */
    List<City> getTopNCitiesByRegion(String region, int n);

    /**
     * Retrieves the top N populated cities in a specific country.
     * @param countryCode The country code.
     * @param n The number of cities to return.
     * @return List of City objects.
     */
    List<City> getTopNCitiesByCountry(String countryCode, int n);

    /**
     * Retrieves the top N populated cities in a specific district.
     * @param district The district name.
     * @param n The number of cities to return.
     * @return List of City objects.
     */
    List<City> getTopNCitiesByDistrict(String district, int n);
}