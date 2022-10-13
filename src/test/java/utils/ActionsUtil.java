package utils;

import org.jsoup.helper.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ActionsUtil extends LoggerUtil {
    protected static WebDriver driver;
    protected static FluentWait<WebDriver> wait;
    protected final String testDataFolder = System.getProperty("user.dir")
            + PropertiesFileUtil.getProperties().getProperty("testDataFolder");
    private JavascriptExecutor executor;

    /**
     * This method is used to navigate to the given URL
     *
     * @param url the URL to navigate to
     */
    protected void navigateTo(String url) {
        try {
            logInfo(String.format("Navigating to '%s'", url));
            driver.get(url);
        } catch (Exception e) {
            logError("Exception while navigating to the desired URL", e);
            throw e;
        }
    }

    /**
     * This method is used to add the given text to an element identified by the given locator after being cleared
     * in case the given text is not null or empty
     *
     * @param locator the locator of the element to add the text inside
     * @param text    the text to be added inside the element
     */
    protected void addText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();

        if (!StringUtil.isBlank(text))
            element.sendKeys(text);
    }

    /**
     * This method is used to add the given text to the given WebElement after being cleared
     *
     * @param element the element to add text to
     * @param text    the text to be added to the given element
     */
    protected void addText(WebElement element, String text) {
        element.clear();

        if (!StringUtil.isBlank(text))
            element.sendKeys(text);
    }

    /**
     * This method is used to click on the given element locator
     *
     * @param locator       the locator of the element to click on
     * @param useJavascript a flag to indicate the behavior of the click, if it's true, it will use the Javascript
     *                      executor to perform the action otherwise it will use the normal selenium click
     */
    protected void clickOn(By locator, boolean useJavascript) {
        wait.until(d -> {
            try {
                WebElement element = driver.findElement(locator);

                if (useJavascript) {
                    executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", element);
                } else {
                    element.click();
                }

                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    /**
     * This method is used to click on the given element locator
     *
     * @param locator the locator of the element to click on
     */
    protected void clickOn(By locator) {
        this.clickOn(locator, false);
    }

    /**
     * This method is used to click on the given WebElement
     *
     * @param element       the element to click on
     * @param useJavascript if true, the element will be clicked using the JavascriptExecutor otherwise, normal click
     */
    protected void clickOn(WebElement element, boolean useJavascript) {
        if (useJavascript) {
            executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } else {
            element.click();
        }
    }

    /**
     * This method is used to set the status of the given checkbox or radio button to the given one
     *
     * @param locator the locator of the checkbox to toggle its status
     * @param status  the desired status for the given checkbox
     */
    protected void toggleCheckboxOrRadioBtn(By locator, boolean status) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        wait.until(d -> {
            if (element.isSelected() != status)
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            return element.isSelected() == status;
        });
    }

    /**
     * This method is used to set the status of the given checkbox or radio button to the given one
     *
     * @param element the checkbox or radio button to set its statue
     * @param status  the expected status of the given checkbox or radio button
     */
    protected void toggleCheckboxOrRadioBtn(WebElement element, boolean status) {
        wait.until(d -> {
            if (element.isSelected() != status)
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            return element.isSelected() == status;
        });
    }

    /**
     * This method is used to wait for an element to be visible given its locator
     *
     * @param locator the locator of element to wait for its visibility
     */
    protected void waitForElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * This method is used to select an option by its visible text from a list based on its given locator
     *
     * @param listLocator the locator of the list to select the option from
     * @param text        the option's text to select from the list
     */
    public void selectByText(By listLocator, String text) {
        WebElement list = wait.until(ExpectedConditions.presenceOfElementLocated(listLocator));
        new Select(list).selectByVisibleText(text);
    }

    /**
     * This method is used to select an option by its visible text from the given list
     *
     * @param list the list to select the option from
     * @param text the option's text to select from the list
     */
    public void selectByText(WebElement list, String text) {
        new Select(list).selectByVisibleText(text);
    }

    /**
     * This method is used to select options by their visible texts from the given list
     *
     * @param list    the list to select the options from
     * @param options the options' texts to select from the list
     */
    public void selectByText(WebElement list, List<String> options) {
        Select select = new Select(list);

        for (String option : options) {
            select.selectByVisibleText(option);
        }
    }

    /**
     * This method is used to switch to a frame given its locator
     *
     * @param frame the locator of the frame to switch to
     */
    public void switchToFrame(By frame) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    /**
     * This method is used to verify that the number of the current tabs
     * matches the given one then to switch to the tab with the given index
     *
     * @param tabIndex   the index of the desired tab to switch to
     * @param tabsNumber the expected tabs number
     */
    public void switchToTab(int tabIndex, int tabsNumber) {
        var windows = new ArrayList<>(driver.getWindowHandles());
        wait.until(ExpectedConditions.numberOfWindowsToBe(tabsNumber));
        driver.switchTo().window(windows.get(tabIndex));
    }

    /**
     * This method is used to verify that the numbers of elements that have the given locator are greater that or
     * equal to the given number
     *
     * @param locator the common locator of the desired elements
     * @param number  the min number of elements that should be found
     * @return list of all the elements that have the given locator are greater that or
     */
    @SuppressWarnings("unchecked")
    public List<WebElement> verifyNumberOfElementsToBeMoreThanOrEqual(By locator, int number) {
        return (List<WebElement>) wait.until(d -> {
            List<WebElement> webElements = driver.findElements(locator);
            if (webElements.size() >= number) return webElements;
            return false;
        });
    }

    /**
     * This method is used to find an elements with the given locator and index
     *
     * @param locator the locator of the desired element
     * @param index   the index of the desired element
     * @return the desired element
     */
    public WebElement findElement(By locator, int index) {
        List<WebElement> elements = verifyNumberOfElementsToBeMoreThanOrEqual(locator, index + 1);
        return elements.get(index);
    }

    /**
     * This method is used to add a file to an uploader
     *
     * @param uploader   the locator of the uploader
     * @param folderPath the path of the folder that has the file
     * @param fileName   the name of the file to be uploaded
     */
    public void uploadFile(WebElement uploader, String folderPath, String fileName) {
        if (!StringUtil.isBlank(fileName))
            uploader.sendKeys(folderPath + fileName);
    }

    /**
     * This method is used to add a file to an uploader
     *
     * @param uploader   the uploader to add the file to
     * @param folderPath the path of the folder that has the file
     * @param fileName   he name of the file to be uploaded
     */
    public void uploadFile(By uploader, String folderPath, String fileName) {
        if (!StringUtil.isBlank(fileName))
            wait.until(ExpectedConditions.presenceOfElementLocated(uploader))
                    .sendKeys(folderPath + fileName);
    }
}
