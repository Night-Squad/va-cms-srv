package com.va.corporate.srv.repository.vav2;


import com.va.corporate.srv.helper.NamingUtils;
import com.va.corporate.srv.models.vav2.MasterCorporateModel;
import io.micrometer.common.util.StringUtils;
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

    private static final String FINDALL = "SELECT * FROM master_corporation LIMIT ? OFFSET ?";

    private static String FINDALL_FILTER = "SELECT * FROM master_corporation WHERE corporate_name ILIKE ? LIMIT ? OFFSET ?";

    private static final String COUNTALL = "SELECT COUNT(*) FROM master_corporation";
    private static final String UPDATE = "UPDATE master_corporation SET corporate_name = ? WHERE id = ?";
    private static final String UPDATE_IS_ACTIVE = "UPDATE master_corporation SET is_active = false WHERE id = ?";


    private final JdbcTemplate jdbcTemplate;

    public MasterCorporateRepository(@Qualifier("vav2JdbcTemplate")JdbcTemplate vav2JdbcTemplate) {
        this.jdbcTemplate = vav2JdbcTemplate;
    }

    public List<MasterCorporateModel> findAll(int page, int size, String corporateName) {
        int offset = (page - 1) * size;
        if(StringUtils.isNotBlank(corporateName)) {
            return jdbcTemplate.query(FINDALL_FILTER, new Object[]{"%"+corporateName+"%", size, offset},
                    new BeanPropertyRowMapper<>(MasterCorporateModel.class));
        }
        return jdbcTemplate.query(FINDALL, new Object[]{size, offset},
                new BeanPropertyRowMapper<>(MasterCorporateModel.class));
    }

    public int countAll() {
        return jdbcTemplate.queryForObject(COUNTALL, Integer.class);
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
        jdbcTemplate.update(UPDATE, masterCorporation.getCorporateName(), masterCorporation.getId());
    }

    public void deleteMasterCorporation(Long id){
        jdbcTemplate.update(UPDATE_IS_ACTIVE, id);
    }
}
