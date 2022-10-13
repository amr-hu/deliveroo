package pages.deliverooForWork;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import utils.ActionsUtil;

public class LandingPage extends ActionsUtil {
    private final By initialFormFrame = By.id("form-iframe");
    private final By firstnameField = By.cssSelector("[title='First name']");
    private final By lastnameField = By.cssSelector("[title='Last name']");
    private final By emailField = By.cssSelector("[title=\"What's your work email?\"]");
    private final By termsCheckbox = By.cssSelector("span [type='checkbox']");
    private final By createAccountBtn = By.id("submit_button");

    /**
     * This method is used to fill the Deliveroo For Work initial form with the given user's data
     *
     * @param userData the data to be added to the form
     */
    public void fillForm(JSONObject userData) {
        try {
            logInfo("Filling the First form");

            switchToTab(1, 2);
            switchToFrame(initialFormFrame);
            addText(firstnameField, (String) userData.get("firstname"));
            addText(lastnameField, (String) userData.get("lastname"));
            addText(emailField, (String) userData.get("email"));
            toggleCheckboxOrRadioBtn(termsCheckbox, true);
            clickOn(createAccountBtn);
        } catch (Exception e) {
            logError("Exception when trying to fill the first form", e);
            throw e;
        }
    }
}
