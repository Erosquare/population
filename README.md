# Population Application

This application reads CSV files that contain population of cities that belong to departments and prints the information
of each department. A CSV file that contains the population of some French cities is available in the "samples" folder. 

## Requirements
To run and compile this project, you need the following prerequisites:

- OpenJDK 11
- Maven 3.x

## Compile the application
To clean and compile the application, go at the root folder of projet the type the following command:
```bash
mvn clean compile
```

## Run the Unit Tests
To run the unit tests, type the following command after compiling:
```bash
mvn test
``` 
Or to compile and run the tests at once:
```bash
mvn clean compile test
``` 

## Run the sample application
To run the program with the CSV file provided, run the following command at the root of the project after compilation:
```bash
mvn exec:java -Dexec.args="samples/population_2019_500.csv"
```
To compile and run the application at the same time:
```bash
mvn clean compile exec:java -Dexec.args="samples/population_2019_500.csv"
```
