package com.napier.sem.dao;

import com.napier.sem.models.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICityDAO. Handles database interactions for City data.
 */
public class CityDAO implements ICityDAO {
    private final Connection con;

    public CityDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<City> getAllCities() {
        String sql = "SELECT ID, Name, CountryCode, District, Population "
                + "FROM city ORDER BY Population DESC";
        // Null limit means "fetch all"
        return executeQuery(sql, null);
    }

    @Override
    public List<City> getTopNCities(int n) {
        // Use ? for parameter to prevent SQL injection
        String sql = "SELECT ID, Name, CountryCode, District, Population "
                + "FROM city ORDER BY Population DESC LIMIT ?";
        return executeQuery(sql, n);
    }

    /**
     * Helper method to execute query and map results.
     * Reduces code duplication.
     */
    private List<City> executeQuery(String sql, Integer limit) {
        List<City> cities = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            if (limit != null) {
                stmt.setInt(1, limit);
            }

            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    City city = new City();
                    city.id = rset.getString("ID");
                    city.name = rset.getString("Name");
                    city.code = rset.getString("CountryCode");
                    city.district = rset.getString("District");
                    city.population = rset.getInt("Population");
                    cities.add(city);
                }
            }
            return cities;
        } catch (SQLException e) {
            System.err.println("Failed to get city details: " + e.getMessage());
            return null;
        }
    }
}