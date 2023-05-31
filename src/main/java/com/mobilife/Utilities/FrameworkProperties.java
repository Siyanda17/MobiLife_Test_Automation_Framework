package com.mobilife.Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 * Represents the Automation Framework Configuration(Scans through the com.mobilife package
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 * @deprecated This class is deprecated. Use the {@link ConfigurationProperties} class instead.
 *
 *
 **/
@Deprecated
public class FrameworkProperties {
    private String result = "";
    private InputStream inputStream;

    public String getProperty(String key) {
        try {
            Properties properties = new Properties();
            String propFileName = Constants.PROP_FILE_NAME;

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if ( inputStream != null )
                properties.load(inputStream);
            else
                throw new FileNotFoundException(Constants.FILE_NOT_FOUND_EXCEPTION_MESSAGE);

            String propertyValue = properties.getProperty(key);
            this.result = propertyValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
