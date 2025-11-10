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
 * Unit tests for CountryDAO.
 * This class uses Mockito to "mock" the database connection.
 */
@ExtendWith(MockitoExtension.class) // This tells JUnit to use Mockito
public class CountryDAOTest {

    // Mock the database dependencies
    @Mock
    private Connection con;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rset;

    // Inject the mocks into the class we are testing
    @InjectMocks
    private CountryDAO countryDAO;

    /**
     * Test the "Happy Path" where data is returned successfully.
     */
    @Test
    public void testGetAllCountriesHappyPath() throws SQLException {
        // 1. Arrange: Program the mocks

        // When con.prepareStatement() is called, return our mock statement
        when(con.prepareStatement(anyString())).thenReturn(stmt);

        // When stmt.executeQuery() is called, return our mock result set
        when(stmt.executeQuery()).thenReturn(rset);

        // When rset.next() is called, return true once, then false
        when(rset.next()).thenReturn(true).thenReturn(false);

        // Program the mock result set to return sample data
        when(rset.getString("Code")).thenReturn("TST");
        when(rset.getString("Name")).thenReturn("Test Country");
        when(rset.getString("Continent")).thenReturn("Testinent");
        when(rset.getString("Region")).thenReturn("Test Region");
        when(rset.getInt("Population")).thenReturn(1000);
        when(rset.getInt("Capital")).thenReturn(1);

        // 2. Act: Call the method
        List<Country> countries = countryDAO.getAllCountries();

        // 3. Assert: Check the results
        assertNotNull(countries);
        assertEquals(1, countries.size()); // We expected one country

        Country country = countries.get(0);
        assertEquals("TST", country.code);
        assertEquals("Test Country", country.name);
        assertEquals("Testinent", country.continent);
        assertEquals("Test Region", country.region);
        assertEquals(1000, country.population);
        assertEquals(1, country.capital);
    }

    /**
     * Test the "Exception Path" where the database throws an error.
     */
    @Test
    public void testGetAllCountriesSQLException() throws SQLException {
        // 1. Arrange: Program the mock connection to throw an exception
        when(con.prepareStatement(anyString())).thenThrow(new SQLException("Database connection failed"));

        // 2. Act: Call the method
        List<Country> countries = countryDAO.getAllCountries();

        // 3. Assert: Check that the method returned null as per the catch block
        assertNull(countries);
    }
}