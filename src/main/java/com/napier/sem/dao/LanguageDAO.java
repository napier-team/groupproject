package com.napier.sem.dao;

import com.napier.sem.models.LanguageReport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ILanguageDAO.
 * Performs complex aggregation to calculate total speakers based on population percentages.
 */
public class LanguageDAO implements ILanguageDAO {
    private final Connection con;

    public LanguageDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<LanguageReport> getLanguageReport() {
        // Complex query to calculate total speakers and world percentage
        String sql = "SELECT " +
                "    cl.Language, " +
                "    FLOOR(SUM(c.Population * (cl.Percentage / 100))) as TotalSpeakers, " +
                "    (SUM(c.Population * (cl.Percentage / 100)) / (SELECT SUM(Population) FROM country) * 100) as WorldPercentage " +
                "FROM countrylanguage cl " +
                "JOIN country c ON cl.CountryCode = c.Code " +
                "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY cl.Language " +
                "ORDER BY TotalSpeakers DESC";

        List<LanguageReport> reports = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {

            while (rset.next()) {
                LanguageReport report = new LanguageReport();
                report.setLanguageName(rset.getString("Language"));
                report.setTotalSpeakers(rset.getLong("TotalSpeakers"));
                report.setWorldPercentage(rset.getFloat("WorldPercentage"));
                reports.add(report);
            }
            return reports;
        } catch (SQLException e) {
            System.err.println("Failed to execute language report query: " + e.getMessage());
            return null;
        }
    }
}