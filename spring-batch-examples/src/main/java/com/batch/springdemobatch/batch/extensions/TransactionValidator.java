package com.batch.springdemobatch.batch.extensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.stereotype.Component;

import com.batch.springdemobatch.model.Transaction;

@Component
public class TransactionValidator implements Validator<Transaction>{

	
	private static final Logger logger = LoggerFactory.getLogger(TransactionValidator.class);
	
	@Override
	public void validate(Transaction transaction) throws ValidationException {
		logger.info("Current Object in Validation \n" + transaction);
		if(transaction.getContactFirstName()  == null) {
			logger.error("Validation Failed : contact First name can not be null");
			 throw new ValidationException("Validation Failed : contact First name can not be null ");
		}
		
	}

}