package com.mobilife.pages.SpecificDebit;

import com.mobilife.Driver.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Represents a Specific Debit Page
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/

public class SpecificDebitPage {
    private final WebDriver driver;
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

    /**
     * Search for Specific Debit on the Specific Debit page
     *
     * @param policy the Unique Policy Number
     *
     * */
    public void searchSpecificDebit(String policy){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        searchBox.sendKeys(policy);
        searchBox.sendKeys(Keys.ENTER);
    }


    // Get the last row



    /**
     * Selects the Latest Specific Debit on the Specific Debit page
     * After you have Searched for the Specific Debit
     * <p>
     * */
    public void getLatestSpecificDebit(){
        List<WebElement> rows = getRows();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement lastRow = rows.get(rows.size() - 1);
        lastRow.click();
    }

    /**
     * Gets all the rows from the Specific Debit Table on the Specific Debit page
     *
     * @return rows a list of rows from Specific Debit Table
     * */
    private List<WebElement> getRows () {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows;
    }
    public List<WebElement> getSubmittedPolicies(){
        List<WebElement> rows = getRows();
        List<WebElement> submittedPolicies = new ArrayList<>();

        for (WebElement row : rows) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script = "return window.getComputedStyle(arguments[0], '::before').content;";

            WebElement cellElement = row.findElement(By.cssSelector("table > tbody > tr:nth-child(8) > td:nth-child(5) > i")); // Replace with appropriate locator for the cell element
            String contentValue = (String) js.executeScript(script, cellElement);
            if(contentValue.equals("\\f00c")){
                submittedPolicies.add(row);
            }
            // String cellText = cellElement.getText();
         //   System.out.println("Cell text: " + cellText);
        }

        return submittedPolicies;
    }


    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(15000));
    /**
     * Add a Specific Debit
     * <p>
     * Clicks add Specific Debit on the Specific Debit page
     * <p>
     * */
    public void AddSpecificDebit(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        AddSpecificDebitBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(15000));
        //Specific Debit Details Window will appear
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
    }

}
