package com.bjbs.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.io.iona.springboot.storage.StorageLocationProperties;

@SpringBootApplication(scanBasePackages={"com.bjbs.auth, com.io.iona.springboot.storage"})
@EnableConfigurationProperties(StorageLocationProperties.class)
@EnableEurekaClient
public class BjbsAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BjbsAuthApplication.class, args);
	}

}
