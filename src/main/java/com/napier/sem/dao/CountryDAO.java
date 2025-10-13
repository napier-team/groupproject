package com.napier.sem.dao;

import com.napier.sem.models.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for handling all country-related database operations.
 * This class implements the ICountryDAO interface.
 */
public class CountryDAO implements ICountryDAO {
    private final Connection con;

    /**
     * Constructs a CountryDAO with a database connection.
     * @param con The database connection to be used for queries.
     */
    public CountryDAO(Connection con) {
        this.con = con;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getAllCountries() {
        String sql = "SELECT Code, Name, Continent, Region, Population, Capital "
                + "FROM country ORDER BY Population DESC";

        List<Country> countries = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {

            while (rset.next()) {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch (SQLException e) {
            System.err.println("Failed to get country details: " + e.getMessage());
            return null; // Return null to indicate failure
        }
    }
}