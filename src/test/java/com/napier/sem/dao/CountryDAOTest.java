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

    // ... (існуючі тести залишаємо без змін, або вони тут для контексту) ...

    @Test
    public void testGetAllCountriesHappyPath() throws SQLException {
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);
        mockCountryRow();
        List<Country> countries = countryDAO.getAllCountries();
        assertNotNull(countries);
    }

    @Test
    public void testGetCountriesByContinentHappyPath() throws SQLException {
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);
        mockCountryRow();
        countryDAO.getCountriesByContinent("Europe");
        verify(stmt).setString(1, "Europe");
    }

    @Test
    public void testGetCountriesByRegionHappyPath() throws SQLException {
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);
        mockCountryRow();
        countryDAO.getCountriesByRegion("Western Europe");
        verify(stmt).setString(1, "Western Europe");
    }

    // --- НОВІ ТЕСТИ ДЛЯ TOP N ---

    @Test
    public void testGetTopNCountriesWorld() throws SQLException {
        // Arrange
        int n = 5;
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);
        mockCountryRow();

        // Act
        List<Country> countries = countryDAO.getTopNCountries(n);

        // Assert
        assertNotNull(countries);
        assertEquals(1, countries.size());
        // Verify LIMIT parameter was set
        verify(stmt).setInt(1, n);
    }

    @Test
    public void testGetTopNCountriesByContinent() throws SQLException {
        // Arrange
        int n = 5;
        String continent = "Asia";
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);
        mockCountryRow();

        // Act
        List<Country> countries = countryDAO.getTopNCountriesByContinent(continent, n);

        // Assert
        assertNotNull(countries);
        // Verify parameters: 1 is Continent, 2 is LIMIT
        verify(stmt).setString(1, continent);
        verify(stmt).setInt(2, n);
    }

    @Test
    public void testGetTopNCountriesByRegion() throws SQLException {
        // Arrange
        int n = 5;
        String region = "Caribbean";
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);
        mockCountryRow();

        // Act
        List<Country> countries = countryDAO.getTopNCountriesByRegion(region, n);

        // Assert
        assertNotNull(countries);
        // Verify parameters: 1 is Region, 2 is LIMIT
        verify(stmt).setString(1, region);
        verify(stmt).setInt(2, n);
    }

    // ... helper method ...
    private void mockCountryRow() throws SQLException {
        when(rset.getString("Code")).thenReturn("TST");
        when(rset.getString("Name")).thenReturn("Agartha");
        when(rset.getString("Continent")).thenReturn("Europe");
        when(rset.getString("Region")).thenReturn("Yugoslavia");
        when(rset.getInt("Population")).thenReturn(1000);
        when(rset.getInt("Capital")).thenReturn(1);
    }
}