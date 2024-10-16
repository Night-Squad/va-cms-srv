package com.server.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages={"com.server.auth"})
@EnableConfigurationProperties
public class AuthApplication {

	public static void main(String[] args) {
		System.out.println("testing cicd..");
		SpringApplication.run(AuthApplication.class, args);
	}

}
