package com.napier.sem.dao;

import com.napier.sem.models.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * implements the ICityDAO interface, to be used with city operations.
 */
public class CityDAO implements ICityDAO {
    private final Connection con;

    /**
     * Constructs a CityDAO with a database connection.
     */
    public CityDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<City> getAllCities() {
        // SQL query to select all necessary fields from the city table
        String sql = "SELECT ID, Name, CountryCode, District, Population "
                + "FROM city ORDER BY Population DESC";

        List<City> cities = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {

            // Creates city objects from sql results
            while (rset.next()) {
                City city = new City();
                city.id = rset.getString("ID");
                city.name = rset.getString("Name");
                city.code = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }
            return cities;
        } catch (SQLException e) {
            System.err.println("Failed to get city details: " + e.getMessage());
            return null; // Failure if returned
        }
    }
}
