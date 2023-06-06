package com.mobilife.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class.getName());

    public static Logger getLogData(String className){
        String path = new File("").getAbsolutePath();
       // DOMConfigurator.configure("log4j.xml");
        return LoggerFactory.getLogger(className);
    }

    public static void startTest(String testName){
        logger.info("Test called: " + testName + " has started");
    }

    public static void endTest(String testName){
        logger.info("Test called: " + testName + " has ended");
    }

    public static void info(String message){
        logger.info(message);
    }

    public static void warn(String message){
        logger.warn(message);
    }

    public static void error(String message){
        logger.error(message);
    }

    public static void fatal(String message){
      //  logger.fatal(message);
    }

    public static void debug(String message){
        logger.debug(message);
    }
}
