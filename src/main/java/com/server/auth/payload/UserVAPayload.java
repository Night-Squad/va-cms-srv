package com.server.auth.payload;

import lombok.Data;

@Data
public class UserVAPayload {
    private String username;
    private String password;
}