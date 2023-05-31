package com.mobilife.pages.SpecificDebit;

import com.mobilife.Driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Represents the Specific Debit Details Window
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
public class SpecificDebitDetailsWindow {
    private WebDriver driver;
    public SpecificDebitDetailsWindow(){
        driver = DriverSingleton.getDriver();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        PageFactory.initElements(driver, this);
    }
    //Specific Debit Details window
    @FindBy(id = "c18")
    private WebElement searchUniquePolicy;
    @FindBy(id = "c19")
    private WebElement findBtn;
    @FindBy(css = ".badge.badge-success.pull-right.item-badge")
    private WebElement selectPolicy;

    @FindBy(id = "c20")
    private WebElement policyNumber;

    @FindBy(id = "c11")
    private  WebElement premiumMonth;

    @FindBy(xpath = "//button[normalize-space()='Done']")
    private WebElement premiumMonthDialogDoneBtn;

    @FindBy(id = "c21")
    private  WebElement collectionMethod;

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

    @FindBy(id = "c14")
    private WebElement notesBox;

    @FindBy(id = "c22")
    private WebElement saveBtn;

    @FindBy(id = "c23")
    private WebElement deleteBtn;

    //Change method to return a Boolean for when we get result
    public void SearchForUniquePolicy(String policy){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        selectPolicy.click();
    }
    /**
     * Sets the premium month
     * Use three letter abbreviation for the months
     * @param month to set the premium months
     * */
    public void ChoosePremiumMonth(String month){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
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
            default -> 0;
        };
        date.selectByIndex(index);
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        actionDate.sendKeys(day+"/"+month+"/"+year);
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
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        notesBox.sendKeys(note);
    }
    /**
     * Gets the policy number from the Specific Debit window
     *
     * @return policyNumber.getAttribute("value") from policy number textbox
     * */
    public String getPolicyNumber(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(policyNumber));
        return policyNumber.getAttribute("value");
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
    public String getPremiumMonth(){
        return premiumMonth.getAttribute("value");
    }
    /**
     * Gets the Action Date from the Specific Debit window
     *
     * @return actionDate.getAttribute("value") from Action Date textbox
     *
     * */
    public String getActionDate(){
        return  actionDate.getAttribute("value");
    }

    /**
     * Gets the Notes from the Specific Debit window
     *
     * @return notesBox.getText() from Notes textbox
     *
     * not sure if it works
     * */
    public String getNotes(){
        return notesBox.getText();
    }
    /**
     * Deletes a Specific Debit
     * */
    public void deleteSpecificDebit(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        deleteBtn.click();
    }

    /**
     * Save the Specific Debit
     * Clicks Save button
     * */
    public void saveSpecificDebit(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        saveBtn.click();
    }
    /**
     * Checks if policy Textbox is enabled
     *
     * @return policyNumber.isEnabled() returns true or false
     * */
    public Boolean isPolicyTextboxEnable(){
        return policyNumber.isEnabled();
    }

}
