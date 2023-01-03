package com.population.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for City class
 *
 * @author Romain Richter
 */
public class CityTest {

    @Test
    @DisplayName("Test City - Set Name")
    public void testCity_SetName() {
        City city = new City();
        city.setName("Nice");

        Assertions.assertEquals("Nice", city.getName(), "City name must be equal to 'Nice'");
    }

    @Test
    @DisplayName("Test City - Set null and empty Name")
    public void testCity_SetNullAndEmptyName() {
        City city = new City();
        city.setName("Nice");

        city.setName(null);
        Assertions.assertEquals("Nice", city.getName(), "The city name should not be changed");

        city.setName("");
        Assertions.assertEquals("Nice", city.getName(), "The city name should not be changed");
    }

    @Test
    @DisplayName("Test City - Set positive population")
    public void testCity_SetPopulation() {
        City city = new City();
        city.setPopulation(5000);

        Assertions.assertEquals(5000, city.getPopulation(), "The population must be equal to 5000");
    }

    @Test
    @DisplayName("Test City - Set negative population")
    public void testCity_SetNegativePopulation() {
        City city = new City();
        city.setPopulation(-1);

        Assertions.assertEquals(0, city.getPopulation(), "The population must be equal to 0");
    }
}
