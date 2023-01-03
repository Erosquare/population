package com.population.model;

import java.util.Objects;

/**
 * Entity representing a City. A city is part of a department.
 *
 * @author Romain Richter
 */
public class City {
    /**
     * The name of the city (ex: Nice, Paris...)
     */
    private String name = "";
    /**
     * The number of people living in the city
     */
    private int population = 0;

    /**
     * Change the name of the city
     * @param name The new name of the city. Cannot be null or empty.
     */
    public void setName(String name) {
        if (name != null && name.length() > 0) {
            this.name = name;
        }
    }

    /**
     * Retrieve the name of the city
     * @return A string representing the name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * Change the number of people living in the city
     *
     * @param population The population of the city. If the value is negative, it is set to 0.
     */
    public void setPopulation(int population) {
        if (population < 0) {
            population = 0;
        }
        this.population = population;
    }

    /**
     * Retrieve the current population of the city
     *
     * @return An integer representing the current population of the city
     */
    public int getPopulation() {
        return population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(name, city.name) && Objects.equals(population, city.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, population);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
