package com.napier.sem.dao;

import com.napier.sem.models.City;
import java.util.List;

/**
 * Interface defining data access operations for City entities.
 */
public interface ICityDAO {
    /**
     * Retrieves all cities in the world ordered by population (largest to smallest).
     * @return List of City objects.
     */
    List<City> getAllCities();

    /**
     * Retrieves all cities in a specific continent.
     * @param continent The continent name.
     * @return List of City objects.
     */
    List<City> getCitiesByContinent(String continent);

    /**
     * Retrieves all cities in a specific region.
     * @param region The region name.
     * @return List of City objects.
     */
    List<City> getCitiesByRegion(String region);

    /**
     * Retrieves all cities in a specific country.
     * @param countryCode The country code (e.g., 'GBR').
     * @return List of City objects.
     */
    List<City> getCitiesByCountry(String countryCode);

    /**
     * Retrieves all cities in a specific district.
     * @param district The district name.
     * @return List of City objects.
     */
    List<City> getCitiesByDistrict(String district);
}