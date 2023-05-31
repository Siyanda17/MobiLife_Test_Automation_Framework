package com.mobilife.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


/**
 * Represents the Automation Framework Configuration(Scans through the com.mobilife package
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
@Configuration
@ComponentScan("com.mobilife")
public class AutomationFrameworkConfiguration {
    public AutomationFrameworkConfiguration (){}

}
