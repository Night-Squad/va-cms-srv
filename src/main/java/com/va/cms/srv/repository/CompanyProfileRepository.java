package com.va.cms.srv.repository;

import com.va.cms.srv.domain.CompanyProfileDomain;
import com.va.cms.srv.helper.NamingUtils;
import com.va.cms.srv.models.CompanyProfileModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.StringJoiner;

@Repository
public class CompanyProfileRepository {

    private static final String FINDALL = "SELECT * FROM company_profile";
    private static final String INSERT = "INSERT INTO company_profile (main_color_pallete, second_color_pallete, third_color_pallete, company_logo, company_fav_icon, info_color, error_color, created_at, created_by, is_active, company_id) VALUES (, :main_color_pallete, :second_color_pallete, :third_color_pallete, :company_logo, :company_fav_icon, :info_color, :error_color, :created_at, :created_by, :is_active, :company_id)";
    private final JdbcClient jdbcClient;
    private final JdbcTemplate jdbcTemplate;

    public CompanyProfileRepository(JdbcTemplate jdbcTemplate, JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
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


    public List<CompanyProfileDomain> findAll() {
        return jdbcClient.sql(FINDALL)
                .query(CompanyProfileDomain.class)
                .list();
    }
}
