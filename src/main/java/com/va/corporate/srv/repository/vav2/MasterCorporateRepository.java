package com.va.corporate.srv.repository.vav2;


import com.va.corporate.srv.models.vav2.MasterCorporateModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MasterCorporateRepository {

    private static final String FINDALL = "SELECT * FROM master_corporation LIMIT ? OFFSET ?";

    private static final String COUNTALL = "SELECT COUNT(*) FROM master_corporation";


    private final JdbcTemplate jdbcTemplate;

    public MasterCorporateRepository(@Qualifier("vav2JdbcTemplate")JdbcTemplate vav2JdbcTemplate) {
        this.jdbcTemplate = vav2JdbcTemplate;
    }

    public List<MasterCorporateModel> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return jdbcTemplate.query(FINDALL, new Object[]{size, offset},
                new BeanPropertyRowMapper<>(MasterCorporateModel.class));
    }

    public int countAll() {
        return jdbcTemplate.queryForObject(COUNTALL, Integer.class);
    }


}
