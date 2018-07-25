package com.batch.springdemobatch.batch.extensions;



import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.context.annotation.Profile;

import com.batch.springdemobatch.model.Transaction;


@Profile("single")
public class TransactionItemListenerSupport extends ItemListenerSupport<Transaction, Transaction> {

	@Override
	public void afterRead(Transaction item) {
		
	}

	@Override
	public void beforeRead() {
		System.out.println(" Reading Listener invoked for beforeRead()");
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println(" Error at the time of reading " + ex.getMessage());
	}

	@Override
	public void afterProcess(Transaction item, Transaction result) {
	}

	@Override
	public void beforeProcess(Transaction item) {
	}

	@Override
	public void onProcessError(Transaction item, Exception e) {
	}

/*	@Override
	public void afterWrite(List<? extends Transaction> item) {
	}

	@Override
	public void beforeWrite(List<? extends Transaction> item) {
	}

	@Override
	public void onWriteError(Exception ex, List<? extends Transaction> item) {
	}
*/
}
