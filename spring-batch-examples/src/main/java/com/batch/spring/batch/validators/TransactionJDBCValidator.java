package com.batch.spring.batch.validators;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import com.batch.spring.batch.exceptions.MinPriceValidationException;
import com.batch.spring.model.Transaction;

public class TransactionJDBCValidator implements Validator<Transaction> {
	private static final Logger logger = LoggerFactory.getLogger(TransactionJDBCValidator.class);

	public static BigDecimal MIN_PRICE_EACH = new BigDecimal("30.0");

	@Override
	public void validate(Transaction value) throws ValidationException {
		logger.info("validation of entity from database");
		if (value != null) {
			if (value.getPriceEach().compareTo(MIN_PRICE_EACH) < 0) {
				logger.error("Minimum Price each validation failed : ValidationException Thrown"
						+ value.getOrderNumber() + " Price Each value -->  " + value.getPriceEach().doubleValue());
				throw new MinPriceValidationException(
						"Minimum Price each validation failed : ValidationException Thrown" + value.getOrderNumber()
								+ " Price Each value -->  " + value.getPriceEach().doubleValue());
			}
		}

	}

}
