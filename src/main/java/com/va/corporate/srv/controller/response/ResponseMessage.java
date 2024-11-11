package com.va.corporate.srv.controller.response;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessage {

    public Map<String, Object> success(String rc, String status, int statusCode, String message, Object obj) {

        Map<String, Object> resp = new HashMap<>();

        resp.put("rc", rc);
        resp.put("statusCode", statusCode);
        resp.put("status", status);
        resp.put("message", message);
        resp.put("data", obj);
        return resp;

    }
}
