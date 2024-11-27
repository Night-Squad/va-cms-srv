package com.va.corporate.srv.service;

import com.va.corporate.srv.dto.PaginatedResponseDto;
import com.va.corporate.srv.dto.PaginatedSummaryDto;
import com.va.corporate.srv.models.vav2.MasterCorporateModel;
import com.va.corporate.srv.repository.vav2.MasterCorporateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
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

            // specify valid columns
            List<String> validColumns = Arrays.asList("corporate_name", "created_by", "updated_by", "id", "start_date", "end_date");

            totalItems = repository.countAll(searching, intColumn, validColumns);
            totalPages = (int) Math.ceil((double) totalItems / size);

            masterCorporates = repository.findAllQueryDynamic(page, size, searching, intColumn, validColumns);

        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }

        return new PaginatedResponseDto<>(masterCorporates, page, totalPages, totalItems, size);
    }

    public void addMasterCorporation(@Valid MasterCorporateModel masterCorporateModel) {
        try {
            masterCorporateModel.setCreatedAt(LocalDateTime.now());
            masterCorporateModel.setCreatedBy("system");
            masterCorporateModel.setIsActive(true);
            this.repository.addMasterCorporation(masterCorporateModel);
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }
    }

    public void updateMasterCorporation(@Valid MasterCorporateModel masterCorporateModel, Long id) {
        try {
            masterCorporateModel.setUpdatedAt(LocalDateTime.now());
            masterCorporateModel.setUpdatedBy("system");
            masterCorporateModel.setId(id);
            this.repository.updateMasterCorporation(masterCorporateModel);
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }
    }

    public void deleteMasterCorporation(Long id) {
        this.repository.deleteMasterCorporation(id);
    }

}
