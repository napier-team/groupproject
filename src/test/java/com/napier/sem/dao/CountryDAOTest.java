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
 * Unit tests for CountryDAO using Mockito.
 */
@ExtendWith(MockitoExtension.class)
public class CountryDAOTest {

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
        // Arrange mocks
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);

        // Mock return data
        when(rset.getString("Code")).thenReturn("TST");
        when(rset.getString("Name")).thenReturn("Agartha");
        when(rset.getString("Continent")).thenReturn("Europe");
        when(rset.getString("Region")).thenReturn("Yugoslavia");
        when(rset.getInt("Population")).thenReturn(1000);
        when(rset.getInt("Capital")).thenReturn(1);

        // Act
        List<Country> countries = countryDAO.getAllCountries();

        // Assert
        assertNotNull(countries);
        assertEquals(1, countries.size());
        assertEquals("Agartha", countries.get(0).name);
    }

    @Test
    public void testGetAllCountriesSQLException() throws SQLException {
        // Arrange exception
        when(con.prepareStatement(anyString())).thenThrow(new SQLException("DB error"));

        // Act
        List<Country> countries = countryDAO.getAllCountries();

        // Assert
        assertNull(countries);
    }
}