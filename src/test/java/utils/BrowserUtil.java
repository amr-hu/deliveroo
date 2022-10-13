package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

public class BrowserUtil extends ActionsUtil {
    /**
     * This method is used to initialize the browser instance and the fluent wait to be used during the execution
     *
     * @throws Exception in case of not being able to initialize the browser instance and the fluent wait
     */
    public static void initializeBrowser(Properties properties) throws Exception {
        var timeout = Long.parseLong(properties.getProperty("timeout"));
        var polling = Long.parseLong(properties.getProperty("polling"));
        var browser = properties.getProperty("browser");
        var runHeadless = Boolean.parseBoolean(properties.getProperty("runHeadless"));
        var pageLoadStrategy = getPageLoadStrategy(properties.getProperty("pageLoadStrategy"));

        switch (browser.toLowerCase()) {
            case "chrome":
                Map<String, Object> prefs = new HashMap<>();
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.BROWSER, Level.SEVERE);

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("test-type");
                chromeOptions.addArguments("--js-flags=--expose-gc");
                chromeOptions.addArguments("--enable-precise-memory-info");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-default-apps");
                chromeOptions.addArguments("test-type=browser");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-device-discovery-notifications");
                chromeOptions.setExperimentalOption("excludeSwitches"
                        , Collections.singletonList("enable-automation"));
                chromeOptions.setHeadless(runHeadless);
                chromeOptions.setPageLoadStrategy(pageLoadStrategy);
                chromeOptions.setCapability("perfLoggingPrefs", logPrefs);
                chromeOptions.setAcceptInsecureCerts(true);

                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptions.setExperimentalOption("prefs", prefs);

                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                ProfilesIni profilesIni = new ProfilesIni();
                FirefoxProfile firefoxProfile = profilesIni.getProfile("default");

                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addPreference("dom.webnotifications.enabled", false);
                ffOptions.setProfile(firefoxProfile);
                ffOptions.setLogLevel(FirefoxDriverLogLevel.ERROR);
                ffOptions.setHeadless(runHeadless);
                ffOptions.setPageLoadStrategy(pageLoadStrategy);

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(ffOptions);
                break;
            case "ie":
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setPageLoadStrategy(pageLoadStrategy);

                System.setProperty("webdriver.ie.driver.loglevel", "ERROR");
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(ieOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPageLoadStrategy(pageLoadStrategy);

                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new Exception(String.format("Undefined browser type: '%s'", browser));
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new FluentWait<>(driver).withTimeout(Duration.ofMinutes(timeout))
                .pollingEvery(Duration.ofSeconds(polling)).ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class).ignoring(UnknownError.class);
    }

    /**
     * This method is used to get the Page Load Strategy based on the given strategy as a string
     *
     * @param pageLoadStrategy the page load strategy as a string
     * @return the Page Load Strategy
     * @throws Exception in case of the given strategy string is invalid
     */
    private static PageLoadStrategy getPageLoadStrategy(String pageLoadStrategy) throws Exception {
        switch (pageLoadStrategy.toLowerCase()) {
            case "normal":
                return PageLoadStrategy.NORMAL;
            case "eager":
                return PageLoadStrategy.EAGER;
            case "none":
                return PageLoadStrategy.NONE;
            default:
                throw new Exception(String.format("Invalid Page Load Strategy: '%s'", pageLoadStrategy));
        }
    }

    /**
     * This method is used to completely close the current browser instance
     */
    public static void closeDriver() {
        driver.close();
        driver.quit();
    }
}
