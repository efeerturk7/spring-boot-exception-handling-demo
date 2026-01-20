package com.example.exception_handling_bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.example.exception_handling_bootcamp"})
@ComponentScan(basePackages ={"com.example.exception_handling_bootcamp"})
@EnableJpaRepositories(basePackages ={"com.example.exception_handling_bootcamp"} )
@SpringBootApplication
public class ExceptionHandlingBootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionHandlingBootcampApplication.class, args);
	}

}
