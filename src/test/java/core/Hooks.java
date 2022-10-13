package core;

import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import utils.*;

import java.util.Properties;

public class Hooks extends ActionsUtil {
    private final Properties properties = PropertiesFileUtil.getProperties();
    private final By acceptCookiesBtn = By.cssSelector("button#onetrust-accept-btn-handler");

    /**
     * This is a common step that can be used within any feature file to navigate to
     * the homepage of the portal then to accept all the cookies
     */
    @Given("user navigates to the homepage")
    public void user_navigates_to_the_homepage() {
        navigateTo(properties.getProperty("homepageUrl"));
        clickOn(acceptCookiesBtn);
    }

    /**
     * This method is executed before each scenario to log that the scenario is started using its name inside the
     * logs.txt file and add a new entry for the test case to be executed inside the HTML report using its name
     *
     * @param scenario the scenario to be executed
     */
    @Before(order = 0)
    public void before(Scenario scenario) {
        var scenarioName = scenario.getName();
        var report = ExtentReportUtil.getReport();

        log.info("---------------------------------------------------------------");
        log.info(String.format("Test Case Name: %s", scenarioName));
        log.info("Status: Started");
        log.info("---------------------------------------------------------------");

        test = report.startTest(scenarioName);
    }

    /**
     * This method is used to initialize the browser instance before any flow scenario
     *
     * @throws Exception in case of not being able to initialize the browser instance with the desired data
     */
    @Before(value = "@flow", order = 1)
    public void beforeFlow() throws Exception {
        try {
            logInfo("Initializing the Browser instance");
            BrowserUtil.initializeBrowser(properties);
        } catch (Exception e) {
            logError("Exception while trying to initialize the browser instance", e);
            throw e;
        }
    }

    /**
     * This method is executed after each scenario to log the final results of the executed scenario inside both the
     * log file or the HTML report, take a screenshot in case of the test case has failed and save it to the HTML report
     *
     * @param scenario the executed scenario
     * @throws Exception in case of not being able to log the final results of the scenario in either the log file
     *                   or the HTML report or not being able to take a screenshot in case of having failed test case
     */
    @After(order = 1)
    public void after(Scenario scenario) throws Exception {
        logTestRunResults(scenario);
    }

    /**
     * This method is used to close the opened browser instance after any flow scenario
     */
    @After(value = "@flow", order = 0)
    public void afterFlow() {
        BrowserUtil.closeDriver();
    }

    /**
     * This method is executed after each scenario to log the final results of the executed scenario inside both the
     * log file and the HTML report also to take a screenshot in case of the scenario has failed and save it to the
     * HTML report
     *
     * @param scenario the executed scenario
     * @throws Exception in case of not being able to log the final results of the executed scenario in either the log
     *                   file or the HTML report or not being able to take a screenshot in case of having failed
     *                   scenario
     */
    private void logTestRunResults(Scenario scenario) throws Exception {
        var scenarioStatus = scenario.getStatus();
        var scenarioName = scenario.getName();

        try {
            switch (scenarioStatus) {
                case PASSED:
                    logSuccess(String.format("Test Status: %s", scenarioStatus));
                    break;
                case FAILED:
                    var screenshotsPath = properties.getProperty("screenshotsPath");
                    var screenshotFileName = "Screenshot_" + scenarioName + ".png";

                    test.log(LogStatus.ERROR, "Failure Screenshot "
                            + test.addBase64ScreenShot("data:image/png;charset=utf-8;base64,"
                            + ScreenshotUtil.takeScreenshot(driver, screenshotsPath, screenshotFileName)));

                    logFail(String.format("Test Status: %s", scenarioStatus));
                    break;
                case SKIPPED:
                    logSkip(String.format("Test Status: %s", scenarioStatus));
                    break;
                default:
                    logUnknown(String.format("Test Status: %s", scenarioStatus));
                    break;
            }
        } catch (Exception e) {
            logError("Exception while trying to log the executed scenario results", e);
            throw e;
        }
    }
}
