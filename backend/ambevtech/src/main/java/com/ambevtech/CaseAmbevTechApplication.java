package com.ambevtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories
public class CaseAmbevTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseAmbevTechApplication.class, args);
	}

}
