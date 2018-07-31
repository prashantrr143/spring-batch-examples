package com.batch.spring.model;

import java.io.Serializable;

/**
 * Model 
 * @author prasingh26
 *
 */
public class SkipTransactionErrorLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String INSERT_SKIP_TRANSACTION_LOG_SQL ="insert into skip_transaction_error_log(error_log) values (?)";

	private Long id; 

	private String errorLog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}

}
