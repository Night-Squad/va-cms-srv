package com.va.corporate.srv.dto;

import java.util.List;

public class PaginatedResponseDto<T> {
    private List<T> rows;
    private int current_page;
    private int total_page;
    private long total_data;

    private int max_page;


    public PaginatedResponseDto(List<T> rows, int current_page, int total_page, long total_data, int max_page) {
        this.rows = rows;
        this.current_page = current_page;
        this.total_page = total_page;
        this.total_data = total_data;
        this.max_page = max_page;
    }

    public PaginatedResponseDto() {

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

    public int getMax_page() {
        return max_page;
    }

    public void setMax_page(int max_page) {
        this.max_page = max_page;
    }



    @Override
    public String toString() {
        return "PaginatedResponseDto{" +
                "rows=" + rows +
                ", current_page=" + current_page +
                ", total_page=" + total_page +
                ", total_data=" + total_data +
                ", max_page=" + max_page +
                '}';
    }
}
