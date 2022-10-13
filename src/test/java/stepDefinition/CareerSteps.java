package stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.careers.ApplicationConfirmationPage;
import pages.careers.CareerApplicationPage;
import pages.careers.CareersPage;

public class CareerSteps {
    private final CareersPage careersPage = new CareersPage();
    private final CareerApplicationPage careerApplicationPage = new CareerApplicationPage();
    private final ApplicationConfirmationPage applicationConfirmationPage = new ApplicationConfirmationPage();

    /**
     * This method is used to select a career card based on the given title, location and team
     *
     * @param title    the title of the career
     * @param location the location of the career
     * @param team     the team of the career
     */
    @When("user clicks on career card with {string} at {string} in {string}")
    public void user_clicks_on_career_card_with(String title, String location, String team) {
        careersPage.clickOnCareerCard(title, location, team);
    }

    /**
     * This method is used to fill the career application with the data of the given used
     *
     * @param user the user to apply for the career
     * @throws Exception in case of not being able to fill the application or submitting it
     */
    @When("fills the application form with {string} data")
    public void fills_the_application_form_with_data(String user) throws Exception {
        careerApplicationPage.submitApplication(user);
    }

    /**
     * This method is used to verify that the submitted career application has been successfully completed
     */
    @Then("verify that the application has been successfully submitted")
    public void verify_that_the_application_has_been_successfully_submitted() {
        applicationConfirmationPage.verifyThatApplicationIsSuccessfullySubmitted();
    }
}
