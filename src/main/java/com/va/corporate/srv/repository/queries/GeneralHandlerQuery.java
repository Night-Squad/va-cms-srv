package com.va.corporate.srv.repository.queries;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneralHandlerQuery {

    public GeneralHandlerQuery() {
    }

    public List<Object> buildSearchParams(Map<String, String> searching, StringBuilder sql, List<String> intColumn, List<String> validColumns) {
        List<Object> params = new ArrayList<>();

        searching.forEach((key, value) -> {

            if (validColumns.contains(key) && value != null && !value.trim().isEmpty()) {

                if (intColumn.contains(key)) {
                    // int column value
                    sql.append(" AND ").append(key).append(" = ?");
                    Integer intValue = Integer.valueOf(value);
                    params.add(intValue);

                } else if(key.equals("start_date") || key.equals("end_date")) {
                    // date range column
                    // check if one of starting_date or end_of_date null or empty string, give validation
                    if(value.isEmpty()) {
                        System.out.println("key : "+key+" has value null or empty string - "+value);
                    }

                    if(key.equals("start_date")) {
                        System.out.println("contains start_date");
                        sql.append(" AND ").append("created_at").append(" >= ?");
                        params.add(Timestamp.valueOf(value+" 00:00:00"));
                    }

                    if(key.equals("end_date")) {
                        System.out.println("contains end_date");
                        sql.append(" AND ").append("created_at").append(" <= ?");
                        params.add(Timestamp.valueOf(value+" 23:59:59"));
                    }


                } else {
                    // string value
                    sql.append(" AND ").append(key).append(" ILIKE ?");
                    params.add("%" + value + "%");
                }

            }
        });

        return params;
    }

}
