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
import static org.mockito.Mockito.*;

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
        // Arrange
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);

        mockCountryRow();

        // Act
        List<Country> countries = countryDAO.getAllCountries();

        // Assert
        assertNotNull(countries);
        assertEquals(1, countries.size());
        assertEquals("Agartha", countries.get(0).getName());
    }

    @Test
    public void testGetCountriesByContinentHappyPath() throws SQLException {
        // Arrange
        String continent = "Europe";
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);

        mockCountryRow();

        // Act
        List<Country> countries = countryDAO.getCountriesByContinent(continent);

        // Assert
        assertNotNull(countries);
        assertEquals(1, countries.size());
        // Verify that setString was called with the correct continent
        verify(stmt).setString(1, continent);
    }

    @Test
    public void testGetCountriesByRegionHappyPath() throws SQLException {
        // Arrange
        String region = "Western Europe";
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);

        mockCountryRow();

        // Act
        List<Country> countries = countryDAO.getCountriesByRegion(region);

        // Assert
        assertNotNull(countries);
        assertEquals(1, countries.size());
        // Verify that setString was called with the correct region
        verify(stmt).setString(1, region);
    }

    @Test
    public void testSQLExceptionHandledGracefully() throws SQLException {
        // Arrange
        when(con.prepareStatement(anyString())).thenThrow(new SQLException("DB error"));

        // Act
        List<Country> resultAll = countryDAO.getAllCountries();
        List<Country> resultCont = countryDAO.getCountriesByContinent("Asia");
        List<Country> resultReg = countryDAO.getCountriesByRegion("Caribbean");

        // Assert
        assertNull(resultAll);
        assertNull(resultCont);
        assertNull(resultReg);
    }

    /**
     * Helper method to mock a standard country row in ResultSet
     */
    private void mockCountryRow() throws SQLException {
        when(rset.getString("Code")).thenReturn("TST");
        when(rset.getString("Name")).thenReturn("Agartha");
        when(rset.getString("Continent")).thenReturn("Europe");
        when(rset.getString("Region")).thenReturn("Yugoslavia");
        when(rset.getInt("Population")).thenReturn(1000);
        when(rset.getInt("Capital")).thenReturn(1);
    }
}