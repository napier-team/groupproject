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
}