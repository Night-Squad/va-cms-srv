package com.va.corporate.srv.dto;

import java.util.List;

public class PaginatedResponseDto<T> {
    private List<T> rows;
    private int current_page;
    private int total_page;
    private long total_data;

    public PaginatedResponseDto(List<T> rows, int current_page, int total_page, long total_data) {
        this.rows = rows;
        this.current_page = current_page;
        this.total_page = total_page;
        this.total_data = total_data;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public long getTotal_data() {
        return total_data;
    }

    public void setTotal_data(long total_data) {
        this.total_data = total_data;
    }

    @Override
    public String toString() {
        return "PaginatedResponseDto{" +
                "content=" + rows +
                ", currentPage=" + current_page +
                ", totalPages=" + total_page +
                ", totalItems=" + total_data +
                '}';
    }
}
