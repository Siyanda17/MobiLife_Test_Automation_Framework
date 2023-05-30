package com.mobilife.automation.glue;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mobilife.Config.AutomationFrameworkConfiguration;
import org.mobilife.Connect.SpecificDebit.SpecificDebitRowMapper;
import org.mobilife.Connect.SpecificDebit.SpecificDebitTable;
import org.mobilife.Driver.DriverSingleton;
import org.mobilife.Utilities.ConfigurationProperties;
import org.mobilife.Utilities.Constants;
import org.mobilife.pages.LoginPage.LoginPage;
import org.mobilife.pages.MainPage.MainPage;
import org.mobilife.pages.SpecificDebit.SpecificDebitDetailsWindow;
import org.mobilife.pages.SpecificDebit.SpecificDebitPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;

//Context Configuration AutomationFrameworkConfiguration in order to include those variable
// to inject them to StepDefinition at runtime
@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private MainPage  mainPage;
    private LoginPage loginPage;
    private SpecificDebitPage specificDebitPage;
    private SpecificDebitDetailsWindow specificDebitDetailsWindow;

    private RowMapper<SpecificDebitTable> specificDebitRowMapper = new SpecificDebitRowMapper();
    private SpecificDebitTable specificDebitTableObject;
    private  SpecificDebitTable specificDebitTable;

    @Autowired
    ConfigurationProperties configurationProperties;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void initializeObjects(){
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        int policy = 152738;
        mainPage = new MainPage();
        loginPage = new LoginPage();
        specificDebitPage = new SpecificDebitPage();
        specificDebitDetailsWindow = new SpecificDebitDetailsWindow();
        specificDebitTableObject = jdbcTemplate.queryForObject("SELECT * FROM SpecificDebit Where id = ?",specificDebitRowMapper,policy);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Given("I am on Specific Debit Tab")
    public void i_am_on_specific_debit_tab(){

        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
        LoginPage loginPage = new LoginPage();
        //Using Constants because  configurationProperties Changes the username
        loginPage.login(Constants.USERNAME, configurationProperties.getPassword());
        loginPage.AuthenticateLogin();

    }

    @When("I Add a Specific Debit")
    public void iAddASpecificDebit () {

    }

    @Then("Specific Debit Details window appears")
    public void specificDebitDetailsWindowAppears () {

    }

    @Then("Find the policy")
    public void findThePolicy () {

    }

    @When("I select the policy")
    public void iSelectThePolicy () {
    }

    @Then("Policy number filed is populated")
    public void policyNumberFiledIsPopulated () {
    }

    @And("Policy number is uneditable")
    public void policyNumberIsUneditable () {
    }

    @And("Collection Method Should be SSVS")
    public void collectionMethodShouldBeSSVS () {
    }

    @And("Premium Month date picker translates to MM\\/YY")
    public void premiumMonthDatePickerTranslatesToMMYY () {
    }

    @And("The Amount is automatically populated")
    public void theAmountIsAutomaticallyPopulated () {
    }

    @And("Matches the current Nett Premium")
    public void matchesTheCurrentNettPremium () {
    }

    @And("Nett Premium cannot be negative")
    public void nettPremiumCannotBeNegative () {
    }

    @Then("Enter Action Date")
    public void enterActionDate () {
    }

    @Then("Click Save")
    public void clickSave () {
    }

    @And("If it's after {string} Mobility will show an error text")
    public void ifItSAfterMobilityWillShowAnErrorText (String arg0) {
    }

    @And("If it's Duplicate for the same month get pop up")
    public void ifItSDuplicateForTheSameMonthGetPopUp () {
    }

    @Then("Error message will show below the empty textboxes")
    public void errorMessageWillShowBelowTheEmptyTextboxes () {
    }

    @When("Submitted checkbox empty\\(no tick) before the linked collection item is Submitted")
    public void submittedCheckboxEmptyNoTickBeforeTheLinkedCollectionItemIsSubmitted () {
    }

    @And("Cannot tick Submitted checkbox")
    public void cannotTickSubmittedCheckbox () {
    }

    @And("Can add notes")
    public void canAddNotes () {
    }

    @Then("Submitted checkbox ticked after the linked collection item is submitted")
    public void submittedCheckboxTickedAfterTheLinkedCollectionItemIsSubmitted () {
    }

    @When("delete a saved  specific debit before it has been submitted")
    public void deleteASavedSpecificDebitBeforeItHasBeenSubmitted () {
    }

    @Then("deleting a Specific Debit the {string} column in the database table gets popuplated")
    public void deletingASpecificDebitTheDeletedColumnInTheDatabaseTableGetsPopuplated () {
    }

    @And("Cannot delete a specific debit after it has been submitted")
    public void cannotDeleteASpecificDebitAfterItHasBeenSubmitted () {
    }

    @When("Adding for inactive contract payment status")
    public void addingForInactiveContractPaymentStatus () {
    }

    @Then("client contract payment status updates to {string} and contract payment status reason to {string}")
    public void clientContractPaymentStatusUpdatesToActiveAndContractPaymentStatusReasonToClientRequested () {
    }

    @When("Allow Edit Specific Debit before Submission")
    public void allowEditSpecificDebitBeforeSubmission () {
    }

    @When("Deny Edit Specific Debit after Submission")
    public void denyEditSpecificDebitAfterSubmission () {
    }
}
