package com.population.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Entity representing a Department. A department is composed of several cities.
 *
 * @author Romain Richter
 */
public class Department {
    /**
     * The code of the department (ex: 06, 2A...)
     */
    private String code;
    /**
     * The name of the department (ex: Alpes-Maritimes, Alsace, Lorraine...)
     */
    private String name;
    /**
     * The list of cities being part of the department
     */
    private List<City> cities;
    /**
     * The total number of people living in the department
     */
    private int totalPopulation;

    /**
     * Creates a Department with its code and name
     *
     * @param code The code of the department (ex: 06, 2A...)
     * @param name The name of the department (ex: Alpes-Maritimes, Alsace, Lorraine...)
     */
    public Department(String code, String name) {
        this.code = code;
        this.name = name;
        cities = new ArrayList<>();
        totalPopulation = 0;
    }

    /**
     * Change the code of the department
     *
     * @param code the new code of the departement. Must not be null or empty.
     */
    public void setCode(String code) {
        if (code != null && code.length() > 0) {
            this.code = code;
        }
    }

    /**
     * Retrieve the code of the department
     *
     * @return A string representing the code of the department
     */
    public String getCode() {
        return code;
    }

    /**
     * Change the name of the department
     *
     * @param name The new name of the department. Must not be null or empty.
     *
     */
    public void setName(String name) {
        if (name != null && name.length() > 0) {
            this.name = name;
        }
    }

    /**
     * Retrieve the name of the department
     *
     * @return A string representing the name of the department
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve all the cities being part of the department
     *
     * @return A list of cities of the department
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Add a new city to the department.
     *
     * @param city The city to be added to the department
     * @return boolean asserting whether the city has been correctly added or not.
     *         If the city already exists, it won't be added.
     */
    public boolean addCity(City city) {
        if (cities.contains(city)) {
            return false;
        }
        if (cities.add(city)) {
            totalPopulation += city.getPopulation();
            return true;
        }
        return false;
    }

    /**
     * Remove a city from the department.
     *
     * @param city The city to be removed from the department.
     * @return boolean asserting whether the city has been removed or not from the department.
     */
    public boolean removeCity(City city) {
        if (cities.remove(city)) {
            totalPopulation -= city.getPopulation();
            return true;
        }
        return false;
    }

    /**
     * Get the total number of people living in the department
     *
     * @return An integer indicating the number of people in the department
     */
    public int getTotalPopulation() {
        return totalPopulation;
    }

    /**
     * Retrieve the city having the highest number of people living in it.
     *
     * @return The largest city of the department
     */
    public Optional<City> getLargestCity() {
        return cities.stream()
                .max(Comparator.comparingInt(City::getPopulation));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return (totalPopulation == that.totalPopulation) && Objects.equals(code, that.code)
                && Objects.equals(name, that.name) && Objects.equals(cities, that.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, cities, totalPopulation);
    }

    @Override
    public String toString() {
        AtomicReference<String> str = new AtomicReference<>("Department{" +
                "name='" + name + '\'' +
                ", totalPopulation='" + totalPopulation + '\'');
        Optional<City> largestCity = getLargestCity();
        largestCity.ifPresent(c -> str.updateAndGet(s -> s + ", largestCity='" + c + '\''));
        str.updateAndGet(v -> v + '}');
        return str.get();
    }
}
