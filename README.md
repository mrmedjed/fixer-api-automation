# Fixer API Automation Project

This is a basic framework for Fixer API automation project using Java, Rest Assured, and Cucumber.

## Setup

1. Install [Maven](https://maven.apache.org/install.html).
2. Clone this repository.
3. Navigate to the project directory and run `mvn clean install` to download the dependencies.

## Running Tests

To run the tests, use the following command:
- mvn test


## Project Structure

- `src/main/java/api/helpers`: Contains helper classes for API interactions.
- `src/main/java/api/models`: Contains model classes for the API responses.
- `src/main/java/api/resources`: Contains config properties and other resources.
- `src/main/java/api/utils`: Contains utilization classes.
- `src/test/java/api/stepdefinitions`: Contains step definitions for Cucumber.
- `src/test/java/api/runners`: Contains the test runner class.
- `src/test/java/api/features`: Contains the feature files for Cucumber.

## Dependencies

- [Rest Assured](http://rest-assured.io/): For API testing.
- [Cucumber](https://cucumber.io/): For BDD style testing.
- [JUnit](https://junit.org/junit4/): For running the tests.
- [Jackson](https://github.com/FasterXML/jackson): For JSON processing.
