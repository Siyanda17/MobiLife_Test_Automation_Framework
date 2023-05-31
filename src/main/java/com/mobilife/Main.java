package com.mobilife;


import com.mobilife.Driver.DriverSingleton;
import com.mobilife.Utilities.Constants;
import com.mobilife.Utilities.FrameworkProperties;
import com.mobilife.pages.LoginPage.LoginPage;
import com.mobilife.pages.MainPage.MainPage;
import com.mobilife.pages.SpecificDebit.SpecificDebitPage;
import com.mobilife.pages.SpecificDebit.SpecificDebitDetailsWindow;
import org.openqa.selenium.WebDriver;


public class Main {


    public static void main(String[] args) {

        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));
        WebDriver driver = DriverSingleton.getDriver();
       // Main main = new Main();

        driver.get("https://mobilife-main-preprod.stratusolvecloud.com/");

      //  main.specificDebitTable = main.jdbcTemplate.queryForObject("SELECT * FROM SpecificDebit", main.specificDebitRowMapper);
        //System.out.println(main.specificDebitTable.getPolicyAmount());
        LoginPage loginPage = new LoginPage();
        loginPage.login(frameworkProperties.getProperty("username"),frameworkProperties.getProperty(Constants.PASSWORD));
        loginPage.AuthenticateLogin();

        MainPage mainPage = new MainPage();
        mainPage.GoToSpecificDebitPage();
        SpecificDebitPage specificDebitPage = new SpecificDebitPage();
        specificDebitPage.AddSpecificDebit();
        SpecificDebitDetailsWindow specificDebitDetailsWindow = new SpecificDebitDetailsWindow();
        specificDebitDetailsWindow.SearchForUniquePolicy("P0054805802LA1");

        specificDebitDetailsWindow.SelectPolicy();
        specificDebitDetailsWindow.ChoosePremiumMonth("Mar");
        specificDebitDetailsWindow.setActionDate("25","05","2023");
//        specificDebitPage.searchSpecificDebit();
//        specificDebitPage.getLatestSpecificDebit();
//        //DriverSingleton.closeObjectInstance();
    }


}