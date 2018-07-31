package com.batch.springdemobatch.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.batch.springdemobatch.model.SkipTransactionErrorLog;
import com.batch.springdemobatch.model.Transaction;
import com.batch.springdemobatch.service.ErrorReportingService;

/**
 * Service Implementation for logging error logs in to database.
 * 
 * @author prasingh26
 *
 */
@Service
@Profile({ "csv-xml" })
public class ErrorReportingServiceImpl implements ErrorReportingService {

	private static final Logger logger = LoggerFactory.getLogger(ErrorReportingServiceImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public void logSkipErrorLog(Exception ex) {
		logger.info("Started Error Logging ");
		SkipTransactionErrorLog errorLog = new SkipTransactionErrorLog();
		errorLog.setErrorLog(getFormattedReadErrorLog(ex));
		jdbcTemplate.update(SkipTransactionErrorLog.INSERT_SKIP_TRANSACTION_LOG_SQL,
				new Object[] { errorLog.getId(), errorLog.getErrorLog() });
		logger.info(" Error Logging successfull..  ");

	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public void logSkipErrorLog(Transaction transaction, Exception ex) {
		logger.info("Started Error Logging ");
		SkipTransactionErrorLog errorLog = new SkipTransactionErrorLog();
		errorLog.setErrorLog(getFormattedReadErrorLog(ex));
		jdbcTemplate.update(SkipTransactionErrorLog.INSERT_SKIP_TRANSACTION_LOG_SQL,
				new Object[] { errorLog.getId(), errorLog.getErrorLog() });
		logger.info(" Error Logging successfull..  ");

	}

	/**
	 * Utility Method to convert an Exception instance to a readable error message
	 * 
	 * @param ex
	 * @return String formatted error log
	 */
	private String getFormattedReadErrorLog(Exception ex) {
		if (ex instanceof FlatFileParseException) {
			FlatFileParseException fe = (FlatFileParseException) ex;
			return "Read Error reported at  " + fe.getLineNumber() + "  Reason :" + fe.getMessage();
		}
		return null;
	}

}
