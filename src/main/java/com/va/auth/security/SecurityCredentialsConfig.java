package com.va.auth.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created By Aristo Pacitra
 */

@EnableWebSecurity
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtConfig jwtConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and()
				.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(new VAAuthenticationManager(), jwtConfig))
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
				.antMatchers(HttpMethod.GET, jwtConfig.getUri()).permitAll()
				.antMatchers(HttpMethod.POST,"/api/users/create").permitAll()
				.antMatchers(HttpMethod.POST,"/api/users_management/create_user_management").permitAll()
				.antMatchers(HttpMethod.POST,"/api/users_management/multiple_create_user_management").permitAll()
				.antMatchers(HttpMethod.PUT,"/api/users_management/update_user_management").permitAll()
				.antMatchers(HttpMethod.GET, "/api/users_management/get_user_management").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/users_management/change_password").permitAll()
				.antMatchers(HttpMethod.GET, "/sendemail").permitAll()
				.antMatchers(HttpMethod.GET, "/api/users/readAll").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/users/update").permitAll()
				.antMatchers(HttpMethod.GET, "/ol/users/optionList").permitAll()
				.antMatchers(HttpMethod.GET, "/ol/role/optionList").permitAll()
				.antMatchers(HttpMethod.GET, "/api/users_management_data/readAll").permitAll()

				.anyRequest().authenticated();
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