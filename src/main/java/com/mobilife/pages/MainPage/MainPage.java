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
    private final WebDriver driver;

    public MainPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".dropdown-toggle.rippleclick")
    private WebElement myProfileSetting;

    @FindBy(css = "a[class='dropdown-toggle']")
    private  WebElement cogElement;

    @FindBy(css = "#c1")
    private WebElement welcomeMessage;

    /*
    *
    * */

    public void ClickMyProfile(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
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
       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
       wait.until(ExpectedConditions.visibilityOf(policyManagement));
        policyManagement.click();
        specificDebit.click();
    }

    public WebElement getWelcomeMessageElement () {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10L));
        return welcomeMessage;
    }
    public String getMessage(){
        WebDriverWait  wait = new WebDriverWait(driver,Duration.ofSeconds(15L));
        wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
        return welcomeMessage.getText().substring(0,7);
    }
}
