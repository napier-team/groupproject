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
 * Uses Mockito to fake a database
 */
@ExtendWith(MockitoExtension.class)
public class CityDAOTest {

    // Mock the database
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
        // 1. Arrange: Program the mocks

        // When called, return mock statement
        when(con.prepareStatement(anyString())).thenReturn(stmt);

        // When called, return mock results
        when(stmt.executeQuery()).thenReturn(rset);

        // When called, return true once then false
        when(rset.next()).thenReturn(true).thenReturn(false);

        // Program the mock result set to return sample data
        when(rset.getString("ID")).thenReturn("67");
        when(rset.getString("Name")).thenReturn("Test City");
        when(rset.getString("CountryCode")).thenReturn("TET");
        when(rset.getString("District")).thenReturn("Mango Phonk");
        when(rset.getInt("Population")).thenReturn(5000);

        List<City> cities = cityDAO.getAllCities();

        assertNotNull(cities);
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("67", city.id);
        assertEquals("Test City", city.name);
        assertEquals("TET", city.code);
        assertEquals("Mango Phonk", city.district);
        assertEquals(5000, city.population);
    }

    @Test
    public void testGetAllCitiesSQLException() throws SQLException {
        when(con.prepareStatement(anyString())).thenThrow(new SQLException("Database connection failed"));

        List<City> cities = cityDAO.getAllCities();

        assertNull(cities);
    }
}