package com.napier.sem.dao;

import com.napier.sem.models.City;
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
 * Unit tests for CityDAO using Mockito.
 */
@ExtendWith(MockitoExtension.class)
public class CityDAOTest {

    @Mock
    private Connection con;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rset;

    @InjectMocks
    private CityDAO cityDAO;

    @Test
    public void testGetAllCitiesHappyPath() throws SQLException {
        // Arrange
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);
        mockCityRow();

        // Act
        List<City> cities = cityDAO.getAllCities();

        // Assert
        assertNotNull(cities);
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetCitiesByContinent() throws SQLException {
        setupMock();
        cityDAO.getCitiesByContinent("Asia");
        verify(stmt).setString(1, "Asia");
    }

    @Test
    public void testGetCitiesByRegion() throws SQLException {
        setupMock();
        cityDAO.getCitiesByRegion("Caribbean");
        verify(stmt).setString(1, "Caribbean");
    }

    @Test
    public void testGetCitiesByCountry() throws SQLException {
        setupMock();
        cityDAO.getCitiesByCountry("GBR");
        verify(stmt).setString(1, "GBR");
    }

    @Test
    public void testGetCitiesByDistrict() throws SQLException {
        setupMock();
        cityDAO.getCitiesByDistrict("Scotland");
        verify(stmt).setString(1, "Scotland");
    }

    // --- Helpers ---

    private void setupMock() throws SQLException {
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);
        mockCityRow();
    }

    private void mockCityRow() throws SQLException {
        when(rset.getInt("ID")).thenReturn(67);
        when(rset.getString("Name")).thenReturn("Test City");
        when(rset.getString("CountryCode")).thenReturn("TET");
        when(rset.getString("District")).thenReturn("District 9");
        when(rset.getInt("Population")).thenReturn(5000);
    }
}