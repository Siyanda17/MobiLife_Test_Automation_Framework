package com.mobilife.automation.glue;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mobilife.Connect.Tables.Policy.PolicyRowMapper;
import com.mobilife.Connect.Tables.Policy.PolicyTable;
import com.mobilife.Utilities.Log;
import com.mobilife.Utilities.Utils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import static java.lang.System.in;
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
    private Scenario scenario;

    static ExtentReports extent;
    ExtentSparkReporter spark = new ExtentSparkReporter("Specific_Debit_Regression_Report.html");
    private ExtentTest test;
    private int retry_count = 0;


    @Autowired
    ConfigurationProperties configurationProperties;


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private  JdbcTemplate jdbcTemplatePolicy;
    //Action Date number of Days
    private int numberOfDays = 0;

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
        //extent = new ExtentReports();
        //Can have more than one report attached
       // extent.attachReporter(spark);


    }
    /**
     * Set up for the different scenarios
     * */
    @Before(order = 2)
    public void setUp(Scenario scenario){
        this.scenario = scenario;
        scenarioName = scenario.getName();

        Log.getLogData(Log.class.getName());
        Log.startTest(scenarioName);
        test = ExtentCucumberAdapter.getCurrentScenario();
        scenario.log(scenarioName);

//        test = extent.createTest(scenarioName).assignAuthor("Yakhuxolo Mxabo")
//                .assignCategory("Specific Debit Regression").assignDevice(configurationProperties.getBrowser());

        driver = DriverSingleton.getDriver();

        if (scenarioName.equals("Delete Specific Debit")) {
            specificDebitDetailsWindow.getCancelBtn().click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    @Given("I am on the login page")
    public void iAmOnTheLoginPage () {
        driver.get(Constants.URL);


        assertEquals(Constants.LOGIN_URL,driver.getCurrentUrl());
        //Trial for precise reporting
        ExtentCucumberAdapter.getCurrentStep().pass("I am on the login page now");
        scenario.log("");
        //test.pass("I am on the login page");
    }

    @When("I enter my valid username and password")
    public void iEnterMyValidAnd () {
        loginPage.login(Constants.USERNAME, configurationProperties.getPassword());
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
            wait.until(ExpectedConditions.alertIsPresent());
            ExtentCucumberAdapter.getCurrentStep().fail("incorrect details");
            Utils.takeScreenshot(scenario);
        }catch (TimeoutException e){
            Log.info("Correct Details");

           ExtentCucumberAdapter.getCurrentStep().pass("I enter my valid username and password");
        }

    }

    @And("click the {string} button")
    public void clickTheButton (String arg0) {
        loginPage.AuthenticateLogin();
        try{
            loginPage.getOneTimePin().isDisplayed();

            Log.info("Authenticated");
            ExtentCucumberAdapter.getCurrentStep().pass("click the {string} button");
            ExtentCucumberAdapter.getCurrentStep().pass("User has been Authenticated");

        }catch (NullPointerException e){

            ExtentCucumberAdapter.getCurrentStep().fail("Hasn't Authenticated");

            Utils.takeScreenshot(scenario);
            Log.error("not Authenticated");
        }

    }

    @Then("I should be redirected to the homepage")
    public void iShouldBeRedirectedToTheHomepage () {
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            assertEquals(Constants.HOME_URL, driver.getCurrentUrl());
            ExtentCucumberAdapter.getCurrentStep().pass("I should be redirected to the homepage");
            //test.pass("I should be redirected to the homepage");
            Log.info("on the homepage");
        }catch (AssertionError e){
            //test.fail("Not on the homepage");
            ExtentCucumberAdapter.getCurrentStep().fail("Not on the homepage");
            ExtentCucumberAdapter.getCurrentStep().fail(e.getMessage());
            Log.error(e.getMessage());
            Log.error("Not on the homepage");
        }
    }

    @And("see a welcome message with my {string}")
    public void seeAWelcomeMessageWithMy (String arg0) {
        try {
            Thread.sleep(5000,2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (mainPage.getMessage().equals(arg0)){
            ExtentCucumberAdapter.getCurrentStep().pass("Welcome message appearing on screen");
           Log.info("Welcome Message appearing");
        }else {
            Log.error("Welcome Message not appearing");
            ExtentCucumberAdapter.getCurrentStep().fail("No Welcome message appearing on screen");

            takeScreenshot();
          //  test.fail("No Welcome message appearing on screen");
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
        try {
            sleep(4000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // specificDebitDetailsWindow.SearchForUniquePolicy("P0054805802LA1");
        //Check if Window Appeared
        if(specificDebitDetailsWindow.getSpecificDebitDetailsWindow().isDisplayed()){
            ExtentCucumberAdapter.getCurrentStep().pass("Specific Debit Details window is appearing");
        }else {
            ExtentCucumberAdapter.getCurrentStep().fail("Specific Debit Details window is not appearing");
            Utils.takeScreenshot(scenario);
        }

    }

    @Then("Find the policy")
    public void findThePolicy () {
        try {
            sleep(4000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String uniqueText = "P0054805802LA1";
        specificDebitDetailsWindow.SearchForUniquePolicy(uniqueText);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));


        assertEquals(uniqueText,policyTableObject.getUniquePolicyNumber());
        ExtentCucumberAdapter.getCurrentStep().pass("Policy Exists on the database");
        try {

            specificDebitDetailsWindow.policyDoesNotExist().isDisplayed() ;
            ExtentCucumberAdapter.getCurrentStep().fail("The policy was not found");
            //This retries to find the policy in case of a not found error
            if(retry_count>Constants.STEP_RETRY){
                findThePolicy();
            }


        }catch (NoSuchElementException e){
            retry_count = 0;

            ExtentCucumberAdapter.getCurrentStep().pass("The policy has been selected successfully");

        }
        Log.info(policyTableObject.getUniquePolicyNumber());
    }

    @When("the policy is {string}")
    public void iSelectThePolicy (String arg0) {
        specificDebitDetailsWindow.SelectPolicy();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));

        if(specificDebitDetailsWindow.getSelectBadge().getText().equals(arg0)){

            ExtentCucumberAdapter.getCurrentStep().pass("The policy has been selected successfully");

        }else {

            ExtentCucumberAdapter.getCurrentStep().fail("The policy has not been selected successfully");
            Utils.takeScreenshot(scenario);
        }

        Log.info(scenarioName+ ": Select Policy");
    }

    @Then("Policy number filed is populated")
    public void policyNumberFiledIsPopulated () {
        try {
            Thread.sleep(10000L);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println(specificDebitDetailsWindow.getPolicyNumber());

        assertFalse(specificDebitDetailsWindow.getPolicyNumber().getAttribute("value").isEmpty());

        ExtentCucumberAdapter.getCurrentStep().pass("The policy textbox has been populated successfully");

        Log.info(scenarioName + " : Policy number filed is populated");
    }

    @And("Policy number is uneditable")
    public void policyNumberIsUneditable () {
        //Policy TextBox not enabled
        try {
            assertFalse(specificDebitDetailsWindow.isPolicyTextboxEnable());

            ExtentCucumberAdapter.getCurrentStep().pass("Policy textbox number is uneditable");

            Log.info(scenarioName +" : Policy number is uneditable");

        }catch (AssertionError e){

            ExtentCucumberAdapter.getCurrentStep().fail("Policy textbox number is uneditable");
            Utils.takeScreenshot(scenario);

            Log.info(scenarioName +" : Policy number is editable");
        }

    }

    @And("Collection Method Should be {string}")
    public void collectionMethodShouldBeSSVS (String args0) {
        //Add a try and change method if its DebiCheck
        String actual = specificDebitDetailsWindow.getCollectionMethod().getText().substring(0,4);
        try {
            assertEquals("Collection Method should be SSVS",args0,actual);

            ExtentCucumberAdapter.getCurrentStep().pass("The default collection method is SSVS");
        }catch (AssertionError e){
            ExtentCucumberAdapter.getCurrentStep().fail("The collection method is not SSVS");
        }




        Log.info("Collection Method Should be SSVS");
    }

    @And("Premium Month date picker translates to MM\\/YY")
    public void premiumMonthDatePickerTranslatesToMMYY () {

        for(int i = 1;i < 13;i++){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            specificDebitDetailsWindow.ChoosePremiumMonth(SpecificDebitDetailsWindow.fromNumToMonth(i));
            Log.info(specificDebitDetailsWindow.getPremiumMonth().getAttribute("value"));
        }

        specificDebitDetailsWindow.ChoosePremiumMonth("Mar");
        try {

            assertFalse(specificDebitDetailsWindow.getPremiumMonth().getAttribute("value").isEmpty());

            ExtentCucumberAdapter.getCurrentStep().pass("Can choose premium month for any months");

           // Utils.takeScreenshot(scenario);

        }catch (AssertionError e){
            ExtentCucumberAdapter.getCurrentStep().fail("Cannot choose premium month for any months");

           // Utils.takeScreenshot(scenario);
        }


        Log.info("Month");
    }

    @And("The Amount is automatically populated")
    public void theAmountIsAutomaticallyPopulated () {
        try {
            assertFalse("Amount not populated",specificDebitDetailsWindow.getAmount().isBlank());
            ExtentCucumberAdapter.getCurrentStep().pass("The Amount is automatically populated");
        }catch (AssertionError e){
            ExtentCucumberAdapter.getCurrentStep().fail("The Amount is empty");
            e.printStackTrace();
        }

    }

    @And("Matches the current Nett Premium")
    public void matchesTheCurrentNettPremium () {
        Double actual = Double.parseDouble(specificDebitDetailsWindow.getAmount());
        Double expected = specificDebitTableObject.get(0).getPolicyAmount();
        try {
            assertEquals(expected,actual);
        }catch (AssertionError e){
            ExtentCucumberAdapter.getCurrentStep().fail("The Nett Premium does not match the policy on the database");
        }

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
        // Gets for Today
        LocalDate date = LocalDate.now();
        String day = String.format("%02d", date.getDayOfMonth()+numberOfDays);
        String month =  String.format("%02d",date.getMonth().getValue());
        String year = String.valueOf(date.getYear());
       specificDebitDetailsWindow.setActionDate(day,month,year);
        assertNotNull(specificDebitDetailsWindow.getActionDate().getAttribute("value"));

    }

    @Then("Click Save")
    public void clickSave () {




        specificDebitDetailsWindow.saveSpecificDebit();
        //I want to change the date if it's a duplicate or its gives some error only when its Successful one
        //Condition changed from not equal to Add Specific Debit without filling in fields
        if(scenarioName.equals("Add Specific Debit")){
            if(specificDebitDetailsWindow.isDuplicate()) {
                Log.info("is a duplicate");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20),Duration.ofSeconds(5));
                wait.until(ExpectedConditions.elementToBeClickable(specificDebitDetailsWindow.getDuplicatePopUpBtn()));
                // Execute JavaScript code to click the button
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.querySelector(\"div.swal2-actions > button.swal2-confirm.btn.btn-primary\").click();");
            }
            // specificDebitDetailsWindow.getDuplicatePopUpBtn().click();
            assertFalse(specificDebitDetailsWindow.isDuplicate());
            if(specificDebitDetailsWindow.theresAnError()){

                numberOfDays = numberOfDays+2;
                specificDebitDetailsWindow.getActionDate().clear();
                //Calls the action date again to increment
                enterActionDate();
                scenario.log("if there's an error we can change action date");
                //Calls the save again
                clickSave();
            }
            scenario.log("Can Save the Specific Debit");
        }
    }

    private void takeScreenshot(){
        Utils.takeScreenshot(scenario);
    }

    @And("If it's after {string} Mobility will show an error text")
    public void ifItSAfterMobilityWillShowAnErrorText (String arg0) {
        LocalTime cutOffTime = LocalTime.of(Integer.parseInt(arg0.substring(0,2)), Integer.parseInt(arg0.substring(3,5)));
        out.println(arg0.substring(0,2)+arg0.substring(3,5));
        String inputDate = specificDebitDetailsWindow.
                getActionDate().
                getAttribute("value");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDate, formatter);
        //If Ran after 14:30 catch the error label
        if(LocalTime.now().isAfter(cutOffTime) &&
                        date.equals(LocalDate.now())){//Get the Error Text
            Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class,TimeoutException.class);
            fluentWait.until(ExpectedConditions.visibilityOf(specificDebitDetailsWindow.getErrorTextUnderActionDate()));
            String actualText = specificDebitDetailsWindow.getErrorTextUnderActionDate().getText();
            assertTrue("Error text",specificDebitDetailsWindow.getErrorTextUnderActionDate().isDisplayed());

        }

    }

    @And("If it's Duplicate for the same month get pop up")
    public void ifItSDuplicateForTheSameMonthGetPopUp () {

        specificDebitDetailsWindow.ChoosePremiumMonth("Mar");
         assertTrue("A pop up should appear",specificDebitDetailsWindow.isDuplicate());
         scenario.log("Check if there's a Duplicate");

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
    @When("Allow Edit Specific Debit before Submission")
    public void allowEditSpecificDebitBeforeSubmission () {

        specificDebitPage.searchSpecificDebit("P0054805802LA1");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        specificDebitPage.getLatestSpecificDebit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        specificDebitDetailsWindow.ChoosePremiumMonth("Jun");
//        driver.get(Constants.SPECIFICDEBIT_URL);
//        Random random = new Random();
//        try {
//            Thread.sleep(5L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        String policy = specificDebitPage.getSubmittedPolicies()
//                .get(random.nextInt(specificDebitPage.getSubmittedPolicies().size()))
//                .findElement(By.xpath("//body[1]/div[7]/div[2]/form[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]"))
//                .getText();
//        specificDebitPage.searchSpecificDebit(policy);
//        specificDebitPage.getAnUnSubmittedPolicy();
    }

    @When("delete a saved  specific debit before it has been submitted")
    public void deleteASavedSpecificDebitBeforeItHasBeenSubmitted () {
        //Add more lines
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        specificDebitDetailsWindow.deleteSpecificDebit();
        specificDebitDetailsWindow.confirmDelete();
    }

    @Then("deleting a Specific Debit the {string} column in the database table gets populated")
    public void deletingASpecificDebitTheDeletedColumnInTheDatabaseTableGetsPopulated (String arg0) {

        driver.get(Constants.SPECIFICDEBIT_URL);
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
        Log.info(deletedSpecific.getPremiumMonth());
        assertEquals(1, deletedSpecific.getDeleted());
    }
    @When("Deny Edit Specific Debit after Submission")
    public void denyEditSpecificDebitAfterSubmission () {
        //driver.get(Constants.SPECIFICDEBIT_URL);
        //Has Submitted specific debits
        specificDebitPage.searchSpecificDebit("P0057609002L01");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        specificDebitPage.getLatestSpecificDebit();
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //String month = specificDebitDetailsWindow.getPremiumMonth().getAttribute("value");
        specificDebitDetailsWindow.ChoosePremiumMonth("Aug");
      assertFalse(specificDebitDetailsWindow.isSaveButtonVisible());
    }


    @And("Cannot delete a specific debit after it has been submitted")
    public void cannotDeleteASpecificDebitAfterItHasBeenSubmitted () {
        try{
           assertTrue(specificDebitDetailsWindow.isDeleteButtonVisible());
           specificDebitDetailsWindow.deleteSpecificDebit();
            ExtentCucumberAdapter.getCurrentStep().fail("The specific debit was deleting");
            takeScreenshot();

        }catch (AssertionError e){
            ExtentCucumberAdapter.getCurrentStep().pass("Cannot delete a specific debit after it has been submitted");

        }
        specificDebitDetailsWindow.getCancelBtn().click();
//        // Create an instance of the Random class
//        Random random = new Random();
//
//        // Generate a random integer between 0 and 999 (inclusive)
//        try {
//            Thread.sleep(Duration.ofSeconds(10));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        String policy = specificDebitPage.getSubmittedPolicies()
//                .get(random.nextInt(specificDebitPage.getSubmittedPolicies().size()))
//                .findElement(By.xpath("//body[1]/div[7]/div[2]/form[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]"))
//                .getText();
//        out.println(policy);
//        specificDebitPage.searchSpecificDebit(policy);
//        try {
//            Thread.sleep(Duration.ofSeconds(10));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        specificDebitPage.getASubmittedPolicy();

    }

    @When("Adding for inactive contract payment status")
    public void addingForInactiveContractPaymentStatus () {
    }

    @Then("client contract payment status updates to {string} and contract payment status reason to {string}")
    public void clientContractPaymentStatusUpdatesToActiveAndContractPaymentStatusReasonToClientRequested () {
    }





    @After(order = 1)
    public void  cleanUpScenario(){
        takeScreenshot();
        if(scenarioName.equals("Add Specific Debit")){
            if(specificDebitDetailsWindow.getSpecificDebitDetailsWindow().isDisplayed()){
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5L));
                wait.until(ExpectedConditions.visibilityOf(specificDebitDetailsWindow.getCancelBtn()));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", specificDebitDetailsWindow.getCancelBtn());
                scenario.log("Close");
            }

        }
        if (scenarioName.equals("Delete Specific Debit")){
            DriverSingleton.closeObjectInstance();
        }

    }
    @After(order = 2)
    public void createReport(){
        //extent.flush();
    }


}
