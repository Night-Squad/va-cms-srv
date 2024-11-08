package com.va.cms.srv.service;


import com.va.cms.srv.domain.CompanyProfileDomain;
import com.va.cms.srv.repository.CompanyProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CompanyProfileService {

    private final CompanyProfileRepository repository;

    public CompanyProfileService(CompanyProfileRepository repository) {
        this.repository = repository;
    }

    public List<CompanyProfileDomain> findAllCompanyProfile() {
        return repository.findAll();
    }

    @Transactional
    public void addCompanyProfile()
}
