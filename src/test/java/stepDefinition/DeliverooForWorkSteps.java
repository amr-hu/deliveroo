package stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import pages.deliverooForWork.LandingPage;
import pages.deliverooForWork.RegistrationFormsPage;
import utils.JsonFileUtil;

public class DeliverooForWorkSteps {
    private final JSONObject testData = (JSONObject) JsonFileUtil.getJsonObject().get("deliverooForWork");
    private final JSONObject usersData = (JSONObject) testData.get("users");
    private final LandingPage landingPage = new LandingPage();
    private final RegistrationFormsPage registrationFormsPage = new RegistrationFormsPage();

    /**
     * This method is used to fill all the forms of the Deliveroo For Work request with the given user's data
     *
     * @param user the key of the user to add his data to the forms
     * @throws Exception in case of not being able to fill the request for or submitting it
     */
    @When("the registration forms are filled with the valid data of {string}")
    public void the_registration_forms_are_filled_with_the_valid_data_of(String user) throws Exception {
        JSONObject userData = (JSONObject) usersData.get(user);

        landingPage.fillForm((JSONObject) userData.get("firstForm"));

        registrationFormsPage.fillSecondForm((JSONObject) userData.get("secondForm"))
                .fillThirdForm((JSONObject) userData.get("thirdForm"))
                .fillFourthForm((JSONObject) userData.get("fourthForm"))
                .fillFifthForm((JSONObject) userData.get("fifthForm"))
                .submitForms((boolean) ((JSONObject) userData.get("sixthForm")).get("acceptTerms"));
    }

    /**
     * This method is used to verify that the Deliveroo For Work request has been successfully completed
     */
    @Then("the request should be successfully completed")
    public void the_request_should_be_successfully_completed() {
        registrationFormsPage.verifyThatRegistrationIsCompleted();
    }
}
