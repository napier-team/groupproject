package com.napier.sem.dao;

import com.napier.sem.dao.impl.CapitalCityDAO;
import com.napier.sem.models.CapitalCity;
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

@ExtendWith(MockitoExtension.class)
public class CapitalCityDAOTest {

    @Mock
    private Connection con;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rset;

    @InjectMocks
    private CapitalCityDAO dao;

    @Test
    public void testGetAllCapitalCities() throws SQLException {
        setupMock();
        List<CapitalCity> result = dao.getAllCapitalCities();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("London", result.get(0).getName());
    }

    @Test
    public void testGetCapitalCitiesByContinent() throws SQLException {
        setupMock();
        dao.getCapitalCitiesByContinent("Europe");
        verify(stmt).setString(1, "Europe");
    }

    @Test
    public void testGetTopNCapitalCitiesWorld() throws SQLException {
        setupMock();
        dao.getTopNCapitalCitiesWorld(5);
        verify(stmt).setInt(1, 5);
    }

    // --- Helpers ---
    private void setupMock() throws SQLException {
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);

        when(rset.getString("Name")).thenReturn("London");
        when(rset.getString("Country")).thenReturn("United Kingdom");
        when(rset.getInt("Population")).thenReturn(9000000);
    }
}