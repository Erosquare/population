package com.population;

import com.population.model.City;
import com.population.model.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

/**
 * Unit tests for PopulationCSVReader class
 *
 * @author Romain Richter
 */
public class PopulationCSVReaderTest {

    private static final String RESOURCES_PATH = "src/test/resources/";

    @Test
    @DisplayName("Test PopulationCSVReader - Empty CSV file")
    public void testPopulationCSVReader_EmptyCSVFile() throws IOException {
        PopulationCSVReader csvReader = new PopulationCSVReader();
        csvReader.readFromCSV(RESOURCES_PATH + "population_empty.csv");

        Assertions.assertEquals(0, csvReader.getDepartments().size(), "Empty file. The department map must be empty.");
    }

    @Test
    @DisplayName("Test PopulationCSVReader - Only header available in the CSV file")

    public void testPopulationCSVReader_OnlyHeaderInCSVFile() throws IOException {
        PopulationCSVReader csvReader = new PopulationCSVReader();
        csvReader.readFromCSV(RESOURCES_PATH + "population_only_header.csv");

        Assertions.assertEquals(0, csvReader.getDepartments().size(), "File contains only the header. The department map must be empty.");
    }

    @Test
    @DisplayName("Test PopulationCSVReader - Empty fields in CSV file")
    public void testPopulationCSVReader_EmptyFields() throws IOException {
        PopulationCSVReader csvReader = new PopulationCSVReader();
        csvReader.readFromCSV(RESOURCES_PATH + "population_empty_fields.csv");

        Assertions.assertEquals(0, csvReader.getDepartments().size(), "File contains multiple empty fields. The department map must be empty.");
    }

    @Test
    @DisplayName("Test PopulationCSVReader - Lines with empty and variable number of fields in CSV file")
    public void testPopulationCSVReader_VariableFields() throws IOException {
        PopulationCSVReader csvReader = new PopulationCSVReader();
        csvReader.readFromCSV(RESOURCES_PATH + "population_variable_fields.csv");

        Assertions.assertEquals(3, csvReader.getDepartments().size(), "3 departments (06,13,83) must be present in the map");

        Department alpesMaritimes = csvReader.getDepartments().get("06");
        Assertions.assertNotNull(alpesMaritimes, "06 should be not null");
        Assertions.assertEquals("06", alpesMaritimes.getCode(), "Department code must be 06");
        Assertions.assertEquals("", alpesMaritimes.getName(), "Department name must be empty");
        Assertions.assertEquals(1, alpesMaritimes.getCities().size(), "Alpes-Maritimes must have a one empty city");
        Assertions.assertEquals("", alpesMaritimes.getCities().get(0).getName(), "Alpes-Maritimes must have a city with an empty name");
        Assertions.assertEquals(0, alpesMaritimes.getCities().get(0).getPopulation(), "Alpes-Maritimes must have a city with 0 person");

        Department var = csvReader.getDepartments().get("83");
        Assertions.assertNotNull(var, "83 should be not null");
        Assertions.assertEquals("83", var.getCode(), "Department code must be 83");
        Assertions.assertEquals("VAR", var.getName(), "Department name must be 'VAR'");
        Assertions.assertEquals(1, var.getCities().size(), "Var must have a one empty city");
        Assertions.assertEquals("", var.getCities().get(0).getName(), "Var must have a city with an empty name");
        Assertions.assertEquals(0, var.getCities().get(0).getPopulation(), "Var must have a city with 0 person");

        Department bouchesDuRhone = csvReader.getDepartments().get("13");

        Assertions.assertNotNull(bouchesDuRhone, "13 should be not null");
        Assertions.assertEquals("13", bouchesDuRhone.getCode(), "Department code must be 83");
        Assertions.assertEquals("BOUCHES-DU-RHONE", bouchesDuRhone.getName(), "Department name must be 'BOUCHES-DU-RHONE'");
        Assertions.assertEquals(2, bouchesDuRhone.getCities().size(), "Bouches-du-Rhône must have a two cities: 'Marseille' and 'Aix-En-Provence'");

        Optional<City> marseille = bouchesDuRhone.getCities().stream().filter(c -> c.getName().equals("Marseille")).findFirst();
        Assertions.assertNotEquals(Optional.empty(), marseille, "Marseille must be part of Department 13");
        Assertions.assertEquals("Marseille", marseille.get().getName(), "Marseille must be part of Department 13");
        Assertions.assertEquals(0, marseille.get().getPopulation(), "The population of Marseille must be 0");

        Optional<City> aixEnProvence = bouchesDuRhone.getCities().stream().filter(c -> c.getName().equals("Aix-En-Provence")).findFirst();
        Assertions.assertNotEquals(Optional.empty(), aixEnProvence, "Aix-En-Provence must be part of Department 13");
        Assertions.assertEquals("Aix-En-Provence", aixEnProvence.get().getName(), "Aix-En-Provence must be part of Department 13");
        Assertions.assertEquals(5000, aixEnProvence.get().getPopulation(), "The population of Aix-En-Provence must be 5000");
    }

}
