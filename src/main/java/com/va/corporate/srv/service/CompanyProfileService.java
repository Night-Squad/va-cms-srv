package com.va.corporate.srv.service;


import com.va.corporate.srv.dto.PaginatedResponseDto;
import com.va.corporate.srv.models.vacms.CompanyProfileModel;
import com.va.corporate.srv.repository.vacms.CompanyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class CompanyProfileService {


    @Autowired
    private final CompanyProfileRepository repository;

    public CompanyProfileService(CompanyProfileRepository repository) {
        this.repository = repository;
    }

    public PaginatedResponseDto<CompanyProfileModel> getPaginatedCompanyProfile(int page, int size) {
        List<CompanyProfileModel> companyProfiles = null;

        int totalItems = 0;
        int totalPages = 0;
        try {
            totalItems = repository.countAll();
            totalPages = (int) Math.ceil((double) totalItems / size);

            companyProfiles = repository.findAll(page, size);
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }

        return new PaginatedResponseDto<>(companyProfiles, page, totalPages, totalItems);

    }

    public void addCompanyProfile(@Valid CompanyProfileModel companyProfile) {
        try {
            companyProfile.setCreatedAt(LocalDateTime.now());
            this.repository.addCompanyProfile(companyProfile);
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }
    }
}
