package com.napier.sem.dao;

import com.napier.sem.models.Country;
import java.util.List;

/**
 * Interface defining data access operations for Country entities.
 */
public interface ICountryDAO {
    /**
     * Retrieves all countries ordered by population (largest to smallest).
     * @return List of Country objects.
     */
    List<Country> getAllCountries();

    /**
     * Retrieves all countries in a specific continent ordered by population.
     * @param continent The name of the continent (e.g., 'Europe', 'Asia').
     * @return List of Country objects.
     */
    List<Country> getCountriesByContinent(String continent);

    /**
     * Retrieves all countries in a specific region ordered by population.
     * @param region The name of the region (e.g., 'Caribbean', 'Western Europe').
     * @return List of Country objects.
     */
    List<Country> getCountriesByRegion(String region);
}