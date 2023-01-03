package com.population;

import com.population.model.Department;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Entry point class for the Population Application. It will read a CSV file passed as argument from the command line.
 * It will parse the file and display all department data sorted by department code. It will also print the department
 * with the lowest number of citizens.
 *
 * @author Romain Richter
 */
public class PopulationApplication {

    public static void main(String[] args) throws IOException {
        PopulationCSVReader csvReader = new PopulationCSVReader();

        // Read and parse departments data from the CSV file
        csvReader.readFromCSV(args[0]);
        HashMap<String, Department> departments = csvReader.getDepartments();

        // Display each department sorted in ascending order of department code
        System.out.println("List of departments:");
        departments.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(s -> s.getValue())
                .forEach(System.out::println);

        System.out.println("Department with the lowest number of citizens:");
        departments.values().stream()
                .min(Comparator.comparingInt(Department::getTotalPopulation))
                .ifPresent(System.out::println);
    }
}
