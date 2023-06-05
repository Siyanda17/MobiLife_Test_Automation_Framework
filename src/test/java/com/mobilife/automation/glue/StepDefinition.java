package com.mobilife.automation.glue;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mobilife.Connect.Tables.Policy.PolicyRowMapper;
import com.mobilife.Connect.Tables.Policy.PolicyTable;
import com.mobilife.Utilities.Log;
import io.cucumber.java.After;
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
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static junit.framework.TestCase.*;


//Context Configuration AutomationFrameworkConfiguration in order to include those variable
// to inject them to StepDefinition at runtime
@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private  WebDriver driver;
    private String scenarioName;
    private MainPage  mainPage;
    private LoginPage loginPage;
    private SpecificDebitPage specificDebitPage;
    private SpecificDebitDetailsWindow specificDebitDetailsWindow;

    private final RowMapper<SpecificDebitTable> specificDebitRowMapper = new SpecificDebitRowMapper();
    private List<SpecificDebitTable> specificDebitTableObject;
    private PolicyTable policyTableObject;
    private final RowMapper<PolicyTable> policyTableRowMapper = new PolicyRowMapper();
    static ExtentReports extent;
    ExtentSparkReporter spark = new ExtentSparkReporter("Specific_Debit_Regression_Report.html");
    ExtentTest test;


    @Autowired
    ConfigurationProperties configurationProperties;


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private  JdbcTemplate jdbcTemplatePolicy;

    public StepDefinition () {
    }

    @Before(order = 1)
    public void initializeObjects(){
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        int policy = 152738;
        mainPage = new MainPage();
        loginPage = new LoginPage();
        specificDebitPage = new SpecificDebitPage();
        specificDebitDetailsWindow = new SpecificDebitDetailsWindow();
        specificDebitTableObject = jdbcTemplate.query("SELECT * FROM SpecificDebit Where policy = ?",specificDebitRowMapper,policy);
        policyTableObject = jdbcTemplatePolicy.queryForObject("SELECT * FROM Policy Where Id = ?",policyTableRowMapper,policy);
        Log.info(scenarioName+" : Initialize Objects");
        extent = new ExtentReports();
        //Can have more than one report attached
        extent.attachReporter(spark);


    }
    /**
     * Set up for the different scenarios
     * */
    @Before(order = 2)
    public void setUp(Scenario scenario){
        scenarioName = scenario.getName();

        Log.getLogData(Log.class.getName());
        Log.startTest(scenarioName);

        test = extent.createTest(scenarioName).assignAuthor("Yakhuxolo Mxabo")
                .assignCategory("Specific Debit Regression").assignDevice(configurationProperties.getBrowser());

        driver = DriverSingleton.getDriver();

        if (scenarioName.equals("Delete Specific Debit")) {
            specificDebitDetailsWindow.getCancelBtn().click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            specificDebitPage.searchSpecificDebit("P0054805802LA1");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            specificDebitPage.getLatestSpecificDebit();
        }
    }
    @Given("I am on the login page")
    public void iAmOnTheLoginPage () {
        driver.get(Constants.URL);


    }

    @When("I enter my valid username and password")
    public void iEnterMyValidAnd () {
        loginPage.login(Constants.USERNAME, configurationProperties.getPassword());
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            test.fail("incorrect details");
        }catch (TimeoutException e){
            Log.info("Correct Details");
            test.pass("I enter my valid username and password");
        }

    }

    @And("click the {string} button")
    public void clickTheButton (String arg0) {
        loginPage.AuthenticateLogin();
        try{
            loginPage.getOneTimePin().isDisplayed();
            test.fail("Hasn't Authenticated");
            Log.error("not Authenticated");
        }catch (NullPointerException e){
            Log.info("Authenticated");
            test.pass("click the {string} button");
            test.pass("User has been Authenticated");
        }

    }

    @Then("I should be redirected to the homepage")
    public void iShouldBeRedirectedToTheHomepage () {
        try {
            assertEquals(Constants.URL, driver.getCurrentUrl());
            test.pass("I should be redirected to the homepage");
            Log.info("on the homepage");
        }catch (AssertionError|NullPointerException e){
            test.fail("Not on the homepage");
            Log.error("Not on the homepage");
        }
    }

    @And("see a welcome message with my {string}")
    public void seeAWelcomeMessageWithMy (String arg0) {
        if (mainPage.getMessage().equals(arg0)){
           test.pass("Welcome message appearing on screen");
           Log.info("Welcome Message appearing");
        }else {
            Log.error("Welcome Message not appearing");
            test.fail("No Welcome message appearing on screen");
            assertEquals(arg0,mainPage.getMessage());
        }
    }
    @Given("I am on Specific Debit Tab")
    public void i_am_on_specific_debit_tab(){

        mainPage.GoToSpecificDebitPage();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Using Constants because  configurationProperties Changes the username

        Log.info(scenarioName+" : On the specific debit tab");

    }

    @When("I Add a Specific Debit")
    public void iAddASpecificDebit () {
        specificDebitPage.AddSpecificDebit();
        Log.info("Add Specific Debit");
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
        Log.info(policyTableObject.getUniquePolicyNumber());
    }

    @When("I select the policy")
    public void iSelectThePolicy () {
        specificDebitDetailsWindow.SelectPolicy();
        Log.info(scenarioName+ ": Select Policy");
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
      Log.info(scenarioName + " : Policy number filed is populated");
    }

    @And("Policy number is uneditable")
    public void policyNumberIsUneditable () {
        //Policy TextBox not enabled
        assertFalse(specificDebitDetailsWindow.isPolicyTextboxEnable());
        Log.info(scenarioName +" : Policy number is uneditable");
    }

    @And("Collection Method Should be {string}")
    public void collectionMethodShouldBeSSVS (String args0) {
        String actual = specificDebitDetailsWindow.getCollectionMethod().getText().substring(0,4);
        assertEquals("Collection Method should be SSVS",args0,actual);
        Log.info("Collection Method Should be SSVS");
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LocalDate date = LocalDate.now();
        String day = String.format("%02d", date.getDayOfMonth()+4);
        String month =  String.format("%02d",date.getMonth().getValue());
        String year = String.valueOf(date.getYear());
       specificDebitDetailsWindow.setActionDate(day,month,year);
       assertFalse(specificDebitDetailsWindow.isThereASubmittedCheckMark());
    }

    @Then("Click Save")
    public void clickSave () {




        specificDebitDetailsWindow.saveSpecificDebit();

        if(!scenarioName.equals("Add Specific Debit without filling in fields")){
            if(specificDebitDetailsWindow.isDuplicate()) {
                Log.info("is a duplicate");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L),Duration.ofSeconds(5L));
                wait.until(ExpectedConditions.elementToBeClickable(specificDebitDetailsWindow.getDuplicatePopUpBtn()));
                // Execute JavaScript code to click the button
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.querySelector(\"div.swal2-actions > button.swal2-confirm.btn.btn-primary\").click();");
            }
            // specificDebitDetailsWindow.getDuplicatePopUpBtn().click();
            assertFalse(specificDebitDetailsWindow.isDuplicate());
        }
    }

    @And("If it's after {string} Mobility will show an error text")
    public void ifItSAfterMobilityWillShowAnErrorText (String arg0) {
        LocalTime cutOffTime = LocalTime.of(Integer.parseInt(arg0.substring(0,1)), Integer.parseInt(arg0.substring(3,4)));
        out.println(arg0.substring(0,2)+arg0.substring(3,5));
        String inputDate = specificDebitDetailsWindow.
                getActionDate().
                getAttribute("value");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDate, formatter);
        //If Ran after 14:30 catch the error label
        if(LocalTime.now().isAfter(cutOffTime) &&
                        date.equals(LocalDate.now())){//Get the Error Text
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
            wait.until(ExpectedConditions.visibilityOf(specificDebitDetailsWindow.getErrorTextUnderActionDate()));
            String actualText = specificDebitDetailsWindow.getErrorTextUnderActionDate().getText();
            assertTrue("Error text",specificDebitDetailsWindow.getErrorTextUnderActionDate().isDisplayed());}

    }

    @And("If it's Duplicate for the same month get pop up")
    public void ifItSDuplicateForTheSameMonthGetPopUp () {

        specificDebitDetailsWindow.ChoosePremiumMonth("Mar");
         assertTrue("A pop up should appear",specificDebitDetailsWindow.isDuplicate());

    }

    @Then("Error message will show below the empty textboxes")
    public void errorMessageWillShowBelowTheEmptyTextboxes () {
        if(specificDebitDetailsWindow.getPremiumMonth().getText().isEmpty()){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            assertTrue(specificDebitDetailsWindow.getErrorTextUnderPremiumMonth().isDisplayed());
        }

        if(specificDebitDetailsWindow.getActionDate().getText().isEmpty()){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            assertTrue(specificDebitDetailsWindow.getErrorTextUnderActionDate().isDisplayed());
        }
    }

    @When("Submitted checkbox empty\\(no tick) before the linked collection item is Submitted")
    public void submittedCheckboxEmptyNoTickBeforeTheLinkedCollectionItemIsSubmitted () {
        assertFalse(specificDebitDetailsWindow.isThereASubmittedCheckMark());
        //Check if value from contentValue is the desired

    }

    @And("Cannot tick Submitted checkbox")
    public void cannotTickSubmittedCheckbox () {
       assertFalse(specificDebitDetailsWindow.getSubmittedBtn().isEnabled());
    }

    @And("Can add notes")
    public void canAddNotes () {
        specificDebitDetailsWindow.writeNotes("Help i am trapped in a Specific Debit");
        //To be Modified
        //specificDebitDetailsWindow.getCancelBtn();
    }

    @Then("Submitted checkbox ticked after the linked collection item is submitted")
    public void submittedCheckboxTickedAfterTheLinkedCollectionItemIsSubmitted () {

    }

    @When("delete a saved  specific debit before it has been submitted")
    public void deleteASavedSpecificDebitBeforeItHasBeenSubmitted () {
        //Add more lines

        specificDebitDetailsWindow.deleteSpecificDebit();
        specificDebitDetailsWindow.confirmDelete();
    }

    @Then("deleting a Specific Debit the {string} column in the database table gets populated")
    public void deletingASpecificDebitTheDeletedColumnInTheDatabaseTableGetsPopulated (String arg0) {

        driver.get(Constants.SPECIFICDEBITURL);
        int policy = 152738;
        try {
            Thread.sleep(5L);
            specificDebitTableObject = jdbcTemplate.query("SELECT *\n" +
                    "                            FROM SpecificDebit s\n" +
                    "                    WHERE s.Id = (\n" +
                    "                            SELECT MAX(Id)\n" +
                    "                    FROM SpecificDebit\n" +
                    "                    WHERE Policy = ?\n" +
                    "            \n" +
                    "            )",specificDebitRowMapper,policy);

            Thread.sleep(5L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SpecificDebitTable deletedSpecific = specificDebitTableObject.get(specificDebitTableObject.size()-1);
        assertEquals(1, deletedSpecific.getDeleted());
    }

    @And("Cannot delete a specific debit after it has been submitted")
    public void cannotDeleteASpecificDebitAfterItHasBeenSubmitted () {
        // Create an instance of the Random class
        Random random = new Random();

        // Generate a random integer between 0 and 999 (inclusive)
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String policy = specificDebitPage.getSubmittedPolicies()
                .get(random.nextInt(specificDebitPage.getSubmittedPolicies().size()))
                .findElement(By.xpath("//body[1]/div[7]/div[2]/form[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]"))
                .getText();
        out.println(policy);
        specificDebitPage.searchSpecificDebit(policy);
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        specificDebitPage.getASubmittedPolicy();

    }

    @When("Adding for inactive contract payment status")
    public void addingForInactiveContractPaymentStatus () {
    }

    @Then("client contract payment status updates to {string} and contract payment status reason to {string}")
    public void clientContractPaymentStatusUpdatesToActiveAndContractPaymentStatusReasonToClientRequested () {
    }

    @When("Allow Edit Specific Debit before Submission")
    public void allowEditSpecificDebitBeforeSubmission () {
        driver.get(Constants.SPECIFICDEBITURL);
        Random random = new Random();
        try {
            Thread.sleep(5L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String policy = specificDebitPage.getSubmittedPolicies()
                .get(random.nextInt(specificDebitPage.getSubmittedPolicies().size()))
                .findElement(By.xpath("//body[1]/div[7]/div[2]/form[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]"))
                .getText();
        specificDebitPage.searchSpecificDebit(policy);
        specificDebitPage.getAnUnSubmittedPolicy();
    }

    @When("Deny Edit Specific Debit after Submission")
    public void denyEditSpecificDebitAfterSubmission () {
    }


    @After(order = 1)
    public void  cleanUpScenario(){
        if(scenarioName.equals("Add Specific Debit")){
            if(specificDebitDetailsWindow.getSpecificDebitDetailsWindow().isDisplayed()){
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5L));
                wait.until(ExpectedConditions.visibilityOf(specificDebitDetailsWindow.getCancelBtn()));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", specificDebitDetailsWindow.getCancelBtn());

            }
        }

    }
    @After(order = 2)
    public void createReport(){
        extent.flush();
    }


}
