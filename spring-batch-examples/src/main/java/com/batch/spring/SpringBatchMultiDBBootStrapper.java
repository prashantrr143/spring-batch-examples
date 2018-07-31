package com.batch.spring;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.batch.spring.multidb.config.OracleDataSourceConfiguration;
import com.batch.spring.multidb.config.PostgresqlDataSourceConfiguration;

@SpringBootApplication
@EnableBatchProcessing
@Profile("multidb")
public class SpringBatchMultiDBBootStrapper {

	
	@Autowired
	OracleDataSourceConfiguration oracleDs;
	
	
	@Autowired
	PostgresqlDataSourceConfiguration postgresDs;

	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchMultiDBBootStrapper.class, args);
	
	}
	
	@Bean
	@Profile("multidb")
	CommandLineRunner runner(){
		return args -> {
			System.out.println("CommandLineRunner running in the UnsplashApplication class...");
			System.out.println("oracleDs " + oracleDs);
			System.out.println("postgresDs " + postgresDs);
		};
	}
}
