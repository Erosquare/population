package com.population.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Unit Tests for Department class
 *
 * @author Romain Richter
 */
public class DepartmentTest {

    @Test
    @DisplayName("Test Department - Set Name")
    public void testDepartment_SetName() {
        Department department = new Department(null, null);

        Assertions.assertNull(department.getName(), "Department name must be null");

        department.setName("Alpes-Maritimes");
        Assertions.assertEquals("Alpes-Maritimes", department.getName(), "Department name must be equal to 'Alpes-Maritimes'");
    }

    @Test
    @DisplayName("Test Department - Set null and empty Name")
    public void testDepartment_SetNullAndEmptyName() {
        Department department = new Department(null, "Alpes-Maritimes");

        department.setName(null);
        Assertions.assertEquals("Alpes-Maritimes", department.getName(), "The department name should not be changed");

        department.setName("");
        Assertions.assertEquals("Alpes-Maritimes", department.getName(), "The department name should not be changed");
    }

    @Test
    @DisplayName("Test Department - Set Code")
    public void testDepartment_SetCode() {
        Department department = new Department(null, null);

        Assertions.assertNull(department.getCode(), "Department code must be null");

        department.setCode("06");
        Assertions.assertEquals("06", department.getCode(), "Department code must be equal to '06'");
    }

    @Test
    @DisplayName("Test Department - Set null and empty Code")
    public void testDepartment_SetNullAndEmptyCode() {
        Department department = new Department("06", null);

        department.setName(null);
        Assertions.assertEquals("06", department.getCode(), "The department code should not be changed");

        department.setName("");
        Assertions.assertEquals("06", department.getCode(), "The department code should not be changed");
    }

    @Test
    @DisplayName("Test Department - Add cities")
    public void testDepartment_AddCities() {
        Department department = new Department("06", "Alpes-Maritimes");

        Assertions.assertEquals(0, department.getCities().size(), "No city by default");
        department.addCity(new City());
        Assertions.assertEquals(1, department.getCities().size(), "There must be 1 city");

        City nice = new City();
        nice.setName("Nice");

        department.addCity(nice);
        Assertions.assertEquals(2, department.getCities().size(), "There must be 2 cities");

        department.addCity(nice);
        Assertions.assertEquals(2, department.getCities().size(), "Adding an already existing city. There must be 2 cities and not 3");
    }

    @Test
    @DisplayName("Test Department - Remove cities")
    public void testDepartment_RemoveCities() {
        Department department = new Department("06", "Alpes-Maritimes");

        City nice = new City();
        nice.setName("Nice");
        City cannes = new City();
        cannes.setName("Cannes");

        department.addCity(new City());
        department.addCity(nice);
        department.addCity(cannes);

        Assertions.assertEquals(3, department.getCities().size(), "There must be 3 cities");

        department.removeCity(new City());
        department.removeCity(cannes);
        Assertions.assertEquals(1, department.getCities().size(), "Removing 2 cities. There must be 1 left.");
        Assertions.assertEquals("Nice", department.getCities().get(0).getName(), "The remaining city should be 'Nice'");
    }

    @Test
    @DisplayName("Test Department - Test total population")
    public void testDepartment_TotalPopulation() {
        Department department = new Department("06", "Alpes-Maritimes");

        City nice = new City();
        nice.setName("Nice");
        nice.setPopulation(5000);

        City cannes = new City();
        cannes.setName("Cannes");
        cannes.setPopulation(2000);

        department.addCity(new City());
        Assertions.assertEquals(0, department.getTotalPopulation(), "Adding empty city. Total population must be 0");

        department.addCity(nice);
        Assertions.assertEquals(5000, department.getTotalPopulation(), "Adding 'Nice' city (5000 people). Total population must be 5000");

        department.addCity(cannes);
        Assertions.assertEquals(7000, department.getTotalPopulation(), "Adding 'Cannes' city (2000 people). Total population must be 7000");

        department.removeCity(nice);
        Assertions.assertEquals(2000, department.getTotalPopulation(), "Removing 'Nice' city (5000 people). Total population must be 2000 (previously 7000)");

        department.removeCity(cannes);
        Assertions.assertEquals(0, department.getTotalPopulation(), "Removing 'Cannes' city (2000 people). Total population must be 0 (previously 2000");
    }

    @Test
    @DisplayName("Test Department - Test largest city")
    public void testDepartment_LargestCity() {
        Department department = new Department("06", "Alpes-Maritimes");

        Assertions.assertEquals(Optional.empty(), department.getLargestCity(), "No city in the department. Must be empty");

        City nice = new City();
        nice.setName("Nice");
        nice.setPopulation(5000);

        City cannes = new City();
        cannes.setName("Cannes");
        cannes.setPopulation(2000);

        City antibes = new City();
        antibes.setName("Antibes");
        antibes.setPopulation(3000);

        department.addCity(new City());
        department.addCity(antibes);
        department.addCity(nice);
        department.addCity(cannes);

        Assertions.assertEquals(nice, department.getLargestCity().get(), "Largest city must be 'Nice'");

        department.removeCity(nice);
        Assertions.assertEquals(antibes, department.getLargestCity().get(), "Removing 'Nice'. Largest city must be 'Antibes'");
    }
}
