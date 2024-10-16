package com.server.auth.security;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created By Aristo Pacitra
 */

@Configuration
@EnableWebSecurity
public class SecurityCredentialsConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtConfig jwtConfig;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(handling -> handling.authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
				.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(new VAAuthenticationManager(), jwtConfig))
				.authorizeHttpRequests(requests -> requests
						.requestMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
						.requestMatchers(HttpMethod.GET, jwtConfig.getUri()).permitAll()
						.requestMatchers(HttpMethod.POST, "/api/users/create").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/users_management/create_user_management").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/users_management/multiple_create_user_management").permitAll()
						.requestMatchers(HttpMethod.PUT, "/api/users_management/update_user_management").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/users_management/get_user_management").permitAll()
						.requestMatchers(HttpMethod.PUT, "/api/users_management/change_password").permitAll()
						.requestMatchers(HttpMethod.GET, "/sendemail").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/users/readAll").permitAll()
						.requestMatchers(HttpMethod.PUT, "/api/users/update").permitAll()
						.requestMatchers(HttpMethod.GET, "/ol/users/optionList").permitAll()
						.requestMatchers(HttpMethod.GET, "/ol/role/optionList").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/users_management_data/readAll").permitAll()

						.anyRequest().authenticated());
		return http.build();
	}

	@Bean
	public JwtConfig jwtConfig() {
		return new JwtConfig();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		System.out.println("====BCryptPasswordEncoder");
		return new BCryptPasswordEncoder();
	}
}