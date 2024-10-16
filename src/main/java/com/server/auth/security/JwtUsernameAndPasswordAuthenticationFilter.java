package com.server.auth.security;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created By Aristo Pacitra
 */

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter   {

	private VAAuthenticationManager authManager;
	private final JwtConfig jwtConfig;

	public JwtUsernameAndPasswordAuthenticationFilter(VAAuthenticationManager authManager, JwtConfig jwtConfig) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
			VAAuthenticationToken authToken = new VAAuthenticationToken(creds.getUsername(),
					creds.getPassword(), 1
			);

			return authManager.authenticate(authToken, jwtConfig);

		} catch (IOException | JSONException e) {
			throw new UsernameNotFoundException("Username: " + e + " not found");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication auth) throws IOException, ServletException {
		System.out.println("successfulAuthentication====");

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> result = new HashMap<>();
		long now = System.currentTimeMillis();

		String token = Jwts.builder()
				.setSubject(auth.getName())
				.claim("authorities", auth.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000L))  // in milliseconds
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
				.compact();
		response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		AuthenticationClaims authClaims = new AuthenticationClaims();
		authClaims.setAuthorization(token);
		authClaims.setExpiresIn(jwtConfig.getExpiration());
		authClaims.setScope(jwtConfig.getSecret());
		authClaims.setTokenType(jwtConfig.getPrefix());

		JSONObject data = null;
		try {
			data = new JSONObject(auth.getDetails().toString());
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}

		System.out.println("data.get(\"virtualAccountResponse\").toString()).toMap()" + data.toString());

		Map<String, Object> virtualAccountResponse = new HashMap<>();
		virtualAccountResponse.put("auth", authClaims);
		// paymentMaslahah.put("data", new JSONObject(data.get("paymentMaslahah").toString()).toMap());
		result.put("virtual_account", virtualAccountResponse);

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