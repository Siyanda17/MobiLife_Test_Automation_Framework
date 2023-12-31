package com.mobilife.pages.SpecificDebit;

import com.mobilife.Driver.DriverSingleton;
import com.mobilife.Utilities.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Represents a Specific Debit Page
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/

public class SpecificDebitPage {
    @Autowired
    private  WebDriver driver;
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
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchBox));
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement lastRow = rows.get(rows.size() - 1);
        lastRow.click();
    }

    /**
     * Gets all the rows from the Specific Debit Table on the Specific Debit page
     *
     * @return rows a list of rows from Specific Debit Table
     * */
    private List<WebElement> getRows () {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(table));
        return table.findElements(By.tagName("tr"));
    }
    public List<WebElement> getSubmittedPolicies(){
        List<WebElement> rows = getRows();
        List<WebElement> submittedPolicies = new ArrayList<>();
        try {
            sleep(6L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (WebElement row : rows) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script = "return window.getComputedStyle(arguments[0], '::before').content;";

            WebElement cellElement = row.findElement(By.xpath("//body[1]/div[7]/div[2]/form[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[5]/i[1]")); // Replace with appropriate locator for the cell element
            //String contentValue = (String) js.executeScript(script, cellElement);
            String contentValue = cellElement.getCssValue("color");
            System.out.println(contentValue);
            Log.info(contentValue);
            //If green then add
            if(contentValue.equals("rgba(0, 128, 0, 1)")){
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
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
        AddSpecificDebitBtn.click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(15000));
//        //Specific Debit Details Window will appear
//        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
  }

    public void getASubmittedPolicy () {
        List<WebElement> rows = getRows();
        for (WebElement row : rows){

            WebElement cellElement = row.findElement(By.xpath("//tbody/tr[4]/td[5]/i[1]")); // Replace with appropriate locator for the cell element
        //String contentValue = (String) js.executeScript(script, cellElement);
        String contentValue = cellElement.getCssValue("color");
        System.out.println(contentValue);
            if(contentValue.equals("rgba(0, 128, 0, 1)")){
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(row));
                row.click();
                Log.info("Click the row");
                break;
                //break;
            }

        }
    }

    public void getAnUnSubmittedPolicy () {
        List<WebElement> rows = getRows();
        for (WebElement row: rows){
//            try {
//                Thread.sleep(Duration.ofSeconds(10));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            WebElement cellElement = row.findElement(By.cssSelector("tbody tr:nth-child(4) td:nth-child(5)")); // Replace with appropriate locator for the cell element
            //String contentValue = (String) js.executeScript(script, cellElement);
            String contentValue = cellElement.getCssValue("color");
            System.out.println(contentValue);
            if(contentValue.contains("255, 0, 0, 1")){
                row.click();
                Log.info("Click the row");
                break;
            }

        }
    }
    public void selectSubmittedPolicy () {
        List<WebElement> rows = getRows();
       // List<WebElement> submittedPolicies = new ArrayList<>();
        try {
            sleep(6L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (WebElement row : rows) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script = "return window.getComputedStyle(arguments[0], '::before').content;";

            WebElement cellElement = row.findElement(By.xpath("//body[1]/div[7]/div[2]/form[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[5]/i[1]")); // Replace with appropriate locator for the cell element
            //String contentValue = (String) js.executeScript(script, cellElement);
            String contentValue = cellElement.getCssValue("color");
            System.out.println(contentValue);
            Log.info(contentValue);
            //If green then add
            if(contentValue.equals("rgba(0, 128, 0, 1)")){
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
                row.click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
                break;
            }
            // String cellText = cellElement.getText();
            //   System.out.println("Cell text: " + cellText);
        }


    }
}
