package com.va.corporate.srv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages={"com.va.corporate.srv"})
@EnableConfigurationProperties
public class VACmsSrvApplication {

	// CMS = Corporate Management System

	public static void main(String[] args) {

		System.out.println("testing pipeline....");
		SpringApplication.run(VACmsSrvApplication.class, args);
	}

}
