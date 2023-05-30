package org.mobilife.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.mobilife")
public class AutomationFrameworkConfiguration {
    public AutomationFrameworkConfiguration (){}

}
