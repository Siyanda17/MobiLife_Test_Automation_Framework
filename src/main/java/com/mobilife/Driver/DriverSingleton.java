package com.mobilife.Driver;

import com.mobilife.Driver.strategies.DriverStrategy;
import com.mobilife.Driver.strategies.DriverStrategyImplementer;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Represents the DriverSingleton
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
