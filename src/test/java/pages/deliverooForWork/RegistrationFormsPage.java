package pages.deliverooForWork;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import utils.ActionsUtil;

import java.util.List;

public class RegistrationFormsPage extends ActionsUtil {
    private final By formFrame = By.id("deliverooIframe");
    private final By firstnameField = By.cssSelector("[title='First name']");
    private final By lastnameField = By.cssSelector("[title='Last name']");
    private final By companyNameField = By.cssSelector("[title='Company name']");
    private final By phoneNumberField = By.cssSelector("[title='Phone number']");
    private final By rolesList = By.cssSelector("[title=\"Which best describes your role?\"]");
    private final By secondStepNextBtn = By.cssSelector("[id^='wfPgIndex'] #wfPageNextId1");
    private final By companyWebsiteField = By.cssSelector("[title='Company website']");
    private final By teamSizeList = By.cssSelector("[title='Team size']");
    private final By industryList = By.cssSelector("[title='Industry']");
    private final By joiningReasonList = By.cssSelector("[title='Main reason for using us']");
    private final By cityField = By.cssSelector("[title='City']");
    private final By thirdStepNextBtn = By.cssSelector("[id^='wfPgIndex'] #wfPageNextId2");
    private final By allowanceGroupNameField = By.cssSelector("[title='Allowance group name']");
    private final By amountField = By.cssSelector("[title='Amount (GBP)']");
    private final By frequencyList = By.cssSelector("[title='Frequency']");
    private final By startTimeList = By.cssSelector("[title='Start time']");
    private final By endTimeList = By.cssSelector("[title='End time']");
    private final By daysList = By.cssSelector("select[id^='tfa_5054']");
    private final By addTimeSlotButton = By.linkText("Add another time slot");
    private final By membersManualRadioBtn = By.cssSelector("input[id^='tfa_5066']");
    private final By membersUploadRadioBtn = By.cssSelector("input[id^='tfa_5067']");
    private final By membersFileUploader = By.cssSelector("[title='CSV file']");
    private final By memberFirstnameField = By.cssSelector("input[id^='tfa_5070']");
    private final By memberLastnameField = By.cssSelector("input[id^='tfa_5071']");
    private final By memberEmailField = By.cssSelector("input[id^='tfa_5073']");
    private final By memberIdField = By.cssSelector("input[id^='tfa_5076']");
    private final By memberDepartmentCodeField = By.cssSelector("input[id^='tfa_5093']");
    private final By addNewMemberBtn = By.linkText("Add another team member");
    private final By addAllowanceGroupBtn = By.linkText("Add another allowance group");
    private final By fourthStepNextBtn = By.cssSelector("[id^='wfPgIndex'] #wfPageNextId3");
    private final By vatNumberField = By.cssSelector("[title='VAT number']");
    private final By companyRegistrationNumberField = By.cssSelector("[title='Company registration number']");
    private final By registrationUploader = By.cssSelector("input#tfa_5213");
    private final By firstLineOfAddressField = By.cssSelector("[title='First line of address']");
    private final By createBillingCheckbox = By.cssSelector("input#tfa_5196");
    private final By billNameField = By.cssSelector("input#tfa_4860");
    private final By billEmailField = By.cssSelector("input#tfa_4868");
    private final By billPhoneField = By.cssSelector("input#tfa_4870");
    private final By billAdditionalNotesField = By.cssSelector("textarea#tfa_4887");
    private final By addressCityField = By.cssSelector("input#tfa_4874");
    private final By postcodeField = By.cssSelector("[title='Postcode']");
    private final By fifthStepNextBtn = By.cssSelector("[id^='wfPgIndex'] #wfPageNextId4");
    private final By termsCheckbox = By.id("tfa_4892");
    private final By submitBtn = By.id("submit_button");
    private final By thanksForm = By.cssSelector("[data-testid='thank-you-page']");

    /**
     * This method is used to fill the Deliveroo For Work second form with the given user's data
     *
     * @param userData the data to be added to the form
     * @return the current instance
     */
    public RegistrationFormsPage fillSecondForm(JSONObject userData) {
        try {
            logInfo("Filling the Second form");

            switchToTab(2, 3);
            switchToFrame(formFrame);
            addText(firstnameField, (String) userData.get("firstname"));
            addText(lastnameField, (String) userData.get("lastname"));
            addText(companyNameField, (String) userData.get("company"));
            addText(phoneNumberField, (String) userData.get("phone"));
            selectByText(rolesList, (String) userData.get("role"));
            clickOn(secondStepNextBtn);

            return this;
        } catch (Exception e) {
            logError("Exception while trying to fill the Second form", e);
            throw e;
        }
    }

