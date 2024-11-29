package com.va.corporate.srv.repository.vacms;

import com.va.corporate.srv.helper.NamingUtils;
import com.va.corporate.srv.models.vacms.CompanyProfileModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;

@Repository
public class CompanyProfileRepository {

    private static final String FINDALL = "SELECT * FROM company_profile WHERE 1=1";
    private static final String COUNTALL = "SELECT COUNT(*) FROM company_profile WHERE 1=1";
    private static final String INSERT = "INSERT INTO company_profile (main_color_pallete, second_color_pallete, third_color_pallete, company_logo, company_fav_icon, info_color, error_color, created_at, created_by, is_active, company_id) VALUES (, :main_color_pallete, :second_color_pallete, :third_color_pallete, :company_logo, :company_fav_icon, :info_color, :error_color, :created_at, :created_by, :is_active, :company_id)";
    private static final String FIND_BY_COMPANY_ID = "SELECT * FROM company_profile WHERE company_id = ? and is_active = true ORDER BY id DESC LIMIT 1";
    private static final String FIND_BY_ID = "SELECT * FROM company_profile WHERE id = ?";
    private static final String SET_INACTIVE_RECORD_ON_UPDATE = "UPDATE company_profile SET is_active = false WHERE id <> ? and company_id = ?";
    private static final String GET_COMPANY_CUSTOMIZATION = "select cp.main_color_pallete, cp.second_color_pallete, cp.third_color_pallete, cp.info_color, cp.error_color, cp.company_logo, cp.company_fav_icon from company_profile cp where cp.company_id = ? and cp.is_active = true order by id desc limit 1";
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

    public void updateCompanyProfile(CompanyProfileModel companyProfileModel) {
        Map<String, Object> columnValues = new HashMap<>();

        // Use reflection to get fields and their values dynamically
        for (Field field : CompanyProfileModel.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(companyProfileModel);
                // System.out.println("Field: " + field.getName() + ", Value: " + value); // Debug log
                if (value != null) {
                    if(!field.getName().equals("id"))
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
        StringJoiner columns = new StringJoiner(" = ?, ");
        columnValues.forEach((key, value) -> {
            if(!key.equals("id")) {
                columns.add(NamingUtils.camelToSnake(key));
            }
        });

        String columnsInString = columns.toString().concat(" = ?");

        String sql = String.format("UPDATE public.company_profile SET %s WHERE id = %s", columnsInString, companyProfileModel.getId());

        // Execute the SQL statement with the collected values
        jdbcTemplate.update(sql, columnValues.values().toArray());
    }


    public List<CompanyProfileModel> findAll(int page, int size, Map<String, String> searching) {
        int offset = (page - 1) * size;
        StringBuilder sql = new StringBuilder(FINDALL);
        List<Object> params = new ArrayList<>();
        if(searching.containsKey("company_id")) {
            searching.forEach((key, value) -> {
                if(key.equals("company_id")) {
                    System.out.println("key : "+key);
                    System.out.println("value : "+value);
                    sql.append(" AND ").append(key).append(" = ?");
                    params.add(Integer.valueOf(value));
                }
            });
        }
        // Add pagination
        sql.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(offset);
        return jdbcTemplate.query(sql.toString(), params.toArray(),
                new BeanPropertyRowMapper<>(CompanyProfileModel.class));
    }

    public List<CompanyProfileModel> findByCompanyId(Integer companyId) {
        return jdbcTemplate.query(FIND_BY_COMPANY_ID, new Object[]{companyId},
                new BeanPropertyRowMapper<>(CompanyProfileModel.class));
    }

    public CompanyProfileModel findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(CompanyProfileModel.class),
                id);
    }

    public void setInactive(Integer id, Integer companyId) {
        jdbcTemplate.update(SET_INACTIVE_RECORD_ON_UPDATE, new Object[]{id, companyId});
    }

    public CompanyProfileModel getLoginCustomization(Integer companyId) {
        return jdbcTemplate.queryForObject(GET_COMPANY_CUSTOMIZATION,
                new BeanPropertyRowMapper<>(CompanyProfileModel.class),
                companyId);
    }

    public int countAll(Map<String, String> searching) {
        // Build dynamic SQL query
        StringBuilder sql = new StringBuilder(COUNTALL);
        List<Object> params = new ArrayList<>();
        if(searching.containsKey("company_id")) {
            searching.forEach((key, value) -> {
                if(key.equals("company_id")) {
                    System.out.println("key : "+key);
                    System.out.println("value : "+value);
                    sql.append(" AND ").append(key).append(" = ?");
                    params.add(Integer.valueOf(value));
                }
            });
        }

        return jdbcTemplate.queryForObject(sql.toString(),params.toArray(), Integer.class);
    }
}
