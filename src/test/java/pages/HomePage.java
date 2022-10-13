package pages;

import org.openqa.selenium.By;
import utils.ActionsUtil;

public class HomePage extends ActionsUtil {
    private final By deliverooForWorkCard = By.cssSelector("div[class^='TrackingCard'] [href$='for-work']");
    private final By giftCardsCard = By.cssSelector("div[class^='TrackingCard'] [href$='gift-cards']");
    private final By careersFooterLink = By.cssSelector("[href='https://careers.deliveroo.co.uk']");

    /**
     * This method is used click on a card at the homepage that have the given name
     *
     * @param card the name of the card to click on
     * @throws Exception in case of not being able to click on the card or it's not found
     */
    public void clickOnCard(String card) throws Exception {
        try {
            switch (card) {
                case "Deliveroo for Work":
                    clickOn(deliverooForWorkCard);
                    break;
                case "Gift Cards":
                    clickOn(giftCardsCard);
                    break;
                default:
                    throw new Exception(String.format("Undefined card: %s", card));
            }
        } catch (Exception e) {
            logError("Failed to click on the Deliveroo For Work card", e);
            throw e;
        }
    }

    /**
     * This method is used to click on any link at the footer given its name
     *
     * @param link the link at the footer to click on
     * @throws Exception in case of the given link is missing or not being able to click on it
     */
    public void clickOnFooterLink(String link) throws Exception {
        switch (link) {
            case "Careers":
                clickOn(careersFooterLink);
                break;
            default:
                throw new Exception(String.format("Undefined footer link: %s", link));
        }
    }
}
