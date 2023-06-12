package com.mobilife.Utilities;

import com.mobilife.Driver.DriverSingleton;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;



/**
 * Represents the  Utilities
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
public class Utils {
    /**
     * decode64 : Decodes encoded Strings useful for protecting your password
     *
     * @param encodedStr the string that has been encoded
     *
     * @return a decoded string value
     * */
    public static String decode64(String encodedStr){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedStr.getBytes()));
    }
    public static void takeScreenshot(Scenario scenario){
        final byte[] screenshot = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot,"image/png","image");
//        try {
//            FileCopyUtils.copy(file, new File(Constants.SCREENSHOTS_FOLDER + generateRandomString(Constants.SCREENSHOT_NAME_LENGTH) + Constants.SCREENSHOT_EXTENSION));
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
    }
}
