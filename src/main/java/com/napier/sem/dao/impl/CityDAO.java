package com.napier.sem.dao.impl;

import com.napier.sem.dao.ICityDAO;
import com.napier.sem.models.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICityDAO using JDBC.
 */
public class CityDAO implements ICityDAO {
    private final Connection con;

    public CityDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<City> getAllCities() {
        String sql = "SELECT ID, Name, CountryCode, District, Population FROM city ORDER BY Population DESC";
        return executeQuery(sql, stmt -> {});
    }

    @Override
    public List<City> getCitiesByContinent(String continent) {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? ORDER BY city.Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, continent));
    }

    @Override
    public List<City> getCitiesByRegion(String region) {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? ORDER BY city.Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, region));
    }

    @Override
    public List<City> getCitiesByCountry(String countryCode) {
        String sql = "SELECT ID, Name, CountryCode, District, Population FROM city WHERE CountryCode = ? ORDER BY Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, countryCode));
    }

    @Override
    public List<City> getCitiesByDistrict(String district) {
        String sql = "SELECT ID, Name, CountryCode, District, Population FROM city WHERE District = ? ORDER BY Population DESC";
        return executeQuery(sql, stmt -> stmt.setString(1, district));
    }

    // --- Top N Implementation ---

    @Override
    public List<City> getTopNCitiesWorld(int n) {
        String sql = "SELECT ID, Name, CountryCode, District, Population " +
                "FROM city ORDER BY Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> stmt.setInt(1, n));
    }

    @Override
    public List<City> getTopNCitiesByContinent(String continent, int n) {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? ORDER BY city.Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
        });
    }

    @Override
    public List<City> getTopNCitiesByRegion(String region, int n) {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? ORDER BY city.Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> {
            stmt.setString(1, region);
            stmt.setInt(2, n);
        });
    }

    @Override
    public List<City> getTopNCitiesByCountry(String countryCode, int n) {
        String sql = "SELECT ID, Name, CountryCode, District, Population " +
                "FROM city WHERE CountryCode = ? ORDER BY Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> {
            stmt.setString(1, countryCode);
            stmt.setInt(2, n);
        });
    }

    @Override
    public List<City> getTopNCitiesByDistrict(String district, int n) {
        String sql = "SELECT ID, Name, CountryCode, District, Population " +
                "FROM city WHERE District = ? ORDER BY Population DESC LIMIT ?";
        return executeQuery(sql, stmt -> {
            stmt.setString(1, district);
            stmt.setInt(2, n);
        });
    }

    // --- Private Helpers ---

    @FunctionalInterface
    private interface StatementPreparer {
        void setParameters(PreparedStatement stmt) throws SQLException;
    }

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