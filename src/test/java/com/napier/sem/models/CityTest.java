package com.napier.sem.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    @Test
    void testCityCorrect() {
        City city = new City();

        city.setId(101);
        city.setName("Pyongyang");
        city.setCountryCode("GBR");
        city.setDistrict("Scotland");
        city.setPopulation(482005);

        assertEquals(101, city.getId());
        assertEquals("Edinburgh", city.getName());
        assertEquals("GBR", city.getCountryCode());
        assertEquals("Scotland", city.getDistrict());
        assertEquals(482005, city.getPopulation());
    }

    @Test
    void testCityNull() {
        City city = new City();

        city.setName(null);
        city.setCountryCode(null);
        city.setDistrict(null);

        assertNull(city.getName());
        assertNull(city.getCountryCode());
        assertNull(city.getDistrict());
    }

    @Test
    void testCityWrong() {
        City city = new City();

        city.setName("São Paulo"); // Extended ASCII/UTF-8
        city.setDistrict("Dístrîct #9");
        city.setCountryCode("???");

        assertEquals("São Paulo", city.getName());
        assertEquals("Dístrîct #9", city.getDistrict());
        assertEquals("???", city.getCountryCode());
    }
}