    /**
     * This method is used to fill the Deliveroo For Work third form with the given user's data
     *
     * @param userData the data to be added to the form
     * @return the current instance
     */
    public RegistrationFormsPage fillThirdForm(JSONObject userData) {
        try {
            logInfo("Filling the Third form");

            addText(companyWebsiteField, (String) userData.get("website"));
            selectByText(teamSizeList, (String) userData.get("teamSize"));
            selectByText(industryList, (String) userData.get("industry"));
            selectByText(joiningReasonList, (String) userData.get("joiningReason"));
            addText(cityField, (String) userData.get("city"));
            clickOn(thirdStepNextBtn);

            return this;
        } catch (Exception e) {
            logError("Exception when trying to fill the Third form", e);
            throw e;
        }
    }

    /**
     * This method is used to fill the Deliveroo For Work fourth form with the given user's data
     *
     * @param userData the data to be added to the form
     * @return the current instance
     * @throws Exception in case of not being able to fill or submit the form
     */
    @SuppressWarnings("unchecked")
    public RegistrationFormsPage fillFourthForm(JSONObject userData) throws Exception {
        try {
            logInfo("Filling the Fourth form");

            List<JSONObject> allowanceGroups = (List<JSONObject>) userData.get("allowanceGroups");
            addAllowanceGroups(allowanceGroups);
            clickOn(fourthStepNextBtn, true);

            return this;
        } catch (Exception e) {
            logError("Exception when trying to fill the Fourth form", e);
            throw e;
        }
    }

    /**
     * This method is used to add the desired allowance groups to the Deliveroo For Work fourth form
     *
     * @param allowanceGroups the allowance groups to be added to the form
     * @throws Exception in case of not being able to add the allowance groups to the form
     */
    @SuppressWarnings("unchecked")
    private void addAllowanceGroups(List<JSONObject> allowanceGroups) throws Exception {
        int currentSlotsCount = 0;

        for (int i = 0; i < allowanceGroups.size(); i++) {
            JSONObject allowanceGroup = allowanceGroups.get(i);
            List<JSONObject> timeSlots = (List<JSONObject>) allowanceGroup.get("timeSlots");
            JSONObject teamMembers = (JSONObject) allowanceGroup.get("teamMembers");
            currentSlotsCount += timeSlots.size();

            if (i > 0) clickOn(addAllowanceGroupBtn, true);
            addText(findElement(allowanceGroupNameField, i), (String) allowanceGroup.get("name"));
            addText(findElement(amountField, i), (String) allowanceGroup.get("amount"));
            selectByText(findElement(frequencyList, i), (String) allowanceGroup.get("frequency"));
            addTimeSlots(timeSlots, currentSlotsCount, i);
            addTeamMembers(teamMembers, i);
        }
    }

    /**
     * This method is used to add the given time slots to an allowance group with the given index
     *
     * @param timeSlots           the time slots to be added to the allowance group
     * @param currentSlotsCount   the current number of the added times slots
     * @param allowanceGroupIndex the index of the allowance group to add the time slots in
     */
    @SuppressWarnings("unchecked")
    private void addTimeSlots(List<JSONObject> timeSlots, int currentSlotsCount, int allowanceGroupIndex) {
        int newSlotsCount = timeSlots.size();

        for (int i = 0; i < newSlotsCount; i++) {
            JSONObject timeSLot = timeSlots.get(i);
            int index = currentSlotsCount - newSlotsCount + i;

            if (i > 0) clickOn(findElement(addTimeSlotButton, allowanceGroupIndex), true);
            selectByText(findElement(startTimeList, index), (String) timeSLot.get("startTime"));
            selectByText(findElement(endTimeList, index), (String) timeSLot.get("endTime"));
            selectByText(findElement(daysList, index), (List<String>) timeSLot.get("days"));
        }
    }

