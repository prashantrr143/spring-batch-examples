package com.batch.spring.batch.extensions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.batch.spring.model.Transaction;

@Component
@Profile({"single","steps"})
public class TransactionItemSkipListerner implements SkipListener<Transaction, Transaction> {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionItemSkipListerner.class);

	@Override
	public void onSkipInRead(Throwable t) {
		logger.info( "Skipping Element in Read : "+  t.getMessage());
		
		
	}

	@Override
	public void onSkipInWrite(Transaction item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInProcess(Transaction item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
