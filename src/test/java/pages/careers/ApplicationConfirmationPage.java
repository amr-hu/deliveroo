package pages.careers;

import org.openqa.selenium.By;
import utils.ActionsUtil;

public class ApplicationConfirmationPage extends ActionsUtil {
    private final By confirmationMessage = By.id("application_confirmation");

    /**
     * This method is used to verify that the submitted career application has been successfully completed
     */
    public void verifyThatApplicationIsSuccessfullySubmitted() {
        waitForElementVisibility(confirmationMessage);
    }
}
