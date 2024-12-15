package com.va.corporate.srv.repository.vav2;


import com.va.corporate.srv.helper.NamingUtils;
import com.va.corporate.srv.models.vav2.MasterCorporateModel;
import com.va.corporate.srv.repository.queries.GeneralHandlerQuery;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Repository
public class MasterCorporateRepository {

    private static final String FINDALL = "SELECT * FROM master_corporation WHERE 1=1";

    private static final String COUNTALL = "SELECT COUNT(*) FROM master_corporation WHERE 1=1";
    private static final String UPDATE = "UPDATE master_corporation SET corporate_name = ?, is_active = ?, updated_at = ?, updated_by = ? WHERE id = ?";
    private static final String UPDATE_IS_ACTIVE = "UPDATE master_corporation SET is_active = false WHERE id = ?";

    @Autowired
    private GeneralHandlerQuery generalHandlerQuery;


    private final JdbcTemplate jdbcTemplate;

    public MasterCorporateRepository(@Qualifier("vav2JdbcTemplate")JdbcTemplate vav2JdbcTemplate) {
        this.jdbcTemplate = vav2JdbcTemplate;
    }

    public List<MasterCorporateModel> findAllQueryDynamic(int page, int size, Map<String, String> searching, List<String> intColumn, List<String> validColumns) {
        int offset = (page - 1) * size;

        // Build dynamic SQL query
        StringBuilder sql = new StringBuilder(FINDALL);
        // System.out.println("sql before search param builder : "+sql.toString());
        List<Object> params = generalHandlerQuery.buildSearchParams(searching, sql, intColumn, validColumns);

        // Add pagination
        sql.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(offset);

        System.out.println("sql : "+sql.toString());

        return jdbcTemplate.query(sql.toString(), params.toArray(),
                new BeanPropertyRowMapper<>(MasterCorporateModel.class));
    }


    public int countAll(Map<String, String> searching, List<String> intColumn, List<String> validColumns) {
        // Build dynamic SQL query
        StringBuilder sql = new StringBuilder(COUNTALL);
        List<Object> params = generalHandlerQuery.buildSearchParams(searching, sql, intColumn, validColumns);

        // Log for debugging
        System.out.println("Generated SQL: " + sql);
        System.out.println("Parameters: " + params);

        System.out.println("count all sql.. : "+sql.toString());

        try {
            return jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Rethrow for debugging
        }


    }

    public void addMasterCorporation(MasterCorporateModel masterCorporation) {
        Map<String, Object> columnValues = new HashMap<>();

        // Use reflection to get fields and their values dynamically
        for (Field field : MasterCorporateModel.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(masterCorporation);
                // System.out.println("Field: " + field.getName() + ", Value: " + value); // Debug log
                if (value != null) {
                    columnValues.put(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access field value", e);
            }
        }

        if (columnValues.isEmpty()) {
            throw new IllegalArgumentException("No fields to insert");
        }

        // Dynamically build the SQL query
        StringJoiner columns = new StringJoiner(", ");
        StringJoiner placeholders = new StringJoiner(", ");
        columnValues.forEach((key, value) -> {
            columns.add(NamingUtils.camelToSnake(key));
            placeholders.add("?");
        });

        String sql = String.format("INSERT INTO public.master_corporation (%s) VALUES (%s)", columns, placeholders);

        // Execute the SQL statement with the collected values
        jdbcTemplate.update(sql, columnValues.values().toArray());
    }

    public void updateMasterCorporation(MasterCorporateModel masterCorporation){
        jdbcTemplate.update(UPDATE,
                masterCorporation.getCorporateName(),
                masterCorporation.getIsActive(),
                masterCorporation.getUpdatedAt(),
                masterCorporation.getUpdatedBy(),
                masterCorporation.getId());
    }

    public void deleteMasterCorporation(Long id){
        jdbcTemplate.update(UPDATE_IS_ACTIVE, id);
    }
}
