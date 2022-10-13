package pages.careers;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import utils.ActionsUtil;
import utils.JsonFileUtil;

public class CareerApplicationPage extends ActionsUtil {
    private final By applyBtn = By.id("apply_button");
    private final By firstnameField = By.id("first_name");
    private final By lastnameField = By.id("last_name");
    private final By emailField = By.id("email");
    private final By phoneField = By.id("phone");
    private final By cityField = By.id("auto_complete_input");
    private final String cityOptionLocator = "//ul[contains(@id,'location')]/li[text()='%s']";
    private final By resumeUploader = By.cssSelector("#s3_upload_for_resume [type='file']");
    private final String addedResumeLocator = "//span[@id='resume_filename' and text()='%s']";
    private final By resumeManualBtn = By.cssSelector("#resume_fieldset .link-container>[data-source='paste']");
    private final By resumeTextArea = By.id("resume_text");
    private final By coverLetterUploader = By.cssSelector("#s3_upload_for_cover_letter [type='file']");
    private final String addedCoverLetterLocator = "//span[@id='cover_letter_filename' and text()='%s']";
    private final By coverLetterManualBtn = By.cssSelector("#cover_letter_fieldset .link-container>[data-source='paste']");
    private final By coverLetterTextArea = By.id("cover_letter_text");
    private final By linkedInProfileField = By.id("job_application_answers_attributes_0_text_value");
    private final By requireVisaList = By.id("job_application_answers_attributes_1_boolean_value");
    private final By heardOfDeliverooList = By.id("job_application_answers_attributes_2_boolean_value");
    private final By useDeliverooList = By.id("job_application_answers_attributes_3_boolean_value");
    private final String checkBoxLocator = "//div[contains(@class,'demographic_question')]//label[contains(.,'%s')]";
    private final By submitBtn = By.id("submit_app");

    /**
     * This method is used to fill the career application with the data of the given used
     *
     * @param user the user to apply for the career
     * @throws Exception in case of not being able to fill the application or submitting it
     */
    public void submitApplication(String user) throws Exception {
        JSONObject application = (JSONObject) ((JSONObject) JsonFileUtil.getJsonObject().get("applicants")).get(user);

        clickOn(applyBtn);
        addText(firstnameField, (String) application.get("firstname"));
        addText(lastnameField, (String) application.get("lastname"));
        addText(emailField, (String) application.get("email"));
        addText(phoneField, (String) application.get("phone"));
        addText(cityField, (String) application.get("city"));
        clickOn(By.xpath(String.format(cityOptionLocator, application.get("city"))));
        addResume((JSONObject) application.get("resume"));
        addCoverLetter((JSONObject) application.get("coverLetter"));
        addText(linkedInProfileField, (String) application.get("linkedIn"));
        selectByText(requireVisaList, (String) application.get("requireVisa"));
        selectByText(heardOfDeliverooList, (String) application.get("heardOfDeliveroo"));
        selectByText(useDeliverooList, (String) application.get("useDeliveroo"));
        clickOn(By.xpath(String.format(checkBoxLocator, application.get("gender"))));
        clickOn(By.xpath(String.format(checkBoxLocator, application.get("orientation"))));
        clickOn(By.xpath(String.format(checkBoxLocator, application.get("disability"))));
        clickOn(By.xpath(String.format(checkBoxLocator, application.get("ethnicity"))));
        clickOn(submitBtn);
    }

    /**
     * This method is used to attach or manually add resume to a career application
     *
     * @param resume the data of the resume to be added
     * @throws Exception in case of not being able to add the resume
     */
    private void addResume(JSONObject resume) throws Exception {
        if (resume != null && !resume.isEmpty()) {
            String resumeType = (String) resume.get("type");

            if (resumeType.equalsIgnoreCase("file")) {
                String filename = (String) resume.get("file");
                uploadFile(resumeUploader, testDataFolder, filename);
                waitForElementVisibility(By.xpath(String.format(addedResumeLocator, filename)));
            } else if (resumeType.equalsIgnoreCase("text")) {
                clickOn(resumeManualBtn);
                addText(resumeTextArea, (String) resume.get("text"));
            } else {
                throw new Exception(String.format("Undefined resume attaching type: %s", resumeType));
            }
        }
    }

    /**
     * This method is used to attach or manually add cover letter to a career application
     *
     * @param coverLetter the data of the cover letter to be added
     * @throws Exception in case of not being able to add the cover letter
     */
    private void addCoverLetter(JSONObject coverLetter) throws Exception {
        if (coverLetter != null && !coverLetter.isEmpty()) {
            String resumeType = (String) coverLetter.get("type");

            if (resumeType.equalsIgnoreCase("file")) {
                String filename = (String) coverLetter.get("file");
                uploadFile(coverLetterUploader, testDataFolder, filename);
                waitForElementVisibility(By.xpath(String.format(addedCoverLetterLocator, filename)));
            } else if (resumeType.equalsIgnoreCase("text")) {
                clickOn(coverLetterManualBtn);
                addText(coverLetterTextArea, (String) coverLetter.get("text"));
            } else {
                throw new Exception(String.format("Undefined resume attaching type: %s", resumeType));
            }
        }
    }
}
