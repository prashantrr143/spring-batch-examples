package com.batch.springdemobatch.service;

import com.batch.springdemobatch.model.Transaction;

public interface ErrorReportingService {

	public void logSkipErrorLog(Exception e);

	public void logSkipErrorLog(Transaction transaction, Exception e);

}