    /**
     * This method is used to add team members to an allowance group with the given index either manually
     * or through CSV file
     *
     * @param teamMembers         the data of the members to be added
     * @param allowanceGroupIndex the index of the allowance group to add members to
     * @throws Exception in case of not being able to add the members to the desired allowance group
     */
    @SuppressWarnings("unchecked")
    private void addTeamMembers(JSONObject teamMembers, int allowanceGroupIndex) throws Exception {
        String uploadType = (String) teamMembers.get("uploadType");
        int formIndex = Integer.parseInt((String) teamMembers.get("index"));

        if (uploadType.equalsIgnoreCase("manual")) {
            toggleCheckboxOrRadioBtn(findElement(membersManualRadioBtn, allowanceGroupIndex), true);
            addMemberManually((List<JSONObject>) teamMembers.get("members"), formIndex);
        } else if (uploadType.equalsIgnoreCase("file")) {
            toggleCheckboxOrRadioBtn(findElement(membersUploadRadioBtn, allowanceGroupIndex), true);
            uploadFile(findElement(membersFileUploader, formIndex), testDataFolder, (String) teamMembers.get("file"));
        } else {
            throw new Exception(String.format("Undefined members upload method: %s", uploadType));
        }
    }

    /**
     * This method is used to add team members manually to the last allowance group
     *
     * @param members   the members to be added to the desired allowance group
     * @param formIndex the index of the member addition form compared to other same forms
     */
    private void addMemberManually(List<JSONObject> members, int formIndex) {
        for (int i = 0; i < members.size(); i++) {
            JSONObject member = members.get(i);
            int membersIndex = Integer.parseInt((String) member.get("index"));

            if (i > 0) clickOn(findElement(addNewMemberBtn, formIndex), true);
            addText(findElement(memberFirstnameField, membersIndex), (String) member.get("firstname"));
            addText(findElement(memberLastnameField, membersIndex), (String) member.get("lastname"));
            addText(findElement(memberEmailField, membersIndex), (String) member.get("email"));
            addText(findElement(memberIdField, membersIndex), (String) member.get("id"));
            addText(findElement(memberDepartmentCodeField, membersIndex), (String) member.get("departmentCode"));
        }
    }

    /**
     * This method is used to fill the Deliveroo For Work fifth form with the given user's data
     *
     * @param userData the data to be added to the form
     * @return the current instance
     */
    public RegistrationFormsPage fillFifthForm(JSONObject userData) {
        try {
            logInfo("Filling the Fifth form");

            addText(vatNumberField, (String) userData.get("vatNumber"));
            addText(companyRegistrationNumberField, (String) userData.get("companyRegistrationNumber"));
            uploadFile(registrationUploader, testDataFolder, (String) userData.get("companyRegistrationFile"));
            toggleCheckboxOrRadioBtn(createBillingCheckbox, (boolean) userData.get("createBillingCheckbox"));
            addBillData((JSONObject) userData.get("billData"));
            addText(firstLineOfAddressField, (String) userData.get("firstAddress"));
            addText(addressCityField, (String) userData.get("city"));
            addText(postcodeField, (String) userData.get("postcode"));
            clickOn(fifthStepNextBtn, true);

            return this;
        } catch (Exception e) {
            logError("Exception when trying to fill the Fifth form", e);
            throw e;
        }
    }

    /**
     * This method is used to add the bill data to the Deliveroo For Work fifth form if needed
     *
     * @param billData the data of the bill to be added to the form
     */
    private void addBillData(JSONObject billData) {
        if (billData != null) {
            addText(billNameField, (String) billData.get("name"));
            addText(billEmailField, (String) billData.get("email"));
            addText(billPhoneField, (String) billData.get("phone"));
            addText(billAdditionalNotesField, (String) billData.get("notes"));
        }
    }

    /**
     * This method is used to fill the Deliveroo For Work sixth form then submit the complete request
     *
     * @param acceptTerms the status of the terms' acceptance checkbox whether it needs to be checked or not
     */
    public void submitForms(boolean acceptTerms) {
        try {
            logInfo("Submitting the request");
            toggleCheckboxOrRadioBtn(termsCheckbox, acceptTerms);
            clickOn(submitBtn, true);
        } catch (Exception e) {
            logError("Exception when trying to submit the forms", e);
            throw e;
        }
    }

    /**
     * This method is used to verify that the Deliveroo For Work request has been successfully completed
     */
    public void verifyThatRegistrationIsCompleted() {
        try {
            waitForElementVisibility(thanksForm);
        } catch (Exception e) {
            logError("The submitted request has not been completed", e);
            throw e;
        }
    }
}
