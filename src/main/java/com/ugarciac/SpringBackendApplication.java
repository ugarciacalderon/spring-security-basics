package com.ugarciac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories("com.ugarciac.repository")
@EntityScan("com.ugarciac.model")
@EnableWebSecurity
public class SpringBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}

}
