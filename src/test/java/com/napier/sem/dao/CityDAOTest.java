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
 * Unit tests for CityDAO.
 * This class uses Mockito to "mock" the database connection.
 */
@ExtendWith(MockitoExtension.class) // This tells JUnit to use Mockito
public class CityDAOTest {

    // Mock the database dependencies
    @Mock
    private Connection con;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rset;

    // Inject the mocks into the class we are testing
    @InjectMocks
    private CityDAO cityDAO;

    /**
     * Test the "Happy Path" where data is returned successfully.
     */
    @Test
    public void testGetAllCitiesHappyPath() throws SQLException {
        // 1. Arrange: Program the mocks

        // When con.prepareStatement() is called, return our mock statement
        when(con.prepareStatement(anyString())).thenReturn(stmt);

        // When stmt.executeQuery() is called, return our mock result set
        when(stmt.executeQuery()).thenReturn(rset);

        // When rset.next() is called, return true once, then false
        when(rset.next()).thenReturn(true).thenReturn(false);

        // Program the mock result set to return sample data
        when(rset.getString("ID")).thenReturn("101");
        when(rset.getString("Name")).thenReturn("Test City");
        when(rset.getString("CountryCode")).thenReturn("TCY");
        when(rset.getString("District")).thenReturn("Test District");
        when(rset.getInt("Population")).thenReturn(5000);

        // 2. Act: Call the method
        List<City> cities = cityDAO.getAllCities();

        // 3. Assert: Check the results
        assertNotNull(cities);
        assertEquals(1, cities.size()); // We expected one city

        City city = cities.get(0);
        assertEquals("101", city.id);
        assertEquals("Test City", city.name);
        assertEquals("TCY", city.code);
        assertEquals("Test District", city.district);
        assertEquals(5000, city.population);
    }

    /**
     * Test the "Exception Path" where the database throws an error.
     */
    @Test
    public void testGetAllCitiesSQLException() throws SQLException {
        // 1. Arrange: Program the mock connection to throw an exception
        when(con.prepareStatement(anyString())).thenThrow(new SQLException("Database connection failed"));

        // 2. Act: Call theN method
        List<City> cities = cityDAO.getAllCities();

        // 3. Assert: Check that the method returned null as per the catch block
        assertNull(cities);
    }
}