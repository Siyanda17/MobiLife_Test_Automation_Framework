package com.mobilife.Utilities;


import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Constant on the Framework
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
public class Constants {
    public static final String PROP_FILE_NAME = "framework.properties";
    public static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "The Property file has not been found";
    public static final String CHROME = "Chrome";
    public static final String FIREFOX = "Firefox";
    public static final String PHANTOMJS = "PhantomJs";
    public static final String BROWSER = "browser";
    public static final String USERNAME = "Y_Mxabo";
    public static final String URL = "https://mobilife-main-preprod.stratusolvecloud.com/";
    public static final String LOGIN_URL = "https://mobilife-main-preprod.stratusolvecloud.com/UserManagement/login/";
    public static final String SPECIFICDEBIT_URL = "https://mobilife-policies-preprod.stratusolvecloud.com/App/MobiLifeAdmin/SpecificDebit_Overview/";
    public static final String HOME_URL = "https://mobilife-main-preprod.stratusolvecloud.com/App/MobiLifeAdmin/" ;

    public static  final String INCORRECT_POLICY = "Incorrect policy";
    public static final int TIMEOUT = 15;
    public static final int STEP_RETRY = 3;
    public static final String PASSWORD = "password";

    public static final String DATABASE_HOST = "databaseHost";
    public static final String DATABASE_USER = "databaseUsername";
    public static final String DATABASE_PASS = "databasePassword";
    //For Testing specific Debits
    public static List<Integer> testPolicy ;
    static {
        // Initialize the static list
        testPolicy = new ArrayList<>();
        //P0054805802LA1
        testPolicy.add(152738);
        //P0057609002L01
        testPolicy.add(108603);
        //TMA93827
        testPolicy.add(763563);
    }
}
