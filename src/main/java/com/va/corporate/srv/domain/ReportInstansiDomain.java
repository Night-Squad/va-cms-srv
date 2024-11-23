package com.va.corporate.srv.domain;

public record ReportInstansiDomain(
    String va_acc_no_name,
    String va_acc_no,
    String created_at,
    String tanggal_transaksi,
    String current_os,
    String debit,
    String credit,
    String last_os,
    String saldo_awal,
    String saldi_akhir,
    String company_name,
    String rekinduk,
    String corporate_name
) {
}
