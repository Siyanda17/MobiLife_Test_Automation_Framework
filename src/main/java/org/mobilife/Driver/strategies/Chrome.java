package org.mobilife.Driver.strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Chrome implements DriverStrategy{
    @Override
    public WebDriver setStrategy () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(option);
    }
}
