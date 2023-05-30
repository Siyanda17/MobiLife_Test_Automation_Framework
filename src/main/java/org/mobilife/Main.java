package org.mobilife;


import org.mobilife.Driver.DriverSingleton;
import org.mobilife.Utilities.Constants;
import org.mobilife.Utilities.FrameworkProperties;
import org.mobilife.pages.LoginPage.LoginPage;
import org.mobilife.pages.MainPage.MainPage;
import org.mobilife.pages.SpecificDebit.SpecificDebitDetailsWindow;
import org.mobilife.pages.SpecificDebit.SpecificDebitPage;
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