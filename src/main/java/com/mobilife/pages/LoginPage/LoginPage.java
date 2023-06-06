package com.mobilife.pages.LoginPage;

import com.mobilife.Driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


/**
 * Represents the  Login Page
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
public class LoginPage {
//    private static final By LOGIN_BUTTON = By.xpath("//button[@id='c10']");
//    private static final By INPUT_USERNAME = By.xpath("//input[@id='c6']");
//    private static final By INPUT_PASSWORD = By.xpath("//input[@id='c7']");
//    private static final By  AUTHENTICATE = By.xpath("(//button[normalize-space()='Authenticate'])[1]");
    private final WebDriver driver;
    public LoginPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
    //getting the Username textfield
    @FindBy(id = "c6")
    @CacheLookup
    private WebElement loginUsername;

    @FindBy(xpath = "//input[@id='c7']")
    @CacheLookup
    private WebElement loginPassword;

    @FindBy(css = "#c10")
    @CacheLookup
    private WebElement loginButton;

    @FindBy(id = "notedFeedbackText")
    @CacheLookup
    private WebElement oneTimePin;

    @FindBy(css = "#c1")
    @CacheLookup
    private WebElement oneTimePinTextbox;

    @FindBy(xpath = "(//button[normalize-space()='Authenticate'])[1]" )
    @CacheLookup
    private WebElement authenticate;

    @FindBy(css = ".swal2-popup.swal2-modal.swal2-show")
    @CacheLookup
    private WebElement incorrectInformationError;

    /**
     * Login to the Mobility Web Application
     *
     * @param username the username credential for the user
     * @param password  the password credential for the password
     *
     * */
    public void login(String username, String password){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginUsername.sendKeys(username);
        loginPassword.sendKeys(password);
        loginButton.click();
    }
    /**
     * AuthenticationLogin() Handles the OTP Authentication when logging in to mobility
     * */
    public void AuthenticateLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(oneTimePin));
        String text = oneTimePin.getText().substring(oneTimePin.getText().indexOf(":")+2,(oneTimePin.getText().indexOf(":")+2)+6);
        oneTimePinTextbox.sendKeys(text);
        authenticate.click();
    }

    public WebElement getIncorrectInformationError () {
        return incorrectInformationError;
    }

    public WebElement getOneTimePin () {
        return oneTimePin;
    }
}
