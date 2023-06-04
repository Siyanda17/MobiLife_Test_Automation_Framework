package com.mobilife.Connect;

import com.mobilife.Utilities.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Represents the  Connector Configuration
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
@Configuration
public class Connector {

    private Connection connection;

    @Autowired
    ConfigurationProperties configurationProperties;
    /**
     * Creates a dataSource for policies_preprod for now
     *
     * @return dataSource
     * */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(configurationProperties.getDatabaseHost());
        dataSource.setUsername(configurationProperties.getDatabaseUsername());
        dataSource.setPassword(configurationProperties.getDatabasePassword());
        return dataSource;
    }

    public Connection getConnection () {

        return connection;
    }
    /**
     * Represents the JdbcTemplate
     * @author Yakhuxolo Mxabo
     * @version 1.0
     *
     **/
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


}
