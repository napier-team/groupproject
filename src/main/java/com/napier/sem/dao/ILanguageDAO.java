package com.napier.sem.dao;

import com.napier.sem.models.LanguageReport;
import java.util.List;

/**
 * Interface defining data access operations for Language reports.
 */
public interface ILanguageDAO {
    /**
     * Retrieves a report for specific languages (Chinese, English, Hindi, Spanish, Arabic).
     * Ordered by total number of speakers.
     * @return List of LanguageReport objects.
     */
    List<LanguageReport> getLanguageReport();
}