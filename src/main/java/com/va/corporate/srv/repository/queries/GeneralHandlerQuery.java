package com.va.corporate.srv.repository.queries;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeneralHandlerQuery {

    public GeneralHandlerQuery() {
    }

    public List<Object> buildSearchParams(Map<String, String> searching, StringBuilder sql, List<String> intColumn, List<String> validColumns) {
        System.out.println("buildSearchParams....");
        List<Object> params = new ArrayList<>();
//        System.out.println("searching : "+searching.toString());
//        System.out.println("sql : "+sql);
//        System.out.println("intColumn : "+intColumn.toString());
//        System.out.println("validColumns : "+validColumns.toString());

        searching.forEach((key, value) -> {
            System.out.println("key : "+key);
            System.out.println("value : "+value);

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

    public Map<String, Object> buildSearchNamedParams(
            Map<String, String> searching,
            StringBuilder sql,
            List<String> intColumn,
            List<String> validColumns) {

//        System.out.println("buildSearchParams....");

        // Map to hold named parameters
        Map<String, Object> params = new HashMap<>();

        searching.forEach((key, value) -> {
//            System.out.println("key : " + key);
//            System.out.println("value : " + value);

            if (validColumns.contains(key) && value != null && !value.trim().isEmpty()) {

                if (intColumn.contains(key)) {
                    // Integer column
                    sql.append(" AND ").append(key).append(" = :").append(key);
                    params.put(key, Integer.valueOf(value));

                } else if (key.equals("start_date") || key.equals("end_date")) {
                    // Date range column
                    if (value.isEmpty()) {
                        throw new IllegalArgumentException("Value for " + key + " cannot be null or empty.");
                    }

                    if (key.equals("start_date")) {
                        System.out.println("contains start_date");
                        sql.append(" AND created_at >= :start_date");
                        params.put("start_date", Timestamp.valueOf(value + " 00:00:00"));
                    }

                    if (key.equals("end_date")) {
                        System.out.println("contains end_date");
                        sql.append(" AND created_at <= :end_date");
                        params.put("end_date", Timestamp.valueOf(value + " 23:59:59"));
                    }

                } else {
                    // String column
                    sql.append(" AND ").append(key).append(" ILIKE :").append(key);
                    params.put(key, "%" + value + "%");
                }
            }
        });

        return params;
    }


    // place holder query
    public String replacePlaceholders(String template, Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
//            System.out.println("entry key : "+entry.getKey());
//            System.out.println("entry value : "+entry.getValue());
            template = template.replace("{"+entry.getKey()+"}", entry.getValue());
        }

        return template;
    }




}
