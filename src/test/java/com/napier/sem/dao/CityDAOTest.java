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
import static org.mockito.Mockito.when;

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
        // Arrange mocks
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false); // Loop once

        // Mock return data
        when(rset.getString("ID")).thenReturn("67");
        when(rset.getString("Name")).thenReturn("Test City");
        when(rset.getString("CountryCode")).thenReturn("TET");
        when(rset.getString("District")).thenReturn("Mango Phonk");
        when(rset.getInt("Population")).thenReturn(5000);

        // Act
        List<City> cities = cityDAO.getAllCities();

        // Assert
        assertNotNull(cities);
        assertEquals(1, cities.size());
        City city = cities.get(0);
        assertEquals("67", city.id);
        assertEquals("Test City", city.name);
    }

    @Test
    public void testGetAllCitiesSQLException() throws SQLException {
        // Arrange exception
        when(con.prepareStatement(anyString())).thenThrow(new SQLException("Database connection failed"));

        // Act
        List<City> cities = cityDAO.getAllCities();

        // Assert
        assertNull(cities);
    }
}