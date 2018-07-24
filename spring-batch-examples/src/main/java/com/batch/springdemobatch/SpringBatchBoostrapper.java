package com.batch.springdemobatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchBoostrapper {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchBoostrapper.class, args);
	}
}
