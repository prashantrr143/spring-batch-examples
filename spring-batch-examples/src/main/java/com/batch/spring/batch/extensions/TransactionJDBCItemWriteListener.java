package com.batch.spring.batch.extensions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import com.batch.spring.model.Transaction;

public class TransactionJDBCItemWriteListener implements ItemWriteListener<Transaction> {

	private static final Logger logger = LoggerFactory.getLogger(TransactionJDBCItemWriteListener.class);

	@Override
	public void beforeWrite(List<? extends Transaction> items) {
		logger.info("Reading Chunk dumping information : total batch for reading" + (items != null ? items.size() : 0));

	}

	@Override
	public void afterWrite(List<? extends Transaction> items) {
		logger.info("afterWrite invoked for chunk of size :" + (items != null ? items.size() : 0));


	}

	@Override
	public void onWriteError(Exception exception, List<? extends Transaction> items) {
		logger.error("Exception occured while writing the current chunk", exception);

	}

}
