package com.napier.sem.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PopulationReportTest {

    @Test
    void testReportCorrect() {
        PopulationReport report = new PopulationReport();

        report.setName("Test Region");
        report.setTotalPopulation(1000L);
        report.setUrbanPopulation(200L);

        // Test basic getters
        assertEquals("Test Region", report.getName());
        assertEquals(1000L, report.getTotalPopulation());
        assertEquals(200L, report.getUrbanPopulation());

        assertEquals(800L, report.getRuralPopulation());

        // Test computed percentages
        assertEquals(20.0f, report.getUrbanPercentage(), 0.01);
        assertEquals(80.0f, report.getRuralPercentage(), 0.01);
    }

    @Test
    void testReportNull() {
        PopulationReport report = new PopulationReport();

        report.setName(null);

        assertNull(report.getName());
    }

    @Test
    void testReportWrong() {
        PopulationReport report = new PopulationReport();

        String symbolData = "Санічка Андрюха©";
        report.setName(symbolData);

        assertEquals(symbolData, report.getName());
    }
}