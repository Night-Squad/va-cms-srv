package com.server.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class VAAuthenticationToken extends AbstractAuthenticationToken  {

    private final Object principal;
    private Object credentials;
    private int userType;

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public int getUserType() { return userType; }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public VAAuthenticationToken(Object principal, Object credentials, int userType) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.userType = userType;
        setAuthenticated(true);
    }

    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
    }
}
