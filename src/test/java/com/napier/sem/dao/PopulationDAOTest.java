package com.napier.sem.dao;

import com.napier.sem.models.PopulationReport;
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
public class PopulationDAOTest {

    @Mock
    private Connection con;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rset;

    @InjectMocks
    private PopulationDAO dao;

    @Test
    public void testGetPopulationReportsByContinent() throws SQLException {
        setupMock();
        List<PopulationReport> reports = dao.getPopulationReportsByContinent();
        assertNotNull(reports);
        assertEquals(1, reports.size());

        PopulationReport r = reports.get(0);
        assertEquals("Europe", r.getName());
        assertEquals(1000, r.getTotalPopulation());
        assertEquals(500, r.getUrbanPopulation());
        assertEquals(50.0f, r.getUrbanPercentage());
    }

    private void setupMock() throws SQLException {
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);

        when(rset.getString("Name")).thenReturn("Europe");
        when(rset.getLong("TotalPop")).thenReturn(1000L);
        when(rset.getLong("CityPop")).thenReturn(500L);
    }
}