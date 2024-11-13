//package com.va.corporate.srv.config;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//    @Bean
//    @ConfigurationProperties("spring.datasource.va-cms")
//    public DataSourceProperties primaryDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    public DataSource primaryDataSource() {
//        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.va-v2")
//    public DataSourceProperties secondaryDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    public DataSource secondaryDataSource() {
//        return secondaryDataSourceProperties().initializeDataSourceBuilder().build();
//    }
//
//    // JdbcTemplate beans for each DataSource
//    @Bean
//    public JdbcTemplate primaryJdbcTemplate(@Qualifier("vaCmsDataSource") DataSource primaryDataSource) {
//        return new JdbcTemplate(primaryDataSource);
//    }
//
//    @Bean
//    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("vaV2DataSource") DataSource secondaryDataSource) {
//        return new JdbcTemplate(secondaryDataSource);
//    }
//}
