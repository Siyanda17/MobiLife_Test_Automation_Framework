package com.mobilife.automation.glue;

import com.mobilife.Connect.Tables.Policy.PolicyRowMapper;
import com.mobilife.Connect.Tables.Policy.PolicyTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import com.mobilife.Config.AutomationFrameworkConfiguration;
import com.mobilife.Connect.Tables.SpecificDebit.*;
import com.mobilife.Driver.DriverSingleton;
import com.mobilife.Utilities.ConfigurationProperties;
import com.mobilife.Utilities.Constants;
import com.mobilife.pages.LoginPage.LoginPage;
import com.mobilife.pages.MainPage.MainPage;
import com.mobilife.pages.SpecificDebit.SpecificDebitDetailsWindow;
import com.mobilife.pages.SpecificDebit.SpecificDebitPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static junit.framework.TestCase.*;


//Context Configuration AutomationFrameworkConfiguration in order to include those variable
// to inject them to StepDefinition at runtime
@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private String scenarioName;
    private MainPage  mainPage;
    private LoginPage loginPage;
    private SpecificDebitPage specificDebitPage;
    private SpecificDebitDetailsWindow specificDebitDetailsWindow;

    private RowMapper<SpecificDebitTable> specificDebitRowMapper = new SpecificDebitRowMapper();
    private List<SpecificDebitTable> specificDebitTableObject;
    private PolicyTable policyTableObject;
    private RowMapper<PolicyTable> policyTableRowMapper = new PolicyRowMapper();

    @Autowired
    ConfigurationProperties configurationProperties;
    @SuppressWarnings({"jdbcTemplate","jdbcTemplatePolicy"})
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private  JdbcTemplate jdbcTemplatePolicy;

    @Before
    public void initializeObjects(){
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        int policy = 152738;
        mainPage = new MainPage();
        loginPage = new LoginPage();
        specificDebitPage = new SpecificDebitPage();
        specificDebitDetailsWindow = new SpecificDebitDetailsWindow();
        specificDebitTableObject = jdbcTemplate.query("SELECT * FROM SpecificDebit Where policy = ?",specificDebitRowMapper,policy);
        policyTableObject = jdbcTemplatePolicy.queryForObject("SELECT * FROM Policy Where Id = ?",policyTableRowMapper,policy);


    }
    /**
     * Set up for the different scenarios
     * */
    @Before
    public void setUp(Scenario scenario){
        scenarioName = scenario.getName();
    }
    @Given("I am on Specific Debit Tab")
    public void i_am_on_specific_debit_tab(){

        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Using Constants because  configurationProperties Changes the username
        loginPage.login(Constants.USERNAME, configurationProperties.getPassword());
        loginPage.AuthenticateLogin();
        mainPage.GoToSpecificDebitPage();

    }

    @When("I Add a Specific Debit")
    public void iAddASpecificDebit () {
        specificDebitPage.AddSpecificDebit();
    }

    @Then("Specific Debit Details window appears")
    public void specificDebitDetailsWindowAppears () {
       // specificDebitDetailsWindow.SearchForUniquePolicy("P0054805802LA1");
        //Check if Window Appeared
    }

    @Then("Find the policy")
    public void findThePolicy () {
        specificDebitDetailsWindow.SearchForUniquePolicy("P0054805802LA1");
        //System.out.println(specificDebitTableObject.getPolicyAmount());
        System.out.println(policyTableObject.getUniquePolicyNumber());
    }

    @When("I select the policy")
    public void iSelectThePolicy () {
        specificDebitDetailsWindow.SelectPolicy();
    }

    @Then("Policy number filed is populated")
    public void policyNumberFiledIsPopulated () {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(specificDebitDetailsWindow.getPolicyNumber());
      assertFalse(specificDebitDetailsWindow.getPolicyNumber().getAttribute("value").isEmpty());
    }

    @And("Policy number is uneditable")
    public void policyNumberIsUneditable () {
        //Policy TextBox not enabled
        assertFalse(specificDebitDetailsWindow.isPolicyTextboxEnable());
    }

    @And("Collection Method Should be {string}")
    public void collectionMethodShouldBeSSVS (String args0) {
        String actual = specificDebitDetailsWindow.getCollectionMethod().getText().substring(0,4);
        assertEquals("Collection Method should be SSVS",args0,actual);
    }

    @And("Premium Month date picker translates to MM\\/YY")
    public void premiumMonthDatePickerTranslatesToMMYY () {
        specificDebitDetailsWindow.ChoosePremiumMonth("Mar");
    }

    @And("The Amount is automatically populated")
    public void theAmountIsAutomaticallyPopulated () {
        assertFalse("Amount not populated",specificDebitDetailsWindow.getAmount().isBlank());
    }

    @And("Matches the current Nett Premium")
    public void matchesTheCurrentNettPremium () {
        Double actual = Double.parseDouble(specificDebitDetailsWindow.getAmount());
        Double expected = specificDebitTableObject.get(0).getPolicyAmount();
        assertEquals(expected,actual);
    }

    @And("Nett Premium cannot be negative")
    public void nettPremiumCannotBeNegative () {
        specificDebitDetailsWindow.setPolicyAmount("-");
        assertFalse(specificDebitDetailsWindow.getPremiumMonth().getAttribute("value").contains("-"));
    }

    @Then("Enter Action Date")
    public void enterActionDate () {
        LocalDate date = LocalDate.now();
        String day = String.format("%02d", date.getDayOfMonth()+2);
        String month =  String.format("%02d",date.getMonth().getValue());
        String year = String.valueOf(date.getYear());
       specificDebitDetailsWindow.setActionDate(day,month,year);
    }

    @Then("Click Save")
    public void clickSave () {




        specificDebitDetailsWindow.saveSpecificDebit();
        if(specificDebitDetailsWindow.getDuplicatePopUp().isDisplayed()){
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5L));
            wait.until(ExpectedConditions.elementToBeClickable(specificDebitDetailsWindow.getDuplicatePopUpBtn()));
            specificDebitDetailsWindow.getDuplicatePopUpBtn().click();
        }
    }

    @And("If it's after {string} Mobility will show an error text")
    public void ifItSAfterMobilityWillShowAnErrorText (String arg0) {
        LocalTime cutOffTime = LocalTime.of(Integer.parseInt(arg0.substring(0,1)), Integer.parseInt(arg0.substring(3,4)));
        out.println(arg0.substring(0,2)+arg0.substring(3,5));
        //If Ran after 14:30 catch the error label
        if(LocalTime.now().isAfter(cutOffTime)){//Get the Error Text
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
            wait.until(ExpectedConditions.visibilityOf(specificDebitDetailsWindow.getErrorTextUnderActionDate()));
            String actualText = specificDebitDetailsWindow.getErrorTextUnderActionDate().getText();
            assertTrue("Error text",specificDebitDetailsWindow.getErrorTextUnderActionDate().isDisplayed());}

    }

    @And("If it's Duplicate for the same month get pop up")
    public void ifItSDuplicateForTheSameMonthGetPopUp () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(15000));
        wait.until(ExpectedConditions.visibilityOf(specificDebitDetailsWindow.getDuplicatePopUp()));
        //Fix logic
        try {
            assertNotNull(specificDebitDetailsWindow.getDuplicatePopUp());
        }catch (NullPointerException e){
            out.println("No Duplicate");
        }
    }

    @Then("Error message will show below the empty textboxes")
    public void errorMessageWillShowBelowTheEmptyTextboxes () {
        if(specificDebitDetailsWindow.getPremiumMonth().getText().isEmpty()){
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
            wait.until(ExpectedConditions.visibilityOf(specificDebitDetailsWindow.getErrorTextUnderPremiumMonth()));
            assertTrue(specificDebitDetailsWindow.getErrorTextUnderPremiumMonth().isDisplayed());
        }

        if(specificDebitDetailsWindow.getActionDate().getText().isEmpty()){
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
            wait.until(ExpectedConditions.visibilityOf(specificDebitDetailsWindow.getErrorTextUnderActionDate()));
            assertTrue(specificDebitDetailsWindow.getErrorTextUnderActionDate().isDisplayed());
        }
    }

    @When("Submitted checkbox empty\\(no tick) before the linked collection item is Submitted")
    public void submittedCheckboxEmptyNoTickBeforeTheLinkedCollectionItemIsSubmitted () {


        // Get the value of a specific CSS property for the ::before pseudo-element
        //String beforeContent = element.getCssValue("content");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String contentValue = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", specificDebitDetailsWindow.getSubmittedCheckbox());
        char submit = contentValue.charAt(0);
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
//        wait.until(ExpectedConditions.elementToBeClickable(specificDebitDetailsWindow.getPolicyNumber()));
        out.println(submit);
        out.println(contentValue);
        //Check if value from contentValue is the desired

    }

    @And("Cannot tick Submitted checkbox")
    public void cannotTickSubmittedCheckbox () {
       assertFalse(specificDebitDetailsWindow.getSubmittedBtn().isEnabled());
    }

    @And("Can add notes")
    public void canAddNotes () {
        specificDebitDetailsWindow.writeNotes("Help i am trapped in a Specific Debit");
    }

    @Then("Submitted checkbox ticked after the linked collection item is submitted")
    public void submittedCheckboxTickedAfterTheLinkedCollectionItemIsSubmitted () {

    }

    @When("delete a saved  specific debit before it has been submitted")
    public void deleteASavedSpecificDebitBeforeItHasBeenSubmitted () {
        //Add more lines
        specificDebitDetailsWindow.deleteSpecificDebit();
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

    @Given("I am on the login page")
    public void iAmOnTheLoginPage () {
    }

    @When("I enter my valid username and password")
    public void iEnterMyValidAnd () {
    }

    @And("click the {string} button")
    public void clickTheButton (String arg0) {
    }

    @Then("I should be redirected to the homepage")
    public void iShouldBeRedirectedToTheHomepage () {
    }

    @And("see a welcome message with my {string}")
    public void seeAWelcomeMessageWithMy (String arg0) {
    }


}
