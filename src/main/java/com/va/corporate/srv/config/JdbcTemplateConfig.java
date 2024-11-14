package com.va.corporate.srv.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {
    @Bean
    @Primary
    public JdbcTemplate vacmsJdbcTemplate(@Qualifier("vacmsDataSource") DataSource vacmsDataSource) {
        return new JdbcTemplate(vacmsDataSource);
    }

    @Bean
    public JdbcTemplate vav2JdbcTemplate(@Qualifier("vav2DataSource") DataSource vav2DataSource) {
        return new JdbcTemplate(vav2DataSource);
    }
}
