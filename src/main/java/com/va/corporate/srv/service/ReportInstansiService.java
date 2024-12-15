package com.va.corporate.srv.service;


import com.va.corporate.srv.dto.PaginatedResponseDto;
import com.va.corporate.srv.dto.PaginatedResponseReportDto;
import com.va.corporate.srv.dto.PaginatedSummaryDto;
import com.va.corporate.srv.dto.ReportInsansiDataResponseDto;
import com.va.corporate.srv.repository.ReportInstansiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.sum;

@Service
@Validated
public class ReportInstansiService {

    @Autowired
    private final ReportInstansiRepository repository;

    public ReportInstansiService(ReportInstansiRepository repository) {
        this.repository = repository;
    }

    public PaginatedResponseReportDto<ReportInsansiDataResponseDto> getPaginationReportInstansi(int page, int size, Map<String, String> searching) {

        System.out.println("Searching value : "+searching);

        List<ReportInsansiDataResponseDto> reportInstansi = null;
        PaginatedSummaryDto summary = new PaginatedSummaryDto();

        int totalItems = 0;
        int totalPages = 0;
        try {

            // specify int column by adding intColumnn
            List<String> intColumn = new ArrayList<>();
            intColumn.add("id");
            intColumn.add("corporate_id");

            // specify valid columns
            List<String> validColumns = Arrays.asList("id","created_by", "start_date", "end_date", "corporate_id");


            // data content
            reportInstansi = repository.findAllQueryDynamic(page, size, searching, intColumn, validColumns);

            // count all
            totalItems = repository.countAll(searching, intColumn, validColumns);
            totalPages = (int) Math.ceil((double) totalItems / size);

            // summary
            summary = this.summaryReportInstansi(reportInstansi);


        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }

        return new PaginatedResponseReportDto<>(reportInstansi, page, totalPages, totalItems, size, summary);
    }

    private PaginatedSummaryDto summaryReportInstansi(List<ReportInsansiDataResponseDto> allData) {
        PaginatedSummaryDto summary = new PaginatedSummaryDto();

        try {

            // debuging summary
            System.out.println("==== SUMARY===");
            System.out.println(allData);

            int totalDebit = 0;
            int totalCredit = 0;

            for(int i=0;i<allData.size();i++) {
                totalDebit = Integer.parseInt(summary.getDebit() + allData.get(i).getDebit());
                totalCredit = Integer.parseInt(summary.getKredit() + allData.get(i).getCredit());

                System.out.println("==== total debit : "+totalDebit);
                System.out.println("==== total credit : "+totalCredit);
                summary.setDebit(totalDebit);
                summary.setKredit(totalCredit);
            }
            return summary;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
