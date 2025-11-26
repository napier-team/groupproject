package com.napier.sem.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LanguageReportTest {

    @Test
    void testLanguageReportCorrect() {
        LanguageReport report = new LanguageReport();

        report.setLanguageName("LanguageOfFacts");
        report.setTotalSpeakers(4100000000L);
        report.setWorldPercentage(67.67f);

        assertEquals("LanguageOfFacts", report.getLanguageName());
        assertEquals(4100000000L, report.getTotalSpeakers());
        assertEquals(67.67f, report.getWorldPercentage(), 0.01);
    }

    @Test
    void testLanguageReportNull() {
        LanguageReport report = new LanguageReport();

        report.setLanguageName(null);

        assertNull(report.getLanguageName());
    }

    @Test
    void testLanguageReportWrong() {
        LanguageReport report = new LanguageReport();

        String specialLang = "中文";
        report.setLanguageName(specialLang);

        assertEquals(specialLang, report.getLanguageName());
    }
}