package com.nhnacademy.screattest.config;

import com.nhnacademy.screattest.credentials.DatabaseCredentials;
import com.nhnacademy.screattest.service.SecureKeyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private SecureKeyManagerService secureKeyManagerService;

    @Bean
    public DataSource dataSource(){

        String databaseInfo = secureKeyManagerService.fetchSecretFromKeyManager();
        DatabaseCredentials databaseCredentials = new DatabaseCredentials(databaseInfo);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(databaseCredentials.getUrl());
        dataSource.setUsername(databaseCredentials.getUsername());
        dataSource.setPassword(databaseCredentials.getPassword());
        return dataSource;
    }
}
