package com.batch.springdemobatch.batch.extensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.batch.springdemobatch.model.Transaction;
import com.batch.springdemobatch.service.ErrorReportingService;

@Component
@Profile({ "single", "steps", "csv-xml" })
public class TransactionItemReadListener implements ItemReadListener<Transaction> {

	@Autowired
	private ErrorReportingService errorReportingService;

	private static final Logger logger = LoggerFactory.getLogger(TransactionItemSkipListerner.class);

	@Override
	public void beforeRead() {
		logger.info("Started reading a new Item");
	}

	
	@Override
	public void onReadError(Exception ex) {
		logger.error("Error Found in reading the item");
		errorReportingService.logSkipErrorLog(ex);

	}

	@Override
	public void afterRead(Transaction item) {
		// TODO Auto-generated method stub

	}

}
