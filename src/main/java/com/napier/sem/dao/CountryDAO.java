package com.napier.sem.dao;

import com.napier.sem.models.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICountryDAO using JDBC.
 */
public class CountryDAO implements ICountryDAO {
    private final Connection con;

    public CountryDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Country> getAllCountries() {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                "FROM country ORDER BY Population DESC";
        return executeQuery(sql, stmt -> {});
    }

    @Override
    public List<Country> getCountriesByContinent(String continent) {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                "FROM country WHERE Continent = ? ORDER BY Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, continent));
    }

    @Override
    public List<Country> getCountriesByRegion(String region) {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                "FROM country WHERE Region = ? ORDER BY Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, region));
    }

    @Override
    public List<Country> getTopNCountries(int n) {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                "FROM country ORDER BY Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> stmt.setInt(1, n));
    }

    @Override
    public List<Country> getTopNCountriesByContinent(String continent, int n) {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                "FROM country WHERE Continent = ? ORDER BY Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
        });
    }

    @Override
    public List<Country> getTopNCountriesByRegion(String region, int n) {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                "FROM country WHERE Region = ? ORDER BY Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> {
            stmt.setString(1, region);
            stmt.setInt(2, n);
        });
    }

    /**
     * Functional interface for setting SQL parameters safely.
     */
    @FunctionalInterface
    private interface StatementPreparer {
        void setParameters(PreparedStatement stmt) throws SQLException;
    }

    /**
     * Generic method to execute queries and map results.
     */
    private List<Country> executeQuery(String sql, StatementPreparer preparer) {
        List<Country> countries = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            preparer.setParameters(stmt);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    countries.add(extractCountryFromResultSet(rset));
                }
            }
            return countries;
        } catch (SQLException e) {
            System.err.println("Failed to execute country query: " + e.getMessage());
            return null;
        }
    }

    /**
     * Extracts a Country object from the current row of the ResultSet.
     */
    private Country extractCountryFromResultSet(ResultSet rset) throws SQLException {
        Country country = new Country();
        country.setCode(rset.getString("Code"));
        country.setName(rset.getString("Name"));
        country.setContinent(rset.getString("Continent"));
        country.setRegion(rset.getString("Region"));
        country.setPopulation(rset.getInt("Population"));
        country.setCapital(rset.getInt("Capital"));
        return country;
    }
}