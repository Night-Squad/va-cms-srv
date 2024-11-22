package com.va.corporate.srv.domain;

public record ReportInstansiDomain(
    String nama_rekening,
    String nomor_rekening_va,
    String tgl_transaksi_terakhir,
    String saldo_awal,
    String debit,
    String credit,
    String saldo_akhir
) {
}
