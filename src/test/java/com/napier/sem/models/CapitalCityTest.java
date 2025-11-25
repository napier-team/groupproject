package com.napier.sem.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CapitalCityTest {

    @Test
    void testCapitalCityCorrect() {
        CapitalCity city = new CapitalCity();

        city.setName("Kosice");
        city.setCountry("Slovakia");
        city.setPopulation(77777);

        assertEquals("Kosice", city.getName());
        assertEquals("Slovakia", city.getCountry());
        assertEquals(77777, city.getPopulation());
    }

    @Test
    void testCapitalCityNull() {
        CapitalCity city = new CapitalCity();

        city.setName(null);
        city.setCountry(null);

        assertNull(city.getName());
        assertNull(city.getCountry());
    }

    @Test
    void testCapitalCityWrong() {
        CapitalCity city = new CapitalCity();

        // Testing with special characters and symbols
        String weirdName = "#@)₴?$0";
        city.setName(weirdName);
        city.setCountry("!! >> ??");

        assertEquals(weirdName, city.getName());
        assertEquals("!! >> ??", city.getCountry());
    }
}