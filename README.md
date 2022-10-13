# deliveroo

deliveroo is a Java testing framework built with BDD concept using cucumber to cover UI scenarios

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for testing purposes

## Concepts Included

* Testing Framework design
* Page Object pattern
* Page
* BDD
* Cucumber
* JUnit
* Gherkin Language
* Common web page interaction methods
* Extracting test data from json file
* Html reports for testing outputs
* Screenshots for the testing outputs

## Tools

* Maven
* Selenium WebDriver
* Cucumber
* JUnit

## Requirements

In order to utilize this project, you need to have the following installed locally:

* Maven
* Chrome Browser
* Java Compiler 11
* Cucumber

## Installing

* Install Java
* Set the System Environment variables with JDK and JRE paths
* Install Eclipse or IntelliJ
* Install Cucumber inside the IDE if not configured by default
* Install Maven inside the IDE if not configured by default
* Install Git
* Clone the project from `https://github.com/amr-hu/deliveroo.git`

## Framework structure

* `careers.feature` in the directory `src/test/resources/feature/` holds the steps of the scenarios related to applying
  for Careers
* `deliverooForWork.feature` in the directory `src/test/resources/feature/` holds the steps of the scenarios related to
  applying for Deliveroo For Work
* `TestRunner.java` in the directory `src/test/java/runner` holds the one method to run before all the scenarios and
  another method to run after all the scenarios
* `Hooks.java` in the directory `src/test/java/stepDefinition/core/` holds the methods that will be executed either
  after or before each scenario
* `ApplicationConfirmationPage.java` in the directory `src/test/java/pages/careers/` holds a method to verify that the
  career application has been successfully submitted
* `CareerApplicationPage.java` in the directory `src/test/java/pages/careers/` holds the methods used to fill the career
  application form
* `CareersPage.java` in the directory `src/test/java/pages/careers/` holds the methods of applying to a career
* `LandingPage.java` in the directory `src/test/java/pages/deliverooForWork/` holds the methods of starting the module
  request submission
* `RegistrationFormsPage.java` in the directory `src/test/java/pages/deliverooForWork/` holds the methods of filling the
  module request forms
* `HomePage.java` in the directory `src/test/java/pages/` holds the methods of interacting with the homepage elements
* `CareerSteps.java` in the directory `src/test/java/stepDefinition/` holds the performed steps while applying to a new
  career
* `DeliverooForWorkSteps.java` in the directory `src/test/java/stepDefinition/` holds the performed steps while applying
  for Deliveroo for Work
* `HomepageSteps.java` in the directory `src/test/java/stepDefinition/` holds the performed stepa while interacting with
  the homepage elements
* `ActionsUtil.java` in the directory `src/test/java/utils/` holds the helper methods that can be used during the
  execution of any UI scenario
* `BrowserUtil.java` in the directory `src/test/java/utils/` holds the helper methods for handling the browser instance
  while running
* `ExtentReportUtil.java` in the directory `src/test/java/utils/` holds the methods used to initialize the Html report
  that will be used to collect the executions results
* `JsonFileUtil.java` in the directory `src/test/java/utils/` holds the methods of handling the json files
* `Log4jUtil.java` in the directory `src/test/java/utils/` holds a method to load the configurations of the logs file
* `LoggerUtil.java.java` in the directory `src/test/java/utils/` holds methods for logging the steps to both log files
  and html report
* `PropertiesFileUtil.java` in the directory `src/test/java/utils/` holds a method to read any properties file
* `ScreenshotUtil.java` in the directory `src/test/java/utils/` holds a method to take a screenshot from the current
  view
* `config.properties` in the directory `src/test/resources/configurations/` holds the properties that will be used
  during the execution
* `log4j.xml` in the directory `src/test/resources/configurations/` holds the configurations of the logger that will be
  used during the execution
* `extent-config.xml` in the directory `src/test/resources/configurations/` holds the configurations of the html report
  that will hold all the results of the executed scenarios
* `src/test/resources/test-data/` the directory that holds the files used as test data during the execution of the
  scenarios

## Running the tests

* You can run all the scenarios either by building the project as Maven project and all the scenarios will be
  automatically executed after the compilation process, or you can run the `TestRunner.java` as a JUnit class
* In case you want to run specific scenario(s), you will have to set its tag in the `TestRunner.java`

## Running workflow description

* The execution begins from the method under the annotation `@BeforeClass` inside `TestRunner.java` and it does the
  following
    * Extract the properties from the `config.properties` that will be used during the execution
    * Extract the test data from `data.json` that will be used during the execution
    * Initialize the HTML Extent Report using its `extent-config.xml` file
    * Initialize the Logger using its `log4j.xml` file
* The method under the annotation `@Before(order = 0)` inside `Hooks.java` is called to add an entry for the scenario to
  be executed inside the html report
* If the scenario to be executed has the `@flow` annotation inside its feature file, then the method under the
  annotation `@Before(value = "@flow", order = 1)` inside `Hooks.java` is called to initialize a browser instance based
  on the extracted properties
* Then the first scenario is executed and each executed step is logged inside the html report, the log file, the json
  logs file and the text logs file
* After executing all the scenario steps, the method under the annotation `@After(order = 1)` inside `Hooks.java` is
  called to log the final results of the scenario inside the reporting also to take screenshot for the scenario if
  failed
* If the executed scenario has the `@flow` annotation inside its feature file, then the method under the
  annotation `@After(value = "@flow", order = 0)` inside `Hooks.java` is called to close the running browser instance
* If the execution contains more than one scenario, they will be executed sequentially based on their order
* Finally, the method under the annotation `@AfterClass` inside `TestRunner.java` is called to flush the data of the
  executed scenario(s) inside the html report

## Reporting

After the run is done, the test outputs can be found in the directory `target/cucumber-reports/` which contains

* `screenshots/` folder which contains screenshots for all the failed scenarios if any
* `logs.log` a log file that has the executed scenarios steps
* `report.html` an html report that has all the scenarios steps along with their screenshots if any
* `report.json` a json report generated by the runner to hold all the executed scenarios steps
* `report.txt` a text report generated by the runner to hold all the executed scenarios steps

## Notes

* Make sure that the Java Compiler used during the run is `11` or higher
* In case you face any compiler errors, make sure that the `Language Level` and the `Target bytecode version` of the
  project are set to `11`
* The `ActionsUtil.java` class contains only the helper methods that were called during the scenarios, but it can be
  modified anytime to have more helper methods
* All the scenarios to be executed should not have the tag `@ignore`