package com.jsfw.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jsfw.*"})
@EnableJpaRepositories("com.jsfw.repositories")
@EntityScan("com.jsfw.models")
public class Ct22FrJava01DuyPt11MockApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ct22FrJava01DuyPt11MockApplication.class, args);
	}

}
