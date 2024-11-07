package com.va.cms.srv.repository;

import com.va.cms.srv.domain.CompanyProfileDomain;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyProfileRepository {

    private static final String FINDALL = "SELECT * FROM company_profile";
    private static final String INSERT = "INSERT INTO company_profile (main_color_pallete, second_color_pallete, third_color_pallete, company_logo, company_fav_icon, info_color, error_color, created_at, created_by, is_active, company_id) VALUES (, :main_color_pallete, :second_color_pallete, :third_color_pallete, :company_logo, :company_fav_icon, :info_color, :error_color, :created_at, :created_by, :is_active, :company_id)";
    private final JdbcClient jdbcClient;

    public CompanyProfileRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void addCompanyProfile(CompanyProfileDomain companyProfileDomain) {
        long affected = jdbcClient.sql(INSERT)
                .param("main_color_pallete", companyProfileDomain.mainColorPallete())
                .update();
    }


    public List<CompanyProfileDomain> findAll() {
        return jdbcClient.sql(FINDALL)
                .query(CompanyProfileDomain.class)
                .list();
    }
}
