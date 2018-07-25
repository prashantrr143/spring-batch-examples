package com.batch.springdemobatch.batch.extensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import com.batch.springdemobatch.model.Transaction;

@Component
public class TransactionItemReadListener implements ItemReadListener<Transaction>{

	private static final Logger logger = LoggerFactory.getLogger(TransactionItemSkipListerner.class);
	@Override
	public void beforeRead() {
			logger.info("Started reading a new Item");
	}


	@Override
	public void onReadError(Exception ex) {
		
		if(ex instanceof FlatFileParseException) {
			FlatFileParseException fe = (FlatFileParseException)ex;
			logger.error("Read Error reported at  "+ fe.getLineNumber() + "  Reason :" + fe.getMessage());
		}
		
	}

	@Override
	public void afterRead(Transaction item) {
		// TODO Auto-generated method stub
		
	}

}
