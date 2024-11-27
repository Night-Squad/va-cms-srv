package com.va.corporate.srv.repository;
import com.va.corporate.srv.dto.ReportInsansiDataResponseDto;
import com.va.corporate.srv.repository.queries.GeneralHandlerQuery;
import com.va.corporate.srv.repository.queries.ReportInstansiQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportInstansiRepository {

    private final JdbcTemplate jdbcTemplate;

    private ReportInstansiQueries reportInstansiQueries;

    @Autowired
    private GeneralHandlerQuery generalHandlerQuery;

    public ReportInstansiRepository(@Qualifier("vav2JdbcTemplate") JdbcTemplate vav2JdbcTemplate) {
        this.jdbcTemplate = vav2JdbcTemplate;
    }

    public List<ReportInsansiDataResponseDto> findAllQueryDynamic(int page, int size, Map<String, String> searching, List<String> intColumn, List<String> validColumns) {
        System.out.println("findAllQueryDynamic ===>");
        int offset = (page - 1) * size;


        // Handling placehodler
        Map<String, String> queryValue = new HashMap<>();
        queryValue.put("start_date", searching.get("start_date"));
        queryValue.put("end_date", searching.get("end_date"));
        queryValue.put("corporate_id", searching.get("corporate_id"));

        String finalQuery = generalHandlerQuery.replacePlaceholders(ReportInstansiQueries.FINDALL, searching);

        // Build dynamic SQL query
        StringBuilder sql = new StringBuilder(finalQuery);
//        List<Object> params = generalHandlerQuery.buildSearchParams(searching, sql, intColumn, validColumns);
        List<Object> params = new ArrayList<>();

        // Add pagination
        sql.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(offset);

        System.out.println("sql : "+sql);
        System.out.println("Named Parameters: " + params);

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(ReportInsansiDataResponseDto.class));
    }

    public int countAll(Map<String, String> searching, List<String> intColumn, List<String> validColumns) {
        // main query place holder
        String mainQuery = generalHandlerQuery.replacePlaceholders(ReportInstansiQueries.FINDALL, searching);

        Map<String, String> all_query = new HashMap<>();
        all_query.put("all_query", mainQuery);

        String finalQuery = generalHandlerQuery.replacePlaceholders(ReportInstansiQueries.COUNTALL, all_query);

//        List<Object> params = generalHandlerQuery.buildSearchParams(searching, sql, intColumn, validColumns);

        List<Object> params = new ArrayList<>();

        // System.out.println("count all sql : "+finalQuery);
        // Build dynamic SQL query
        StringBuilder sql = new StringBuilder(finalQuery);

        return jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
    }


}
