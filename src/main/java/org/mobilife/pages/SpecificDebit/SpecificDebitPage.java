package org.mobilife.pages.SpecificDebit;

import org.mobilife.Driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SpecificDebitPage {
    private WebDriver driver;
    @FindBy(css = "#c9_searchBox")
    private WebElement searchBox;

    @FindBy(xpath = "//table[@class='table table-hover table-striped table-bordered mrg-top10']")
    private WebElement table;

    @FindBy(id = "c2")
    private WebElement AddSpecificDebitBtn ;// = wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[@id='c2']")))
    @FindBy(id = "c37")
    private WebElement importSpecificDebitBtn;

    public SpecificDebitPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }


    public void searchSpecificDebit(String policy){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        searchBox.sendKeys(policy);
        searchBox.sendKeys(Keys.ENTER);
    }


    // Get the last row




    public void getLatestSpecificDebit(){
        List<WebElement> rows = getRows();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement lastRow = rows.get(rows.size() - 1);
        lastRow.click();
    }


    public List<WebElement> getRows () {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows;
    }


    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(15000));

    public void AddSpecificDebit(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        AddSpecificDebitBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(15000));
        //Specific Debit Details Window will appear
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
    }

}
