package com.va.corporate.srv.repository.vacms;

import com.va.corporate.srv.helper.NamingUtils;
import com.va.corporate.srv.models.vacms.CompanyProfileModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.StringJoiner;

@Repository
public class CompanyProfileRepository {

    private static final String FINDALL = "SELECT * FROM company_profile LIMIT ? OFFSET ?";
    private static final String COUNTALL = "SELECT COUNT(*) FROM company_profile";
    private static final String INSERT = "INSERT INTO company_profile (main_color_pallete, second_color_pallete, third_color_pallete, company_logo, company_fav_icon, info_color, error_color, created_at, created_by, is_active, company_id) VALUES (, :main_color_pallete, :second_color_pallete, :third_color_pallete, :company_logo, :company_fav_icon, :info_color, :error_color, :created_at, :created_by, :is_active, :company_id)";
    private final JdbcTemplate jdbcTemplate;

    public CompanyProfileRepository(@Qualifier("vacmsJdbcTemplate") JdbcTemplate vacmsJdbcTemplate) {
        this.jdbcTemplate = vacmsJdbcTemplate;
    }

    public void addCompanyProfile(CompanyProfileModel companyProfileModel) {
        Map<String, Object> columnValues = new HashMap<>();

        // Use reflection to get fields and their values dynamically
        for (Field field : CompanyProfileModel.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(companyProfileModel);
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

        String sql = String.format("INSERT INTO public.company_profile (%s) VALUES (%s)", columns, placeholders);

        // Execute the SQL statement with the collected values
        jdbcTemplate.update(sql, columnValues.values().toArray());
    }


    public List<CompanyProfileModel> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return jdbcTemplate.query(FINDALL, new Object[]{size, offset},
                new BeanPropertyRowMapper<>(CompanyProfileModel.class));
    }

    public int countAll() {
        return jdbcTemplate.queryForObject(COUNTALL, Integer.class);
    }
}
