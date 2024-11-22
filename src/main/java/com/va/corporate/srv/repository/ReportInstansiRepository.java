package com.va.corporate.srv.repository;
import com.va.corporate.srv.domain.ReportInstansiDomain;
import com.va.corporate.srv.repository.queries.GeneralHandlerQuery;
import com.va.corporate.srv.repository.queries.ReportInstansiQueries;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReportInstansiRepository {

    private final JdbcTemplate jdbcTemplate;

    private ReportInstansiQueries reportInstansiQueries;

    private GeneralHandlerQuery generalHandlerQuery;

    public ReportInstansiRepository(@Qualifier("vav2JdbcTemplate") JdbcTemplate vav2JdbcTemplate) {
        this.jdbcTemplate = vav2JdbcTemplate;
    }

    public List<ReportInstansiDomain> findAllQueryDynamic(int page, int size, Map<String, String> searching, List<String> intColumn, List<String> validColumns) {
        int offset = (page - 1) * size;

        // Build dynamic SQL query
        StringBuilder sql = new StringBuilder(ReportInstansiQueries.FINDALL);
        List<Object> params = generalHandlerQuery.buildSearchParams(searching, sql, intColumn, validColumns);

        // Add pagination
        sql.append(" LIMIT :limit OFFSET :offset");
        params.add(size);
        params.add(offset);

        System.out.println("sql : "+sql.toString());

        return jdbcTemplate.query(sql.toString(), params.toArray(),
                new BeanPropertyRowMapper<>(ReportInstansiDomain.class));
    }

    public int countAll(Map<String, String> searching, List<String> intColumn, List<String> validColumns) {
        // Build dynamic SQL query
        StringBuilder sql = new StringBuilder(reportInstansiQueries.COUNTALL);
        List<Object> params = generalHandlerQuery.buildSearchParams(searching, sql, intColumn, validColumns);

        System.out.println("count all sql : "+sql.toString());

        return jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
    }


}
