package com.va.corporate.srv.dto;

import java.util.List;

public class PaginatedResponseReportDto<T> extends PaginatedResponseDto<T>{

    private PaginatedSummaryDto summary;
    private PaginatedSummaryDto totalMutasi;


    public PaginatedResponseReportDto(List<ReportInsansiDataResponseDto> rows, int current_page, int total_page, long total_data, int max_page) {
        super((List<T>) rows, current_page, total_page, total_data, max_page);
    }

    public PaginatedResponseReportDto(
            List<ReportInsansiDataResponseDto> reportInstansi,
            int page,
            int totalPages,
            int totalItems,
            int size,
            PaginatedSummaryDto summary
    ) {
        super((List<T>) reportInstansi, page, totalPages, totalItems, size);
        this.summary = summary;
        this.totalMutasi = summary;
    }

    public PaginatedSummaryDto getSummary() {
        return summary;
    }

    public void setSummary(PaginatedSummaryDto summary) {
        this.summary = summary;
    }

    public PaginatedSummaryDto getTotalMutasi() {
        return totalMutasi;
    }

    public void setTotalMutasi(PaginatedSummaryDto totalMutasi) {
        this.totalMutasi = totalMutasi;
    }
}
