package com.batch.springdemobatch.multisteps;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableBatchProcessing
@Profile({"steps","csv-xml"})
public class SpringBatchMultiStepsBoostrapper {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchMultiStepsBoostrapper.class, args);
	}
}
