package com.va.corporate.srv.domain;

import java.time.LocalDateTime;

public record LoginAttempDomain (
        String email,
        boolean success,
        LocalDateTime createdAt
) {
}
