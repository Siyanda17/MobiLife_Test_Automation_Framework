package org.mobilife.Driver;

import org.mobilife.Driver.strategies.DriverStrategy;
import org.mobilife.Driver.strategies.DriverStrategyImplementer;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static WebDriver driver;

    //We make the constructor private so as to have a single access point
    private DriverSingleton(String driver){
        instantiate(driver);
    }

    public WebDriver instantiate(String strategy){
        DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy(strategy);
        driver = driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public static DriverSingleton getInstance(String driver){
        if(instance == null){
            instance = new DriverSingleton(driver);
        }

        return instance;
    }

    public static void closeObjectInstance() {
        instance = null;
        driver.quit();
    }

    public static WebDriver getDriver() {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        return driver;
    }
}
