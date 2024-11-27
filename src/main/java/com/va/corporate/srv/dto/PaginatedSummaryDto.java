package com.va.corporate.srv.dto;

public class PaginatedSummaryDto {
    private int saldo_awal = 0;
    private int debit = 0;
    private int kredit = 0;
    private int saldo_akhir = 0;

    public PaginatedSummaryDto(int saldo_awal, int debit, int kredit, int saldo_akhir) {
        this.saldo_awal = saldo_awal;
        this.debit = debit;
        this.kredit = kredit;
        this.saldo_akhir = saldo_akhir;
    }

    public PaginatedSummaryDto() {
    }

    public int getSaldo_awal() {
        return saldo_awal;
    }

    public void setSaldo_awal(int saldo_awal) {
        this.saldo_awal = saldo_awal;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public int getSaldo_akhir() {
        return saldo_akhir;
    }

    public void setSaldo_akhir(int saldo_akhir) {
        this.saldo_akhir = saldo_akhir;
    }
}
