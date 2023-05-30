package org.mobilife.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static String decode64(String encodedStr){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedStr.getBytes()));
    }
}
