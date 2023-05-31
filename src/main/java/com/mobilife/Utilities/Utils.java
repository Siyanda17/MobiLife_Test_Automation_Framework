package com.mobilife.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

import java.util.Base64;
import java.util.concurrent.TimeUnit;


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
}
