package com.va.corporate.srv.service;

import com.va.corporate.srv.controller.dto.PaginatedResponseDto;
import com.va.corporate.srv.models.vav2.MasterCorporateModel;
import com.va.corporate.srv.repository.vav2.MasterCorporateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class MasterCorporateService {

    @Autowired
    private final MasterCorporateRepository repository;

    public MasterCorporateService(MasterCorporateRepository repository) {
        this.repository = repository;
    }

    public PaginatedResponseDto<MasterCorporateModel> getPaginatedMasterCorporate(int page, int size) {

        List<MasterCorporateModel> masterCorporates = null;

        int totalItems = 0;
        int totalPages = 0;

        try {
            totalItems = repository.countAll();
            totalPages = (int) Math.ceil((double) totalItems / size);

            masterCorporates = repository.findAll(page, size);
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }

        return new PaginatedResponseDto<>(masterCorporates, page, totalPages, totalItems);
    }
}
