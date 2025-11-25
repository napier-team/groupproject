package com.napier.sem.dao;

import com.napier.sem.dao.impl.LanguageDAO;
import com.napier.sem.models.LanguageReport;
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
public class LanguageDAOTest {

    @Mock
    private Connection con;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rset;

    @InjectMocks
    private LanguageDAO dao;

    @Test
    public void testGetLanguageReport() throws SQLException {
        // Arrange
        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(true).thenReturn(false);

        // Mock result data
        when(rset.getString("Language")).thenReturn("Chinese");
        when(rset.getLong("TotalSpeakers")).thenReturn(1000000000L);
        when(rset.getFloat("WorldPercentage")).thenReturn(15.5f);

        // Act
        List<LanguageReport> reports = dao.getLanguageReport();

        // Assert
        assertNotNull(reports);
        assertEquals(1, reports.size());

        LanguageReport report = reports.get(0);
        assertEquals("Chinese", report.getLanguageName());
        assertEquals(1000000000L, report.getTotalSpeakers());
        assertEquals(15.5f, report.getWorldPercentage());
    }

    // --- UNHAPPY TEST ---

    @Test
    public void testLanguageEmpty() throws SQLException {
        // crash Fix
        doNothing().when(stmt).close();
        doNothing().when(rset).close();

        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);
        when(rset.next()).thenReturn(false);

        List<LanguageReport> reports = dao.getLanguageReport();
        assertNotNull(reports);
        assertTrue(reports.isEmpty());
    }
}