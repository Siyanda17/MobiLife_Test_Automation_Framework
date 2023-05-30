package org.mobilife.pages.SpecificDebit;

import org.mobilife.Driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SpecificDebitDetailsWindow {
    private WebDriver driver;
    public SpecificDebitDetailsWindow(){
    driver = DriverSingleton.getDriver();
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
     * This method is required after policy search
     * */
    public void SelectPolicy(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        selectPolicy.click();
    }
    /**
     * Use three letter abbreviation for the months
     * */
    public void ChoosePremiumMonth(String month){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        premiumMonth.click();
        Select date = new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
        List<WebElement> allSelectedOptions = date.getAllSelectedOptions();
        int index = 0;
        switch (month){
            case "Jan":
                index = 0;
                break;
            case "Feb":
                index = 1;
                break;
            case "Mar":
                index = 2;
                break;
            case "Apr":
                index = 3;
                break;
            case "May":
                index = 4;
                break;
            case "Jun":
                index = 5;
                break;
            case "Jul":
                index = 6;
                break;
            case "Aug":
                index = 7;
                break;
            case "Sep":
                index = 8;
                break;
            case "Oct":
                index = 9;
                break;
            case "Nov":
                index = 10;
                break;
            case "Dec":
                index = 11;
                break;

        }
        date.selectByIndex(index);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(premiumMonthDialogDoneBtn));
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
    public void writeNotes(String note){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        notesBox.sendKeys(note);
    }
    public String getPolicyNumber(){
        return policyNumber.getAttribute("value");
    }

    public String getAmount(){
        return policyAmount.getAttribute("value");
    }
    public String getPremiumMonth(){
        return premiumMonth.getAttribute("value");
    }
    public String getActionDate(){
        return  actionDate.getAttribute("value");
    }
    public String getNotes(){
        return notesBox.getText();
    }
    public void deletePolicy(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        deleteBtn.click();
    }

}
