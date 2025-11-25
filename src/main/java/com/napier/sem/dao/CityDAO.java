package com.napier.sem.dao;

import com.napier.sem.models.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the ICityDAO interface to handle city database operations.
 */
public class CityDAO implements ICityDAO {
    private final Connection con;

    public CityDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<City> getAllCities() {
        String sql = "SELECT ID, Name, CountryCode, District, Population FROM city ORDER BY Population DESC";
        List<City> cities = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {

            while (rset.next()) {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("Name"));
                city.setCountryCode(rset.getString("CountryCode"));
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("Population"));
                cities.add(city);
            }
            return cities;
        } catch (SQLException e) {
            System.err.println("Failed to get city details: " + e.getMessage());
            return null;
        }
    }
}