package com.va.corporate.srv.dto;

public class ReportInsansiResponseDto {
    private String va_acc_no_name;
    private String va_acc_no;
    private String created_at;
    private String tanggal_transaksi;
    private String current_os;
    private String debit;
    private String credit;
    private String last_os;
    private String saldo_awal;
    private String saldi_akhir;
    private String company_name;
    private String rekinduk;
    private String corporate_name;

    public ReportInsansiResponseDto() {
    }

    public String getVa_acc_no_name() {
        return va_acc_no_name;
    }

    public void setVa_acc_no_name(String va_acc_no_name) {
        this.va_acc_no_name = va_acc_no_name;
    }

    public String getVa_acc_no() {
        return va_acc_no;
    }

    public void setVa_acc_no(String va_acc_no) {
        this.va_acc_no = va_acc_no;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public String getCurrent_os() {
        return current_os;
    }

    public void setCurrent_os(String current_os) {
        this.current_os = current_os;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getLast_os() {
        return last_os;
    }

    public void setLast_os(String last_os) {
        this.last_os = last_os;
    }

    public String getSaldo_awal() {
        return saldo_awal;
    }

    public void setSaldo_awal(String saldo_awal) {
        this.saldo_awal = saldo_awal;
    }

    public String getSaldi_akhir() {
        return saldi_akhir;
    }

    public void setSaldi_akhir(String saldi_akhir) {
        this.saldi_akhir = saldi_akhir;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getRekinduk() {
        return rekinduk;
    }

    public void setRekinduk(String rekinduk) {
        this.rekinduk = rekinduk;
    }

    public String getCorporate_name() {
        return corporate_name;
    }

    public void setCorporate_name(String corporate_name) {
        this.corporate_name = corporate_name;
    }

    @Override
    public String toString() {
        return "ReportInsansiResponseDto{" +
                "va_acc_no_name='" + va_acc_no_name + '\'' +
                ", va_acc_no='" + va_acc_no + '\'' +
                ", created_at='" + created_at + '\'' +
                ", tanggal_transaksi='" + tanggal_transaksi + '\'' +
                ", current_os='" + current_os + '\'' +
                ", debit='" + debit + '\'' +
                ", credit='" + credit + '\'' +
                ", last_os='" + last_os + '\'' +
                ", saldo_awal='" + saldo_awal + '\'' +
                ", saldi_akhir='" + saldi_akhir + '\'' +
                ", company_name='" + company_name + '\'' +
                ", rekinduk='" + rekinduk + '\'' +
                ", corporate_name='" + corporate_name + '\'' +
                '}';
    }
}
