package com.napier.sem.dao;

import com.napier.sem.models.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Uses Mockito to fake a database
 */
@ExtendWith(MockitoExtension.class)
public class CountryDAOTest {

    // Mock the database dependencies
    @Mock
    private Connection con;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rset;

    @InjectMocks
    private CountryDAO countryDAO;


    @Test
    public void testGetAllCountriesHappyPath() throws SQLException {

        // When called, return mock statement
        when(con.prepareStatement(anyString())).thenReturn(stmt);

        // When called, return mock results
        when(stmt.executeQuery()).thenReturn(rset);

        // When called, return true once, then false
        when(rset.next()).thenReturn(true).thenReturn(false);

        when(rset.getString("Code")).thenReturn("TST");
        when(rset.getString("Name")).thenReturn("Agartha");
        when(rset.getString("Continent")).thenReturn("Europe");
        when(rset.getString("Region")).thenReturn("Yugoslavia");
        when(rset.getInt("Population")).thenReturn(1000);
        when(rset.getInt("Capital")).thenReturn(1);

        List<Country> countries = countryDAO.getAllCountries();

        assertNotNull(countries);
        assertEquals(1, countries.size());

        Country country = countries.get(0);
        assertEquals("TST", country.code);
        assertEquals("Agartha", country.name);
        assertEquals("Europe", country.continent);
        assertEquals("Yugoslavia", country.region);
        assertEquals(1000, country.population);
        assertEquals(1, country.capital);
    }

    @Test
    public void testGetAllCountriesSQLException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException("Database connection failed"));

        List<Country> countries = countryDAO.getAllCountries();

        assertNull(countries);
    }
}