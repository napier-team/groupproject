package com.napier.sem.dao;

import com.napier.sem.models.CapitalCity;
import java.util.List;

/**
 * Interface defining data access operations for Capital City reports.
 */
public interface ICapitalCityDAO {
    // --- All Capital Cities ---
    List<CapitalCity> getAllCapitalCities();
    List<CapitalCity> getCapitalCitiesByContinent(String continent);
    List<CapitalCity> getCapitalCitiesByRegion(String region);

    // --- Top N Capital Cities ---
    List<CapitalCity> getTopNCapitalCitiesWorld(int n);
    List<CapitalCity> getTopNCapitalCitiesByContinent(String continent, int n);
    List<CapitalCity> getTopNCapitalCitiesByRegion(String region, int n);
}