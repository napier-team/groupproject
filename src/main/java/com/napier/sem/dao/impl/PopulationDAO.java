package com.napier.sem.dao.impl;

import com.napier.sem.dao.IPopulationDAO;
import com.napier.sem.models.PopulationReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IPopulationDAO.
 * Uses subqueries to accurately aggregate city populations without inflating country totals.
 */
public class PopulationDAO implements IPopulationDAO {
    private final Connection con;

    public PopulationDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<PopulationReport> getPopulationReportsByContinent() {
        // We select the continent name, the sum of country populations,
        // and a subquery to sum up city populations for that continent.
        String sql = "SELECT " +
                "  country.Continent as Name, " +
                "  SUM(country.Population) as TotalPop, " +
                "  (SELECT SUM(city.Population) " +
                "   FROM city " +
                "   JOIN country c2 ON city.CountryCode = c2.Code " +
                "   WHERE c2.Continent = country.Continent) as CityPop " +
                "FROM country " +
                "GROUP BY country.Continent";
        return executeQuery(sql);
    }

    @Override
    public List<PopulationReport> getPopulationReportsByRegion() {
        String sql = "SELECT " +
                "  country.Region as Name, " +
                "  SUM(country.Population) as TotalPop, " +
                "  (SELECT SUM(city.Population) " +
                "   FROM city " +
                "   JOIN country c2 ON city.CountryCode = c2.Code " +
                "   WHERE c2.Region = country.Region) as CityPop " +
                "FROM country " +
                "GROUP BY country.Region";
        return executeQuery(sql);
    }

    @Override
    public List<PopulationReport> getPopulationReportsByCountry() {
        // Slightly simpler for country: subquery correlates directly by Code
        String sql = "SELECT " +
                "  country.Name as Name, " +
                "  country.Population as TotalPop, " +
                "  (SELECT SUM(city.Population) " +
                "   FROM city " +
                "   WHERE city.CountryCode = country.Code) as CityPop " +
                "FROM country";
        return executeQuery(sql);
    }

    /**
     * Helper to execute query and map results.
     */
    private List<PopulationReport> executeQuery(String sql) {
        List<PopulationReport> reports = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {

            while (rset.next()) {
                PopulationReport report = new PopulationReport();
                report.setName(rset.getString("Name"));
                report.setTotalPopulation(rset.getLong("TotalPop"));
                // Handle potential NULL if no cities exist
                long cityPop = rset.getLong("CityPop");
                if (rset.wasNull()) cityPop = 0;
                report.setUrbanPopulation(cityPop);

                reports.add(report);
            }
            return reports;
        } catch (SQLException e) {
            System.err.println("Failed to execute population report query: " + e.getMessage());
            return null;
        }
    }
}