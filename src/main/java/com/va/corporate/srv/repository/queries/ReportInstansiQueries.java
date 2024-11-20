package com.va.corporate.srv.repository.queries;

public class ReportInstansiQueries {

    public ReportInstansiQueries() {
    }

    public static final String FINDALL = """
            WITH CTE as
                        (
                        SELECT *,
                        ROW_NUMBER() OVER (PARTITION BY va_acc_no ORDER BY created_at desc) AS RN
                        from (
                                select * from (
                                    select
                                        master_customer.value AS va_acc_no_name,
                                    	ts_akhir.va_acc_no,
                                        ts_akhir.created_at,
                                        ts_akhir.created_at AS tgl_transaksi,
                                        COALESCE(current_os, 0) AS current_os,
                                        COALESCE(total_debit, 0) AS debit,
                                        COALESCE(total_credit, 0) AS credit,
                                        last_os,
                                        COALESCE(current_os, 0) AS saldo_awal,
                                        last_os AS saldo_akhir,
                                        master_company.company_name,
                                        master_company.giro_acc_no AS rekinduk,
                                        master_corporation.corporate_name
                                    from (--saldo akhir
                                        WITH CTE AS
                                            (
                                            SELECT * ,ROW_NUMBER() OVER (PARTITION BY va_acc_no ORDER BY created_at desc, id desc) AS RN
                                            FROM (select id, va_acc_no , last_os, created_at, company_id from master_tx where substring(created_at::varchar,1,10) >= ':start_date' and substring(created_at::varchar,1,10) <= 'end_date' and (is_reversal <> true or is_reversal is null)) mst_tx
                                            )
                                        select * FROM CTE WHERE RN = 1
                                    ) ts_akhir
                                    left join --saldo awal
                                        (
                                            WITH CTE AS
                                                (
                                                SELECT * ,ROW_NUMBER() OVER (PARTITION BY va_acc_no ORDER BY created_at asc, id asc) AS RN
                                                FROM (select id, va_acc_no , current_os, created_at from master_tx where substring(created_at::varchar,1,10) >= ':start_date' and substring(created_at::varchar,1,10) <= 'end_date' and (is_reversal <> true or is_reversal is null)) mst_tx
                                                )
                                            select * FROM CTE WHERE RN = 1) ts_awal on ts_akhir.va_acc_no=ts_awal.va_acc_no
                                    left join -- total debit
                                        (
                                            select master_tx.va_acc_no, sum(tx_amount) total_debit from master_tx
                                            where tx_type = 0 and substring(master_tx.created_at::varchar,1,10) >= ':start_date' and substring(master_tx.created_at::varchar,1,10) <= 'end_date' and (is_reversal <> true or is_reversal is null) group by master_tx.va_acc_no
                                        ) ts_tb on ts_akhir.va_acc_no=ts_tb.va_acc_no
                                    left join -- total credit
                                        (
                                            select master_tx.va_acc_no, sum(tx_amount) total_credit from master_tx
                                            where tx_type = 1 and substring(master_tx.created_at::varchar,1,10) >= ':start_date' and substring(master_tx.created_at::varchar,1,10) <= 'end_date' and (is_reversal <> true or is_reversal is null) group by master_tx.va_acc_no
                                        ) ts_tc on ts_akhir.va_acc_no=ts_tc.va_acc_no
                                        LEFT JOIN master_customer ON master_customer.va_acc_no = ts_akhir.va_acc_no
                                        LEFT JOIN master_company ON master_company.id = ts_akhir.company_id
                                        LEFT JOIN master_corporation ON master_corporation.id =  master_company.coporate_id
                                        WHERE master_customer.bit_id = 1 AND master_corporation.id = :corporate_id
                                    ORDER BY ts_akhir.created_at desc
                                ) tbl_hasil
                                union all
                                --untuk  data yg tdk ada berdasarkan filter nya
                                select * from (
                                    select
                                    	master_customer.value AS va_acc_no_name,
                                        ts_akhir.va_acc_no,
                                        ts_akhir.created_at,
                                        ts_akhir.created_at AS tgl_transaksi,
                                        last_os AS current_os,
                                        0 AS debit,
                                        0 AS credit,
                                        last_os,
                                        COALESCE(last_os, 0) AS saldo_awal,
                                        last_os AS saldo_akhir,
                                        master_company.company_name,
                                        master_company.giro_acc_no AS rekinduk,
                                        master_corporation.corporate_name
                                    from (
                                        WITH CTE AS
                                            (
                                            SELECT * ,ROW_NUMBER() OVER (PARTITION BY va_acc_no ORDER BY created_at desc, id desc) AS RN
                                            FROM (select id, va_acc_no , last_os, created_at, company_id from master_tx where substring(created_at::varchar,1,10) < ':end_date' and (is_reversal <> true or is_reversal is null)) mst_tx
                                            )
                                        select * FROM CTE WHERE RN = 1
                                    ) ts_akhir
                                    left join --saldo awal
                                        (WITH CTE as -- saldo awal
                                            (
                                            SELECT *,
                                            ROW_NUMBER() OVER (PARTITION BY va_acc_no ORDER BY created_at asc, id asc) AS RN
                                            FROM (select id,master_tx.va_acc_no, current_os, created_at, tgl_max from master_tx inner join (
                                                        select va_acc_no, max(substring(created_at::varchar,1,10)) tgl_max from master_tx where substring(created_at::varchar,1,10) < ':end_date' and (is_reversal <> true or is_reversal is null) group by va_acc_no
                                                    ) as tbl_get_max on master_tx.va_acc_no = tbl_get_max.va_acc_no and substring(master_tx.created_at::varchar,1,10) = tbl_get_max.tgl_max where substring(master_tx.created_at::varchar,1,10) < ':end_date'
                                                ) mst_tx
                                            )
                                        select va_acc_no , current_os , created_at FROM CTE WHERE RN = 1) ts_awal on ts_akhir.va_acc_no=ts_awal.va_acc_no
                                    left join -- total debit
                                        (
                                            select master_tx.va_acc_no, sum(tx_amount) total_debit from master_tx inner join (
                                                select va_acc_no, max(substring(created_at::varchar,1,10)) tgl_max from master_tx where substring(created_at::varchar,1,10) < ':end_date' and (is_reversal <> true or is_reversal is null) group by va_acc_no
                                            ) as tbl_get_max on master_tx.va_acc_no = tbl_get_max.va_acc_no and substring(master_tx.created_at::varchar,1,10) = tbl_get_max.tgl_max
                                            where tx_type = 0 and substring(master_tx.created_at::varchar,1,10) < ':end_date' group by master_tx.va_acc_no
                                        ) ts_tb on ts_akhir.va_acc_no=ts_tb.va_acc_no
                                    left join -- total credit
                                        (
                                            select master_tx.va_acc_no, sum(tx_amount) total_credit from master_tx inner join (
                                                select va_acc_no, max(substring(created_at::varchar,1,10)) tgl_max from master_tx group by va_acc_no
                                            ) as tbl_get_max on master_tx.va_acc_no = tbl_get_max.va_acc_no and substring(master_tx.created_at::varchar,1,10) = tbl_get_max.tgl_max
                                            where tx_type = 1 and substring(master_tx.created_at::varchar,1,10) < ':end_date' and (is_reversal <> true or is_reversal is null) group by master_tx.va_acc_no
                                        ) ts_tc on ts_akhir.va_acc_no=ts_tc.va_acc_no
                                                            LEFT JOIN "ref_PMK183_level_user" ON "ref_PMK183_level_user"."KDSATKER" = substring(ts_akhir.va_acc_no, 7,6)
                                        LEFT JOIN master_customer ON master_customer.va_acc_no = ts_akhir.va_acc_no
                                        LEFT JOIN master_company ON master_company.id = ts_akhir.company_id
                                        LEFT JOIN master_corporation ON master_corporation.id =  master_company.coporate_id
                                        WHERE master_customer.bit_id = 1 AND  master_corporation.id = :corporate_id
                                    ORDER BY ts_akhir.created_at desc
                                ) tbl_hasil
                            ) mst_tx
                        )
                    select * FROM CTE WHERE RN = 1
            """;

    public static final String COUNTALL = "select COUNT(*) from (:all_query) report_instansi;";
}
