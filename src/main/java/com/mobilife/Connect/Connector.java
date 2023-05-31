package com.mobilife.Connect;

import com.mobilife.Utilities.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class Connector {

    private Connection connection;

    @Autowired
    ConfigurationProperties configurationProperties;
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
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


}
