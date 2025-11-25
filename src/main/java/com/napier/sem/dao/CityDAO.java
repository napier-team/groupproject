package com.napier.sem.dao;

import com.napier.sem.models.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICityDAO using JDBC.
 * Handles complex queries involving joins with the Country table.
 */
public class CityDAO implements ICityDAO {
    private final Connection con;

    public CityDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<City> getAllCities() {
        String sql = "SELECT ID, Name, CountryCode, District, Population " +
                "FROM city ORDER BY Population DESC";
        return executeQuery(sql, stmt -> {});
    }

    @Override
    public List<City> getCitiesByContinent(String continent) {
        // JOIN is required because City table doesn't have Continent data
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, continent));
    }

    @Override
    public List<City> getCitiesByRegion(String region) {
        // JOIN is required because City table doesn't have Region data
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, region));
    }

    @Override
    public List<City> getCitiesByCountry(String countryCode) {
        String sql = "SELECT ID, Name, CountryCode, District, Population " +
                "FROM city WHERE CountryCode = ? ORDER BY Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, countryCode));
    }

    @Override
    public List<City> getCitiesByDistrict(String district) {
        String sql = "SELECT ID, Name, CountryCode, District, Population " +
                "FROM city WHERE District = ? ORDER BY Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, district));
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
     * Reduces code duplication for standard city queries.
     */
    private List<City> executeQuery(String sql, StatementPreparer preparer) {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            preparer.setParameters(stmt);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(extractCityFromResultSet(rset));
                }
            }
            return cities;
        } catch (SQLException e) {
            System.err.println("Failed to execute city query: " + e.getMessage());
            return null;
        }
    }

    /**
     * Extracts a City object from the current row of the ResultSet.
     */
    private City extractCityFromResultSet(ResultSet rset) throws SQLException {
        City city = new City();
        city.setId(rset.getInt("ID"));
        city.setName(rset.getString("Name"));
        city.setCountryCode(rset.getString("CountryCode"));
        city.setDistrict(rset.getString("District"));
        city.setPopulation(rset.getInt("Population"));
        return city;
    }
}