package com.va.corporate.srv.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.va-cms")
    public DataSourceProperties vacmsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource vacmsDataSource() {
        return vacmsDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSourceTransactionManager vacmsTransactionManager() {
        return new DataSourceTransactionManager(vacmsDataSource());
    }

    @Bean
    @ConfigurationProperties("spring.datasource.va-v2")
    public DataSourceProperties vav2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource vav2DataSource() {
        return vav2DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSourceTransactionManager vav2TransactionManager() {
        return new DataSourceTransactionManager(vav2DataSource());
    }

}
