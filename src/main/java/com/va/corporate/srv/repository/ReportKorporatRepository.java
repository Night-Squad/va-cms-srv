package com.va.corporate.srv.repository;


import com.va.corporate.srv.repository.queries.GeneralHandlerQuery;
import com.va.corporate.srv.repository.queries.ReportKorporatQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportKorporatRepository {

    private final JdbcTemplate jdbcTemplate;

    private ReportKorporatQueries reportKorporatQueries;

    @Autowired
    private GeneralHandlerQuery generalHandlerQuery;

    public ReportKorporatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
