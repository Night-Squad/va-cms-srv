package com.va.corporate.srv.repository;


import com.va.corporate.srv.repository.queries.GeneralHandlerQuery;
import com.va.corporate.srv.repository.queries.ReportKorporatQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReportKorporatRepository {

    private final JdbcTemplate jdbcTemplate;

    private ReportKorporatQueries reportKorporatQueries;

    @Autowired
    private GeneralHandlerQuery generalHandlerQuery;

    public ReportKorporatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countAll(Map<String, String> searching, List<String> intColumn, List<String> validColumns) {
        try {

        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }

        return 0;
    }


}
