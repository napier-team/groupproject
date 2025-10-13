package com.napier.sem.dao;

import com.napier.sem.models.Country;
import java.util.List;

/**
 * An interface for country data access operations.
 * It defines the contract that any CountryDAO implementation must follow.
 */
public interface ICountryDAO {
    /**
     * Retrieves a list of all countries from the database,
     * ordered by population from largest to smallest.
     * @return A list of Country objects, or null if an error occurs.
     */
    List<Country> getAllCountries();
}