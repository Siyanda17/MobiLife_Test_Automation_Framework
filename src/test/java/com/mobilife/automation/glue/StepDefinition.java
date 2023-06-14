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
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;

import java.time.DayOfWeek;
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
      //  test = ExtentCucumberAdapter.getCurrentScenario();
        scenario.log(scenarioName);

//        test = extent.createTest(scenarioName).assignAuthor("Yakhuxolo Mxabo")
//                .assignCategory("Specific Debit Regression").assignDevice(configurationProperties.getBrowser());

        driver = DriverSingleton.getDriver();
//
//        if (scenarioName.equals("Delete Specific Debit")) {
//            specificDebitDetailsWindow.getCancelBtn().click();
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
        if(scenarioName.equals("Add Specific Debit")){
            specificDebitDetailsWindow.getCancelBtn().click();
            try {
                Thread.sleep(3000);
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
            ExtentCucumberAdapter.getCurrentStep().pass("click login button");
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
        String uniqueText = "P0054805802LA1";
        if(!scenarioName.equals("Add Specific Debit without filling in fields")){
        try {
            sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Policy

        specificDebitDetailsWindow.SearchForUniquePolicy(uniqueText);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));


        assertEquals(uniqueText,policyTableObject.getUniquePolicyNumber());
        ExtentCucumberAdapter.getCurrentStep().pass("Policy Exists on the database");
        try {

            specificDebitDetailsWindow.policyDoesNotExist().isDisplayed() ;
            ExtentCucumberAdapter.getCurrentStep().fail("The policy was not found");
            takeScreenshot();
            Log.error("Policy was not found");
            //This retries to find the policy in case of a not found error
            if(retry_count>Constants.STEP_RETRY){
                findThePolicy();
                retry_count++;
            }


        }catch (NoSuchElementException e){
            retry_count = 0;

            ExtentCucumberAdapter.getCurrentStep().pass("The policy has been selected successfully");

        }
        Log.info(policyTableObject.getUniquePolicyNumber());
        }
        else{// Test if mobility can differentiate if the policy is not registered
            try {
                sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //policy
            specificDebitDetailsWindow.SearchForUniquePolicy(Constants.INCORRECT_POLICY);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try{
                specificDebitDetailsWindow.policyDoesNotExist().isDisplayed() ;
                ExtentCucumberAdapter.getCurrentStep().pass("The Policy not found Dialog pops up");

                // Execute JavaScript code to click the button
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.querySelector(\"button[class='swal2-confirm btn btn-info swal2-styled']\").click();");

                specificDebitDetailsWindow.getSearchUniquePolicy().clear();
                specificDebitDetailsWindow.SearchForUniquePolicy(uniqueText);
            }catch (NoSuchElementException e){
                ExtentCucumberAdapter.getCurrentStep().fail("The Policy not found Dialog is not popping up");
                takeScreenshot();

            }

        }
    }

    @When("the policy is {string}")
    public void iSelectThePolicy (String arg0) {
        specificDebitDetailsWindow.SelectPolicy();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));

        if(specificDebitDetailsWindow.getSelectBadge().getText().equals(arg0)){

            ExtentCucumberAdapter.getCurrentStep().pass("The policy has been selected successfully");

        }else {

            ExtentCucumberAdapter.getCurrentStep().fail("The policy has not been selected successfully");
            Utils.takeScreenshot(scenario);
        }

        Log.info(scenarioName+ ": Select Policy");
    }
    @And("Buttons {string} and {string} Appear an the bottom")
    public void buttonsAndAppearAnTheBottom (String arg0, String arg1) {
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        //Save Button Validation
        try {
            assertTrue(specificDebitDetailsWindow.isSaveButtonVisible());
            Log.info(specificDebitDetailsWindow.getSaveBtn().getText());
            Log.info(arg0);
            assertEquals(arg0,specificDebitDetailsWindow.getSaveBtn().getText());
            ExtentCucumberAdapter.getCurrentStep().pass(arg0+" Button is appearing is Appearing");
        }catch (AssertionError|NoSuchElementException e){
            takeScreenshot();
            Log.error("Button not showing");
            ExtentCucumberAdapter.getCurrentStep().fail(arg0+" Button is not appearing on the window");

        }
        //Cancel Button Validation
        try {
            assertTrue(specificDebitDetailsWindow.isCancelButtonVisible());
            assertEquals(arg1,specificDebitDetailsWindow.getCancelBtn().getText());
            ExtentCucumberAdapter.getCurrentStep().pass(arg1+" Button is appearing");

        }catch (AssertionError|NoSuchElementException e){
            takeScreenshot();
            Log.error("Button not showing");
            ExtentCucumberAdapter.getCurrentStep().fail(arg1+" Button is not appearing on specific debit window");

        }
    }

    @Then("Policy number filed is populated")
    public void policyNumberFiledIsPopulated () {
        try {
            Thread.sleep(2000);

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

        for(int i = 1;i < 7;i++){
            try {
                Thread.sleep(1000);
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

    @Then("Enter a Weekend Action Date and in the past")
    public void enterAWeekendActionDateAndInThePast () {
        specificDebitDetailsWindow.ChoosePremiumMonth("Aug");
        LocalDate date = LocalDate.now();  // Get the current date
        long days = 7;

        for(long i = 0; i < days;i++){
            final LocalDate date1 = date.plusDays(i);
            Log.info(String.valueOf(i));
            Log.info(date.toString());
            Log.info("Day of th week "+date1);
            // Check if the day of the week is either Saturday or Sunday
            if (date1.getDayOfWeek() == DayOfWeek.SATURDAY || date1.getDayOfWeek() == DayOfWeek.SUNDAY) {
                String day = String.format("%02d", date1.getDayOfMonth());
                String month =  String.format("%02d",date1.getMonth().getValue());
                String year = String.valueOf(date1.getYear());
                Log.info("Its the weekend");
                specificDebitDetailsWindow.setActionDate(day,month,year);
                //We have to save first to observe the error codes
                specificDebitDetailsWindow.saveSpecificDebit();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try{
                    assertTrue(specificDebitDetailsWindow.theresAnError());
                    ExtentCucumberAdapter.getCurrentStep().pass("The error is appearing");
                }catch (AssertionError e){
                    takeScreenshot();
                    ExtentCucumberAdapter.getCurrentStep().fail("The Error warning about weekend Specific Debits is not appearing");
                }
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //Clear Text field for next steps
                specificDebitDetailsWindow.getPremiumMonth().clear();
                specificDebitDetailsWindow.getActionDate().clear();
                System.out.println("It's the weekend!");
                break;
            }

        }

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
        if(scenarioName.equals(" Action Date")){
            specificDebitDetailsWindow.saveSpecificDebit();
        }

    }

    @Then("Click Save")
    public void clickSave () {




        specificDebitDetailsWindow.saveSpecificDebit();
        //I want to change the date if it's a duplicate or its gives some error only when its Successful one
        //Condition changed from not equal to Add Specific Debit without filling in fields
        if(scenarioName.equals("Add Specific Debit")){
            if(specificDebitDetailsWindow.isDuplicate()) {
                Log.info("is a duplicate");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10),Duration.ofSeconds(2));
                wait.until(ExpectedConditions.elementToBeClickable(specificDebitDetailsWindow.getDuplicatePopUpBtn()));
                try{
                    specificDebitDetailsWindow.getDuplicatePopUp().isDisplayed();
                    ExtentCucumberAdapter.getCurrentStep().pass("The Duplicate Alert Apppears When there is a Duplicate");
                }catch (NoSuchElementException e){
                    ExtentCucumberAdapter.getCurrentStep().fail("The Duplicate is not Appearing ");
                    takeScreenshot();
                }
                // Execute JavaScript code to click the button
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.querySelector(\"div.swal2-actions > button.swal2-confirm.btn.btn-primary\").click();");

                specificDebitDetailsWindow.ChoosePremiumMonth("Jun");
            }

            // specificDebitDetailsWindow.getDuplicatePopUpBtn().click();
            assertFalse(specificDebitDetailsWindow.isDuplicate());
            //If we can't action the date
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
                    .withTimeout(Duration.ofSeconds(5L))
                    .pollingEvery(Duration.ofSeconds(2L))
                    .ignoring(NoSuchElementException.class,TimeoutException.class);
            fluentWait.until(ExpectedConditions.visibilityOf(specificDebitDetailsWindow.getErrorTextUnderActionDate()));
            //Implement after 14:30
            String expected = "Cutoff time is 14:30 - Please choose tomorrow or later";
            String actualText = specificDebitDetailsWindow.getErrorTextUnderActionDate().getText();
            try {
                assertEquals(expected,actualText);
                assertTrue("Error text",specificDebitDetailsWindow.getErrorTextUnderActionDate().isDisplayed());
                ExtentCucumberAdapter.getCurrentStep().pass("the error for cutoff time is showing");
            }catch (AssertionError e){
                ExtentCucumberAdapter.getCurrentStep().fail("The Cutoff time error is not showing");
                takeScreenshot();
            }

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
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                //Checks if Error is Appearing
                assertTrue(specificDebitDetailsWindow.getErrorTextUnderPremiumMonth().isDisplayed());
                ExtentCucumberAdapter.getCurrentStep().pass("Error Under Premium month is appearing as expected");
            }catch (AssertionError|NoSuchElementException e){
                takeScreenshot();
                ExtentCucumberAdapter.getCurrentStep().fail("Error Under Premium month is not appearing");
            }

        }

        if(specificDebitDetailsWindow.getActionDate().getText().isEmpty()){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                //Checks if Error is Appearing
                assertTrue(specificDebitDetailsWindow.getErrorTextUnderActionDate().isDisplayed());
                ExtentCucumberAdapter.getCurrentStep().pass("Error Under Premium month is appearing as expected");
            }catch (AssertionError|NoSuchElementException e){
                takeScreenshot();
                ExtentCucumberAdapter.getCurrentStep().fail("Error Under Premium month is not appearing");
            }
        }
    }

    @When("Submitted checkbox empty\\(no tick) before the linked collection item is Submitted")
    public void submittedCheckboxEmptyNoTickBeforeTheLinkedCollectionItemIsSubmitted () {
        assertFalse(specificDebitDetailsWindow.isThereASubmittedCheckMark());
        //Check if value from contentValue is the desired

    }

    @And("Cannot tick Submitted checkbox")
    public void cannotTickSubmittedCheckbox () {
        try {
            assertFalse(specificDebitDetailsWindow.getSubmittedBtn().isEnabled());
            ExtentCucumberAdapter.getCurrentStep().pass("The Submit Button is disabled as expected");
        }catch (AssertionError|NoSuchElementException e){
            takeScreenshot();
            specificDebitDetailsWindow.getSubmittedBtn().click();
            ExtentCucumberAdapter.getCurrentStep().fail("The Submit Button should not be enabled");
            Log.error(e.getMessage());
        }

    }

    @And("Can add notes")
    public void canAddNotes () {
        specificDebitDetailsWindow.writeNotes("Help i am trapped in a Specific Debit");
        //To be Modified
        //specificDebitDetailsWindow.getCancelBtn();
    }


    @When("Allow Edit Specific Debit before Submission")
    public void allowEditSpecificDebitBeforeSubmission () {

        specificDebitPage.searchSpecificDebit("P0054805802LA1");
        try {
            Thread.sleep(2000);
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
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //String month = specificDebitDetailsWindow.getPremiumMonth().getAttribute("value");
        specificDebitDetailsWindow.ChoosePremiumMonth("Aug");
      assertFalse(specificDebitDetailsWindow.isSaveButtonVisible());
    }
    @Then("Submitted checkbox ticked after the linked collection item is submitted")
    public void submittedCheckboxTickedAfterTheLinkedCollectionItemIsSubmitted () {//More Code to be added (Database Validation)
        try{
            assertTrue(specificDebitDetailsWindow.isThereASubmittedCheckMark());
            ExtentCucumberAdapter.getCurrentStep().pass("The is a check mark for a Submitted specific debit as expected");
        }catch (AssertionError e){
            takeScreenshot();
            ExtentCucumberAdapter.getCurrentStep().fail("There is no check mark for this Submitted policy");
        }

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

