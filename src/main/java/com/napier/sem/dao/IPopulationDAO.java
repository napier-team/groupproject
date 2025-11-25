package com.napier.sem.dao;

import com.napier.sem.models.PopulationReport;
import java.util.List;

/**
 * Interface defining data access operations for Population Reports.
 */
public interface IPopulationDAO {
    List<PopulationReport> getPopulationReportsByContinent();
    List<PopulationReport> getPopulationReportsByRegion();
    List<PopulationReport> getPopulationReportsByCountry();

    long getWorldPopulation();
    long getContinentPopulation(String continent);
    long getRegionPopulation(String region);
    long getCountryPopulation(String countryCode);
    long getDistrictPopulation(String district);
    long getCityPopulation(String cityName);
}
