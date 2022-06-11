package com.yeol.ocle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class OcleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcleApplication.class, args);
	}

}
