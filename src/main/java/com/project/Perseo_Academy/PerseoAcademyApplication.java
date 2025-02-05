package com.project.Perseo_Academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.Perseo_Academy.repositories")
public class PerseoAcademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerseoAcademyApplication.class, args);
	}

}
