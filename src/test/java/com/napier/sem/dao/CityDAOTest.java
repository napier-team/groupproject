package com.napier.sem.dao;

import com.napier.sem.dao.impl.CityDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        setupMock();
        cityDAO.getAllCities();
        verify(stmt, atLeastOnce()).executeQuery();
    }

    // --- TOP N TESTS ---

    @Test
    public void testGetTopNCitiesWorld() throws SQLException {
        setupMock();
        int n = 5;
        cityDAO.getTopNCitiesWorld(n);
        verify(stmt).setInt(1, n);
    }

    @Test
    public void testGetTopNCitiesByContinent() throws SQLException {
        setupMock();
        int n = 5;
        String continent = "Asia";
        cityDAO.getTopNCitiesByContinent(continent, n);
        // Verify Param 1 is Continent, Param 2 is Limit
        verify(stmt).setString(1, continent);
        verify(stmt).setInt(2, n);
    }

    @Test
    public void testGetTopNCitiesByRegion() throws SQLException {
        setupMock();
        int n = 5;
        String region = "Caribbean";
        cityDAO.getTopNCitiesByRegion(region, n);
        verify(stmt).setString(1, region);
        verify(stmt).setInt(2, n);
    }

    @Test
    public void testGetTopNCitiesByCountry() throws SQLException {
        setupMock();
        int n = 5;
        String countryCode = "GBR";
        cityDAO.getTopNCitiesByCountry(countryCode, n);
        verify(stmt).setString(1, countryCode);
        verify(stmt).setInt(2, n);
    }

    @Test
    public void testGetTopNCitiesByDistrict() throws SQLException {
        setupMock();
        int n = 5;
        String district = "Scotland";
        cityDAO.getTopNCitiesByDistrict(district, n);
        verify(stmt).setString(1, district);
        verify(stmt).setInt(2, n);
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