package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.ExtentReportUtil;
import utils.JsonFileUtil;
import utils.Log4jUtil;
import utils.PropertiesFileUtil;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/resources/feature/", glue = {"stepDefinition", "core"}, tags = "not @ignore"
        , plugin = {"pretty", "json:target/cucumber-reports/report.json", "rerun:target/cucumber-reports/rerun.txt"})
public class TestRunner {
    private final static String propertiesFilePath = "./src/test/resources/configurations/config.properties";

    /**
     * This method is executed before all test scenarios to extract the run properties then to extract the test data
     * from its file, initialize the html report and load the Log4j configurations
     *
     * @throws Exception in case of facing an issue while initializing any of the mentioned above
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        var properties = PropertiesFileUtil.getProperties(propertiesFilePath);

        Log4jUtil.loadConfigurations(properties.getProperty("log4jConfigPath"));
        JsonFileUtil.setJsonObject(properties.getProperty("testDataFilePath"));
        ExtentReportUtil.initializeHtmlReport(properties.getProperty("htmlReportPath")
                , properties.getProperty("htmlReportConfigPath"));
    }

    /**
     * This method is executed after all scenarios to flush the html report with all the executed scenarios results
     */
    @AfterClass
    public static void afterClass() {
        ExtentReportUtil.flushReport();
    }
}
