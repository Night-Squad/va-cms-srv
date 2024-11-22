package com.va.corporate.srv.service;


import com.va.corporate.srv.controller.dto.PaginatedResponseDto;
import com.va.corporate.srv.domain.ReportInstansiDomain;
import com.va.corporate.srv.repository.ReportInstansiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class ReportInstansiService {

    @Autowired
    private final ReportInstansiRepository repository;

    public ReportInstansiService(ReportInstansiRepository repository) {
        this.repository = repository;
    }

    public PaginatedResponseDto<ReportInstansiDomain> getPaginationReportInstansi(int page, int size, Map<String, String> searching) {

        System.out.println("Searching value : "+searching);

        List<ReportInstansiDomain> reportInstansi = null;

        int totalItems = 0;
        int totalPages = 0;
        try {

            // specify int column by adding intColumnn
            List<String> intColumn = new ArrayList<>();
            intColumn.add("id");

            // specify valid columns
            List<String> validColumns = Arrays.asList("id","created_by");

            totalItems = repository.countAll(searching, intColumn, validColumns);
            totalPages = (int) Math.ceil((double) totalItems / size);

            reportInstansi = repository.findAllQueryDynamic(page, size, searching, intColumn, validColumns);


        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }

        return new PaginatedResponseDto<>(reportInstansi, page, totalPages, totalItems);
    }
}
