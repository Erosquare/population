package com.population;

import com.population.model.City;
import com.population.model.Department;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Utility class to read and parse a CSV file containing data about departments and cities
 *
 * @author Romain Richter
 */
public class PopulationCSVReader {
    /**
     * Map containing all departments. The key is the code of the department.
     */
    private HashMap<String, Department> departments = new HashMap<>();

    /**
     * Read and parse a CSV file and store all departments data in a Map.
     *
     * @param path The path of the CSV file
     * @throws IOException Throws an exception in case the CSV file is not found or if it cannot be parsed correctly.
     */
    public void readFromCSV(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;

        // Read the first line (header) and discard it
        reader.readLine();

        // Read the rest of the lines
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split("[,;|]");

            City city = new City();

            String departmentCode = (fields.length > 0)? fields[0] : "";

            // Skip if the line is empty or if we cannot retrieve the department code
            if ("".equals(departmentCode)) {
                continue;
            }

            city.setName((fields.length > 1)? fields[1] : "");
            try {
                city.setPopulation((fields.length > 2)? Integer.parseInt(fields[2]) : 0);
            } catch (NumberFormatException ex) {
                city.setPopulation(0);
            }
            String departmentName = (fields.length > 3)? fields[3] : "";

            // Get the department data or create a new one if it doesn't exist
            Department departmentData = departments.getOrDefault(departmentCode, new Department(departmentCode, departmentName));

            // Update the departments data by adding the new city
            departmentData.addCity(city);
            departmentData.setName(departmentName);
            departments.put(departmentCode, departmentData);
        }
        reader.close();
    }

    /**
     * Retrieve the departments after CSV parsing
     *
     * @return A map containing the data of departments present in the CSV file
     */
    public HashMap<String, Department> getDepartments() {
        return departments;
    }

}
