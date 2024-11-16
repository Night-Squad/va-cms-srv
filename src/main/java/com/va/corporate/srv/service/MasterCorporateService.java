package com.va.corporate.srv.service;

import com.va.corporate.srv.controller.dto.PaginatedResponseDto;
import com.va.corporate.srv.models.vav2.MasterCorporateModel;
import com.va.corporate.srv.repository.vav2.MasterCorporateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class MasterCorporateService {

    @Autowired
    private final MasterCorporateRepository repository;

    public MasterCorporateService(MasterCorporateRepository repository) {
        this.repository = repository;
    }

    public PaginatedResponseDto<MasterCorporateModel> getPaginatedMasterCorporate(int page, int size, Map<String, String> searching) {

        System.out.println("Searching value : "+searching);

        List<MasterCorporateModel> masterCorporates = null;

        int totalItems = 0;
        int totalPages = 0;

        try {

            // specify int column by adding intColumn
            List<String> intColumn = new ArrayList<>();
            intColumn.add("id");

            totalItems = repository.countAll(searching, intColumn);
            totalPages = (int) Math.ceil((double) totalItems / size);

            masterCorporates = repository.findAll(page, size, searching, intColumn);
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }

        return new PaginatedResponseDto<>(masterCorporates, page, totalPages, totalItems);
    }
}
