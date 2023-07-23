package com.pan_secure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.pan_secure.controller", "com.pan_secure.dto",
		"com.pan_secure.exception", "com.pan_secure.helper", "com.pan_secure.service", "com.pan_secure.advice" })
@EntityScan(basePackages = { "com.pan_secure.entity" })
@EnableJpaRepositories(basePackages = { "com.pan_secure.repository" })
public class SecurePanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurePanApplication.class, args);
	}

}
