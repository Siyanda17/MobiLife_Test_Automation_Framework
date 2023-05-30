package org.mobilife.Utilities;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

//Component ensure inversion control
//This is basically the service class
@Component
@PropertySources({
        @PropertySource(value = "classpath:framework.properties", factory = CaseSensitivePropertySourceFactory.class)
})
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

    public String getDatabaseHost () {
        return databaseHost;
    }

    public String getDatabaseUsername () {
        return databaseUsername;
    }

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

    public String getBrowser () {
        return browser;
    }

    public String getUsername () {
        System.out.println(username);
        return username;
    }

    public String getPassword () {
        return password;
    }
}
