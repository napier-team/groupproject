package com.napier.sem.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    void testCountryCorrect() {
        Country country = new Country();

        country.setCode("UAH");
        country.setName("Kolomyia");
        country.setContinent("Europe");
        country.setRegion("Eastern Europe");
        country.setPopulation(67000000);
        country.setCapital(123);

        assertEquals("UAH", country.getCode());
        assertEquals("Kolomyia", country.getName());
        assertEquals("Europe", country.getContinent());
        assertEquals("Eastern Europe", country.getRegion());
        assertEquals(67000000, country.getPopulation());
        assertEquals(123, country.getCapital());
    }

    @Test
    void testCountryNull() {
        Country country = new Country();

        country.setCode(null);
        country.setName(null);
        country.setContinent(null);
        country.setRegion(null);

        assertNull(country.getCode());
        assertNull(country.getName());
        assertNull(country.getContinent());
        assertNull(country.getRegion());
    }

    @Test
    void testCountryWrong() {
        Country country = new Country();

        country.setName("#@)₴?$0");
        country.setCode("X-X"); // Hyphens
        country.setRegion("North & South"); // Ampersands

        assertEquals("#@)₴?$0", country.getName());
        assertEquals("X-X", country.getCode());
        assertEquals("&&&&&", country.getRegion());
    }
}