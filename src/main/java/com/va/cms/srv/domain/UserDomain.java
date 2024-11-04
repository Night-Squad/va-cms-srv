package com.va.cms.srv.domain;

public record UserDomain(
        String name,
        String email,
        String password
) {
}
