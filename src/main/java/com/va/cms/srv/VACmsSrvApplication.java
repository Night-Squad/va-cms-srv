package com.va.cms.srv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages={"com.va.cms.srv"})
@EnableConfigurationProperties
public class VACmsSrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(VACmsSrvApplication.class, args);
	}

}
