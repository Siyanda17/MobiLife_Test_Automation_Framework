package org.mobilife.pages.LoginPage;

import org.mobilife.Driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginPage {
//    private static final By LOGIN_BUTTON = By.xpath("//button[@id='c10']");
//    private static final By INPUT_USERNAME = By.xpath("//input[@id='c6']");
//    private static final By INPUT_PASSWORD = By.xpath("//input[@id='c7']");
//    private static final By  AUTHENTICATE = By.xpath("(//button[normalize-space()='Authenticate'])[1]");
    private WebDriver driver;
    public LoginPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
    //getting the Username textfield
    @FindBy(id = "c6")
    private WebElement loginUsername;

    @FindBy(xpath = "//input[@id='c7']")
    private WebElement loginPassword;

    @FindBy(css = "#c10")
    private WebElement loginButton;

    @FindBy(id = "notedFeedbackText")
    private WebElement oneTimePin;

    @FindBy(css = "#c1")
    private WebElement oneTimePinTextbox;

    @FindBy(xpath = "(//button[normalize-space()='Authenticate'])[1]" )
    private WebElement authenticate;
    public void login(String username, String password){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        loginUsername.sendKeys(username);
        loginPassword.sendKeys(password);
        loginButton.click();
    }
    public void AuthenticateLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(oneTimePin));
        String text = oneTimePin.getText().substring(oneTimePin.getText().indexOf(":")+2,(oneTimePin.getText().indexOf(":")+2)+6);
        oneTimePinTextbox.sendKeys(text);
        authenticate.click();
    }

}
