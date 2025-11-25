package com.napier.sem.dao;

import com.napier.sem.models.CapitalCity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICapitalCityDAO.
 * Uses JOINs to link Cities to Countries specifically checking the Capital ID.
 */
public class CapitalCityDAO implements ICapitalCityDAO {
    private final Connection con;

    public CapitalCityDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<CapitalCity> getAllCapitalCities() {
        String sql = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC";
        return executeQuery(sql, stmt -> {});
    }

    @Override
    public List<CapitalCity> getCapitalCitiesByContinent(String continent) {
        String sql = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, continent));
    }

    @Override
    public List<CapitalCity> getCapitalCitiesByRegion(String region) {
        String sql = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, region));
    }

    @Override
    public List<CapitalCity> getTopNCapitalCitiesWorld(int n) {
        String sql = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> stmt.setInt(1, n));
    }

    @Override
    public List<CapitalCity> getTopNCapitalCitiesByContinent(String continent, int n) {
        String sql = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
        });
    }

    @Override
    public List<CapitalCity> getTopNCapitalCitiesByRegion(String region, int n) {
        String sql = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> {
            stmt.setString(1, region);
            stmt.setInt(2, n);
        });
    }

    // --- Private Helpers ---

    @FunctionalInterface
    private interface StatementPreparer {
        void setParameters(PreparedStatement stmt) throws SQLException;
    }

    private List<CapitalCity> executeQuery(String sql, StatementPreparer preparer) {
        List<CapitalCity> capitals = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            preparer.setParameters(stmt);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    CapitalCity city = new CapitalCity();
                    city.setName(rset.getString("Name"));
                    city.setCountry(rset.getString("Country"));
                    city.setPopulation(rset.getInt("Population"));
                    capitals.add(city);
                }
            }
            return capitals;
        } catch (SQLException e) {
            System.err.println("Failed to execute capital city query: " + e.getMessage());
            return null;
        }
    }
}