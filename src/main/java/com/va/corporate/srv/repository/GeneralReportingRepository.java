package com.va.corporate.srv.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralReportingRepository {

    private final JdbcTemplate jdbcTemplate;

    public GeneralReportingRepository(@Qualifier("vav2JdbcTemplate") JdbcTemplate vav2JdbcTemplate) {
        this.jdbcTemplate = vav2JdbcTemplate;
    }




}
