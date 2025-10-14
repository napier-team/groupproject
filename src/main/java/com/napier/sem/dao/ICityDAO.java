package com.napier.sem.dao;

import com.napier.sem.models.City;
import java.util.List;

/**
 * City data access interface
 */
public interface ICityDAO {
    /**
     * Retrieves a list of cities from the sql file and order by population
     */
    List<City> getAllCities();
}