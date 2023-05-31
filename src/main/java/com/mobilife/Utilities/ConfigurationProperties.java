package com.mobilife.Utilities;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

//Component ensure inversion control
//This is basically the service class

/**
 * Represents the Configuration Properties
 * Gets Data From framework.properties
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 * @see <p><b>framework.properties<b/></p>
 *
 **/
@Component
@PropertySource("framework.properties")
public class ConfigurationProperties {
    //Gets the element from framework properties
    @Value("${browser}")
    private String browser;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${databaseHost}")
    private String databaseHost;

    @Value("${databaseUsername}")
    private String databaseUsername;
    @Value("${databasePassword}")
    private String databasePassword;

    /**
     * Gets Database host from properties
     *
     * @return databaseHost returns database host url
     * */
    public String getDatabaseHost () {
        return databaseHost;
    }
    /**
     * Gets Database Username from properties
     *
     * @return databaseUsername returns database username
     * */
    public String getDatabaseUsername () {
        return databaseUsername;
    }
    /**
     * Gets Database Password from properties
     *
     * @return databasePassword returns database username
     * */
    public String getDatabasePassword () {
        return databasePassword;
    }

    public void setBrowser (String browser) {
        this.browser = browser;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public void setPassword (String password) {
        this.password = password;
    }
    /**
     * Gets Browser name from properties
     *
     * @return browser returns Browser name
     * */

    public String getBrowser () {
        return browser;
    }
    /**
     * Gets Username from properties
     *
     * @return username returns Username
     * */
    public String getUsername () {
        System.out.println(username);
        return username;
    }
    /**
     * Gets Password from properties
     *
     * @return password returns Password
     * */
    public String getPassword () {
        return password;
    }
}
