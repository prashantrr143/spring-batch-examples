package com.batch.springdemobatch.multidb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


/**
 * 
 * @author prasingh26
 *
 */
@Configuration
@Profile("multidb")
public class MultiDBJobConfiguration {
	
	
	@Autowired
	OracleDataSourceConfiguration oracleDSConfiguration;
	
	@Autowired
	PostgresqlDataSourceConfiguration postgresqlDataSourceConfiguration;	
	
	
	
	
	

}
