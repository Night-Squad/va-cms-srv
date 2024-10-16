package com.server.auth.security;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.ToString;

/**
 * Created By Aristo Pacitra
 */

 @Getter 		
 @ToString	
public class JwtConfig {

	@Value("${security.jwt.uri:/login/**}")
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;

	@Value("${user-va.url}")
	private String userVaUrl;

	public String getUserVaUrl() {
		return userVaUrl;
	}

	public void setUserVaUrl(String userVaUrl) {
		this.userVaUrl = userVaUrl;
	}

	public String getUri() {
		return Uri;
	}

	public String getHeader() {
		return header;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiration() {
		return expiration;
	}

	public String getSecret() {
		return secret;
	}
    
}