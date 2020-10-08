package com.bjbs.auth.login.umroh;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.bjbs.auth.utility.HashUtil.*;

import com.bjbs.auth.dtos.CustomUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created By Aristo Pacitra
 */

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter   {

	private AuthenticationManager authManager;
	private final JwtConfig jwtConfig;

	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					creds.getUsername(), SHA_256.digestAsHex(creds.getPassword()), Collections.emptyList());

			return authManager.authenticate(authToken);

		} catch (IOException e) {
			throw new UsernameNotFoundException("Username: " + e + " not found");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		CustomUser springUser = (CustomUser)auth.getPrincipal();
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
				.setSubject(auth.getName())	
				.claim("authorities", auth.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
				.compact();
		response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Map<String, Object> result = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		result.put("data", springUser);
		
		AuthenticationClaims authClaims = new AuthenticationClaims();
		authClaims.setAuthorization(token);
		authClaims.setExpiresIn(jwtConfig.getExpiration());
		authClaims.setScope(jwtConfig.getSecret());
		authClaims.setTokenType(jwtConfig.getPrefix());
		result.put("auth", authClaims);

		response.getWriter().write(mapper.writeValueAsString(result));
	}
	private static class UserCredentials {
		private String username, password;

		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
	}

	class AuthenticationClaims {
		public String Authorization;
		private String tokenType;
		private String scope;
		private long expiresIn;

		public void setAuthorization(String authorization) {
			Authorization = authorization;
		}
		public String getTokenType() {
			return tokenType;
		}
		public void setTokenType(String tokenType) {
			this.tokenType = tokenType;
		}
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		public long getExpiresIn() {
			return expiresIn;
		}
		public void setExpiresIn(long expiresIn) {
			this.expiresIn = expiresIn;
		}
	}
}