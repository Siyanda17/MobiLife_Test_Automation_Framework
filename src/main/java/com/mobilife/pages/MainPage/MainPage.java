package com.mobilife.pages.MainPage;

import com.mobilife.Driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


/**
 * Represents the Main Page
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
public class MainPage {
    private WebDriver driver;

    public MainPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".dropdown-toggle.rippleclick")
    private WebElement myProfileSetting;

    @FindBy(css = "a[class='dropdown-toggle']")
    private  WebElement cogElement;

    /*
    *
    * */

    public void ClickMyProfile(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5l));
        wait.until(ExpectedConditions.elementToBeClickable(myProfileSetting));
        myProfileSetting.click();
    }
    //Policy Management
    @FindBy(xpath = "//a[normalize-space()='Policy Management']")
    WebElement policyManagement;

    // Specific Debit
    @FindBy(xpath = "(//a[normalize-space()='Specific Debits'])[1]")
    WebElement specificDebit ;

    public void GoToSpecificDebitPage(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        policyManagement.click();
        specificDebit.click();
    }


}
