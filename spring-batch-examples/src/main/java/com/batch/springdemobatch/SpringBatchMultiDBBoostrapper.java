package com.batch.springdemobatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.batch.springdemobatch.multidb.config.OracleDataSourceConfiguration;

@SpringBootApplication
@EnableBatchProcessing
@Profile("multidb")
public class SpringBatchMultiDBBoostrapper {

	
	@Autowired
	OracleDataSourceConfiguration oracleDs;
	
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchMultiDBBoostrapper.class, args);
	
	}
	
	@Bean
	@Profile("multidb")
	CommandLineRunner runner(){
		return args -> {
			System.out.println("CommandLineRunner running in the UnsplashApplication class...");
			System.out.println("oracleDs " + oracleDs);
		};
	}
}
