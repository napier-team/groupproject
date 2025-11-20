package com.napier.sem.dao;

import com.napier.sem.models.City;
import java.util.List;

/**
 * Interface defining data access operations for City entities.
 */
public interface ICityDAO {
    /**
     * Retrieves all cities organized by largest population to smallest.
     * @return List of all cities.
     */
    List<City> getAllCities();

    /**
     * Retrieves the top N populated cities in the world.
     * @param n The number of cities to return.
     * @return List of top N cities.
     */
    List<City> getTopNCities(int n);
}