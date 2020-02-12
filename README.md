## Description

This is a test project for web automation task. This project tests Login, Hover and Table sorting at [the-internet.herokuapp.com](http://the-internet.herokuapp.com).

### Technology

```
WebDriver, Java 1.8, JUnit, Maven, AssertJ
```
### Pattern

Page Object Model

### Prerequisites

In order to run tests the following is required:
  * [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
  * Google Chrome
  * [Maven](http://maven.apache.org/install.html)

### Run tests

To run Test Suite execute `mvn test`

After each test/suite execution `Test Execution Summary` will be added into logs/report.html

If you want [surefire report](http://maven.apache.org/surefire/maven-surefire-report-plugin/usage.html) to be generated execute `mvn clean surefire-report:report`
