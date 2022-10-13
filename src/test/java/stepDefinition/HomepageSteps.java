package stepDefinition;

import io.cucumber.java.en.Given;
import pages.HomePage;
import utils.ActionsUtil;

public class HomepageSteps extends ActionsUtil {
    private final HomePage homepage = new HomePage();

    /**
     * This method is used to click on a card with the given name in the homepage
     *
     * @param card the card to click on
     * @throws Exception in case the given card is missing or not being able to click on it
     */
    @Given("clicks on {string} card")
    public void clicks_on_card(String card) throws Exception {
        homepage.clickOnCard(card);
    }

    /**
     * This method is used to click on the given link at the footer
     *
     * @param link the link to click on
     * @throws Exception in case the given link is missing or not being able to click on it
     */
    @Given("clicks on the {string} link at the footer")
    public void clicks_on_the_link_at_the_footer(String link) throws Exception {
        homepage.clickOnFooterLink(link);
    }
}
