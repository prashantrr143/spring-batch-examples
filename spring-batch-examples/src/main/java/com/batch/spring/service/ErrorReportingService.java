package com.batch.spring.service;

import com.batch.spring.model.Transaction;

public interface ErrorReportingService {

	public void logSkipErrorLog(Exception e);

	public void logSkipErrorLog(Transaction transaction, Exception e);

}
