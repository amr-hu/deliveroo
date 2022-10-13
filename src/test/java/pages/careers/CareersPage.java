package pages.careers;

import org.openqa.selenium.By;
import utils.ActionsUtil;

public class CareersPage extends ActionsUtil {
    private final String careerCardLocatorString = "//h3[text()='%s']/..  //p[text()='%s']/.. //span[text()='%s']/..";

    /**
     * This method is used to select a career card based on the given title, location and team
     *
     * @param title    the title of the career
     * @param location the location of the career
     * @param team     the team of the career
     */
    public void clickOnCareerCard(String title, String location, String team) {
        try {
            By careerCardLocator = By.xpath(String.format(careerCardLocatorString, title, location, team));
            clickOn(careerCardLocator);
        } catch (Exception e) {
            logError(String.format("Failed to click on career card with data: %s, %s, %s", title, location, team), e);
            throw e;
        }
    }
}
