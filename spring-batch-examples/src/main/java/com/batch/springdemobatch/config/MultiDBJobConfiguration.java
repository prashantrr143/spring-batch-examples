package com.batch.springdemobatch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.batch.springdemobatch.multidb.config.OracleDataSourceConfiguration;
import com.batch.springdemobatch.multidb.config.PostgresqlDataSourceConfiguration;


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
