package com.server.auth.domain;

public record UserDomain(
        String name,
        String email,
        String password
) {
}
