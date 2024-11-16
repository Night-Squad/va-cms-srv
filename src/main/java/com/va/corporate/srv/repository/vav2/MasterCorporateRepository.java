package com.va.corporate.srv.repository.vav2;


import com.va.corporate.srv.models.vav2.MasterCorporateModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class MasterCorporateRepository {

    private static final String FINDALL = "SELECT * FROM master_corporation WHERE 1=1";

    private static final String COUNTALL = "SELECT COUNT(*) FROM master_corporation WHERE 1=1";


    private final JdbcTemplate jdbcTemplate;

    public MasterCorporateRepository(@Qualifier("vav2JdbcTemplate")JdbcTemplate vav2JdbcTemplate) {
        this.jdbcTemplate = vav2JdbcTemplate;
    }

    public List<MasterCorporateModel> findAll(int page, int size, Map<String, String> searching, List<String> intColumn) {
        int offset = (page - 1) * size;

        // Build dynamic SQL query
        StringBuilder sql = new StringBuilder(FINDALL);
        System.out.println("sql before search param builder : "+sql.toString());
        List<Object> params = this.buildSearchParams(searching, sql, intColumn);

        // Add pagination
        sql.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(offset);

        System.out.println("sql : "+sql.toString());

        return jdbcTemplate.query(sql.toString(), params.toArray(),
                new BeanPropertyRowMapper<>(MasterCorporateModel.class));
    }

    private List<Object> buildSearchParams(Map<String, String> searching, StringBuilder sql, List<String> intColumn) {
        List<Object> params = new ArrayList<>();
        List<String> validColumns = Arrays.asList("corporate_name", "created_by", "updated_by", "id"); // Replace with actual columns


        searching.forEach((key, value) -> {
            if (validColumns.contains(key) && value != null && !value.trim().isEmpty()) {

                if (intColumn.contains(key)) {
                    // int value
                    sql.append(" AND ").append(key).append(" = ?");
                    Integer intValue = Integer.valueOf(value);
                    params.add(intValue);

                } else {
                    // string value
                    sql.append(" AND ").append(key).append(" LIKE ?");
                    params.add("%" + value + "%");
                }


            }
        });

        return params;
    }

    public int countAll(Map<String, String> searching, List<String> intColumn) {
        // Build dynamic SQL query
        StringBuilder sql = new StringBuilder(COUNTALL);
        List<Object> params = buildSearchParams(searching, sql, intColumn);

        System.out.println("count all sql : "+sql.toString());

        return jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
    }


}
