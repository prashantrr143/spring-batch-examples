package com.batch.springdemobatch.multisteps;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchMultiStepsBoostrapper {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchMultiStepsBoostrapper.class, args);
	}
}
