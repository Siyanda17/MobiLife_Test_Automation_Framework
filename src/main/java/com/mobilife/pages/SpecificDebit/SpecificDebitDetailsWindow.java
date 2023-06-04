package com.mobilife.pages.SpecificDebit;

import com.mobilife.Driver.DriverSingleton;
import com.mobilife.Utilities.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.PageFactory.initElements;


/**
 * Represents the Specific Debit Details Window
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
public class SpecificDebitDetailsWindow {
    private final WebDriver driver;



    public SpecificDebitDetailsWindow(){
        driver = DriverSingleton.getDriver();
        initElements(driver, this);
    }
    @FindBy(css = "div[id='SpecificDebitModal'] div[class='modal-content']")
    private WebElement specificDebitDetailsWindow;
    //Specific Debit Details window
    @FindBy(id = "c18")
    private WebElement searchUniquePolicy;
    @FindBy(id = "c19")
    private WebElement findBtn;
    @FindBy(css = ".badge.badge-success.pull-right.item-badge")
    private WebElement selectPolicy;

    @FindBy(xpath = "//input[@id='c20']")
    private WebElement policyNumber;

    @FindBy(id = "c11")
    private  WebElement premiumMonth;

    @FindBy(xpath = "//button[normalize-space()='Done']")
    private WebElement premiumMonthDialogDoneBtn;

    @FindBy(id = "c21")
    private  WebElement collectionMethod;

    @FindBy(id = "c10_validationLabel")
    private WebElement errorTextUnderActionDate;

    @FindBy(id = "c11_validationLabel")
    private WebElement errorTextUnderPremiumMonth;

    @FindBy(xpath = "//div[@class='swal2-popup swal2-modal swal2-show']")
    private WebElement duplicatePopUp;

    @FindBy(css = "button[class='swal2-confirm btn btn-primary']")
    private WebElement duplicatePopUpBtn;

    @FindBy(css = "body > div.swal2-container.swal2-center.swal2-fade.swal2-shown > div")
    private WebElement deleteDialog;

    @FindBy(css = "div.swal2-actions > button.swal2-confirm.btn.btn-success-green")
    private WebElement deleteDialogYes;

    @FindBy(css = "div.swal2-actions > button.swal2-cancel.btn.btn-danger")
    private WebElement deleteDialogNo;

    public WebElement getCollectionMethod () {
        return collectionMethod;
    }
//
//    public void setCollectionMethod (WebElement collectionMethod) {
//        this.collectionMethod = collectionMethod;
//    }

    @FindBy(id ="c10")
    private WebElement actionDate;
    @FindBy(xpath = "//button[normalize-space()='Done']")
    private  WebElement actionDateDoneBtn;

    @FindBy(id = "c12")
    private WebElement policyAmount;

    @FindBy(id = "c13")
    private WebElement submittedBtn;

    @FindBy(css = "#c13 > i")
    private WebElement submittedCheckbox;

    @FindBy(id = "c14")
    private WebElement notesBox;

    @FindBy(id = "c22")
    private WebElement saveBtn;

    @FindBy(id = "c23")
    private WebElement deleteBtn;

    @FindBy(xpath = "//button[@type='button'][normalize-space()='Cancel']")
    private WebElement cancelBtn;

    //Change method to return a Boolean for when we get result
    public void SearchForUniquePolicy(String policy){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(searchUniquePolicy));
        searchUniquePolicy.sendKeys(policy);
        findBtn.click();


    }
    /**
     * Sets the Policy amount on the Specific Debit Page
     * */
    public void setPolicyAmount (String  policyAmount) {
        this.policyAmount.sendKeys(policyAmount);
    }

    /**
     * This method is required after policy search to press select
     * */
    public void SelectPolicy(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.elementToBeClickable(selectPolicy));
        selectPolicy.click();
    }
    /**
     * Sets the premium month
     * Use three letter abbreviation for the months
     * @param month to set the premium months
     * */
    public void ChoosePremiumMonth(String month){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.elementToBeClickable(premiumMonth));
        premiumMonth.click();
        Select date = new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
        List<WebElement> allSelectedOptions = date.getAllSelectedOptions();
        int index = switch (month) {
            case "Jan" -> 0;
            case "Feb" -> 1;
            case "Mar" -> 2;
            case "Apr" -> 3;
            case "May" -> 4;
            case "Jun" -> 5;
            case "Jul" -> 6;
            case "Aug" -> 7;
            case "Sep" -> 8;
            case "Oct" -> 9;
            case "Nov" -> 10;
            case "Dec" -> 11;
            default -> -1;
        };
        date.selectByIndex(index);
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5L));
        wait2.until(ExpectedConditions.elementToBeClickable(premiumMonthDialogDoneBtn));
       premiumMonthDialogDoneBtn.click();
    }
    /**
     * Action date is in the form dd/mm/yyyy
     * */
    public void setActionDate(String day,String month,String year){
        // Write code here that turns the phrase above into concrete actions
        //WebElement datepicker;
        /*
         * Cases that are not For Testing the Action Date*/
        actionDate.sendKeys(day+"-"+month+"-"+year);
        actionDateDoneBtn.click();
//           // datepicker = driver.findElement(By.xpath("//input[@id='c10']"));
//
//            // Click on the datepicker to open it
//            datepicker.click();
//
//            //Date we use today's date
//            LocalDate date = LocalDate.now();
//            // Input the date in the format dd/mm/yyyy
//            datepicker.sendKeys(date.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")));

    }



    /**
    * Write notes on the notes textbox on the Specific Debit Window
    *
    * @param note the text for notes
    * */
    public void writeNotes(String note){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(notesBox));
        notesBox.sendKeys(note);
    }
    /**
     * Gets the policy number from the Specific Debit window
     *
     * @return policyNumber.getAttribute("value") from policy number textbox
     * */
    public WebElement getPolicyNumber(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(policyNumber));
        return policyNumber;
    }
    /**
     * Gets the nett premium from the Specific Debit window
     *
     * @return policyAmount.getAttribute("value") from nett premium textbox
     *
     * */
    public String getAmount(){
        return policyAmount.getAttribute("value");
    }
    /**
     * Gets the premium month from the Specific Debit window
     *
     * @return premiumMonth.getAttribute("value") from premium month textbox
     *
     * */
    public WebElement getPremiumMonth(){
        return premiumMonth;
    }
    /**
     * Gets the Action Date from the Specific Debit window
     *
     * @return actionDate.getAttribute("value") from Action Date textbox
     *
     * */
    public WebElement getActionDate(){
        return  actionDate;
    }

    /**
     * Gets the Notes from the Specific Debit window
     *
     * @return notesBox.getText() from Notes textbox
     *
     * not sure if it works
     * */
    public WebElement getNotes(){
        return notesBox;
    }

    public WebElement getDuplicatePopUp () {
        return duplicatePopUp;
    }

    public WebElement getDuplicatePopUpBtn () {
        return duplicatePopUpBtn;
    }

    public WebElement getErrorTextUnderPremiumMonth () {
        return errorTextUnderPremiumMonth;
    }

    public WebElement getErrorTextUnderActionDate () {
        return errorTextUnderActionDate;
    }

    public WebElement getSubmittedBtn () {
        return submittedBtn;
    }

    public WebElement getSubmittedCheckbox () {
        return submittedCheckbox;
    }

    public WebElement getDeleteDialog () {
        return deleteDialog;
    }

    public WebElement getDeleteDialogYes () {
        return deleteDialogYes;
    }

    public WebElement getDeleteDialogNo () {
        return deleteDialogNo;
    }

    /**
     * Deletes a Specific Debit
     * */
    public void deleteSpecificDebit(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        deleteBtn.click();
    }
    public void confirmDelete(){ WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        wait.until(ExpectedConditions.elementToBeClickable(deleteDialogYes));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"div.swal2-actions > button.swal2-confirm.btn.btn-success-green\").click();");


    }

    /**
     * Save the Specific Debit
     * Clicks Save button
     * */
    public void saveSpecificDebit(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        saveBtn.click();
    }
    public Boolean isDuplicate(){
        boolean isDuplicate;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
            wait.until(ExpectedConditions.visibilityOf(duplicatePopUp));
           // boolean isDisplayed = duplicatePopUp.isDisplayed();
            //assertTrue(duplicatePopUp.isDisplayed());
            isDuplicate = true;
            Log.info("Duplicate True");

        }catch (NullPointerException|TimeoutException e){
            isDuplicate = false;
            Log.info("Duplicate False");


        }





        return isDuplicate;
    }
    /**
     * Checks if policy Textbox is enabled
     *
     * @return policyNumber.isEnabled() returns true or false
     * */
    public Boolean isPolicyTextboxEnable(){
        return policyNumber.isEnabled();
    }

    public WebElement getSpecificDebitDetailsWindow () {
        return specificDebitDetailsWindow;
    }

    public WebElement getCancelBtn () {
        return cancelBtn;
    }
    public Boolean isThereASubmittedCheckMark(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return window.getComputedStyle(arguments[0], '::before').content;";
        String contentValue = (String) js.executeScript(script, submittedCheckbox);
        return contentValue.equals("\\f046");
    }

}
