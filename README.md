# Selenium Automation Framework

## Overview

This project is a UI automation testing framework developed using **Java**, **Selenium WebDriver**, **TestNG**, and **Maven**. It automates both **positive** and **negative** test scenarios following the **Page Object Model (POM)** design pattern for better maintainability and scalability.

---

## Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Git & GitHub
- GitHub Actions

---

## Framework Choice

### Java
Java is one of the most widely used programming languages for Selenium automation due to its strong community support and extensive libraries.

### Selenium WebDriver
Used for automating browser interactions across different browsers.

### TestNG
Provides powerful features such as:
- Test annotations
- Test execution control
- Assertions
- Grouping
- Parallel execution support

### Maven
Used for:
- Dependency management
- Build automation
- Easy project maintenance

---

## Framework Structure

```
Selenium-Automation-Framework
│
├── src
│   ├── main
│   │    └── java
│   │         ├── pages
│   │         └── utilities
│   │
│   └── test
│        └── java
│             └── tests
│
├── testng.xml
├── pom.xml
└── README.md
```

---

## Test Scenarios

### Positive Test Cases

- Login with valid username and password
- Verify successful login

### Negative Test Cases

- Invalid username
- Invalid password
- Invalid username and password
- Blank username
- Blank password
- Blank username and password

---

## How to Run

Clone the repository

```bash
git clone https://github.com/<your-username>/<repository-name>.git
```

Navigate to the project

```bash
cd <repository-name>
```

Execute the tests

```bash
mvn clean test
```

---

## GitHub Actions

A GitHub Actions workflow is configured to automatically execute the test suite whenever code is pushed to the **main** branch.

Workflow location:

```
.github/workflows/selenium.yml
```

---

## Extension Plan

### Parallel Execution

Future enhancement includes running tests in parallel using TestNG to reduce execution time.

### Reporting

The framework can be extended with:

- Extent Reports
- Allure Reports

for rich execution reports with screenshots and detailed logs.

### Future Improvements

- Cross-browser testing
- Jenkins CI/CD integration
- Docker execution
- Data-driven testing using Excel/JSON
- Screenshot capture on failure
- Retry failed test cases
- Logging using Log4j

---

## Author

**Navneet Kumar Singh**

Aspiring Software Development Engineer in Test (SDET)

Skills:
- Java
- Selenium WebDriver
- TestNG
- Maven
- SQL
- API Testing
- Git
