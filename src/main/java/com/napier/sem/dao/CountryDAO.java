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
        return executeQuery(sql, null);
    }

    @Override
    public List<Country> getCountriesByContinent(String continent) {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                "FROM country WHERE Continent = ? ORDER BY Population DESC";
        return executeQuery(sql, continent);
    }

    @Override
    public List<Country> getCountriesByRegion(String region) {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                "FROM country WHERE Region = ? ORDER BY Population DESC";
        return executeQuery(sql, region);
    }

    /**
     * Helper method to execute queries and map results to Country objects.
     * Prevents code duplication for mapping logic.
     *
     * @param sql   The SQL query string.
     * @param param The parameter to set in the PreparedStatement (can be null if no params).
     * @return A list of Country objects or null if an error occurs.
     */
    private List<Country> executeQuery(String sql, String param) {
        List<Country> countries = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            // Set parameter if provided
            if (param != null) {
                stmt.setString(1, param);
            }

            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    Country country = new Country();
                    country.setCode(rset.getString("Code"));
                    country.setName(rset.getString("Name"));
                    country.setContinent(rset.getString("Continent"));
                    country.setRegion(rset.getString("Region"));
                    country.setPopulation(rset.getInt("Population"));
                    country.setCapital(rset.getInt("Capital"));
                    countries.add(country);
                }
            }
            return countries;
        } catch (SQLException e) {
            System.err.println("Failed to execute country query: " + e.getMessage());
            return null;
        }
    }
}