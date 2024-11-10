package com.va.cms.srv.service;


import com.va.cms.srv.domain.CompanyProfileDomain;
import com.va.cms.srv.models.CompanyProfileModel;
import com.va.cms.srv.repository.CompanyProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class CompanyProfileService {

    private final CompanyProfileRepository repository;

    public CompanyProfileService(CompanyProfileRepository repository) {
        this.repository = repository;
    }

    public List<CompanyProfileDomain> findAllCompanyProfile() {
        return repository.findAll();
    }

    public void addCompanyProfile(@Valid CompanyProfileModel companyProfile) {
        companyProfile.setCreatedAt(LocalDateTime.now());
        this.repository.addCompanyProfile(companyProfile);
    }
}
