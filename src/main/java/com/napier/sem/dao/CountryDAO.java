package com.napier.sem.dao;

import com.napier.sem.models.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements ICountryDAO to handle country database operations.
 */
public class CountryDAO implements ICountryDAO {
    private final Connection con;

    public CountryDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Country> getAllCountries() {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
        List<Country> countries = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {

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
            return countries;
        } catch (SQLException e) {
            System.err.println("Failed to get country details: " + e.getMessage());
            return null;
        }
    }
}