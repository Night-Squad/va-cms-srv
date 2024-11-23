package com.va.corporate.srv.dto;

import com.va.corporate.srv.domain.LoginAttempDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record LoginAttemptResponseDto(
        @Schema(description = "The date and time of the login attempt")LocalDateTime createdAt,
        @Schema(description = "The login status") boolean status
        ) {

    public static LoginAttemptResponseDto convertDto(LoginAttempDomain loginAttempt) {
        return new LoginAttemptResponseDto(loginAttempt.createdAt(), loginAttempt.success());
    }
}
