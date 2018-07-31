package com.batch.springdemobatch.batch.extensions;

import java.sql.ResultSet;
import java.sql.SQLException;
/*import java.text.ParseException;
import java.text.SimpleDateFormat;*/

import org.springframework.jdbc.core.RowMapper;

import com.batch.springdemobatch.model.Transaction;

public class TransactionRowMapper implements RowMapper<Transaction> {

	//private SimpleDateFormat sdf = new SimpleDateFormat("M/DD/YYYY HH:MI:SS AM");

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {

		Transaction transaction = new Transaction();
		transaction.setOrderNumber(rs.getLong(1));
		transaction.setQuantityOrdered(rs.getInt(2));
		transaction.setPriceEach(rs.getBigDecimal(3));
		transaction.setOrderLineNumber(rs.getInt(4));
		transaction.setSales(rs.getBigDecimal(5));
//		try {
		transaction.setOrderDate(rs.getTimestamp(6));
		/*
		 * } catch (ParseException pe) { //logger.error("Error while parsing order date"
		 * , pe); pe.printStackTrace(); }
		 */
		transaction.setStatus(rs.getString(7));
		transaction.setQtrId(rs.getInt(8));
		transaction.setMonthId(rs.getInt(9));
		transaction.setYearId(rs.getInt(10));
		transaction.setProductLine(rs.getString(11));
		transaction.setMsrp(rs.getInt(12));
		transaction.setProductCode(rs.getString(13));
		transaction.setCustomerName(rs.getString(14));
		transaction.setPhone(rs.getString(15));
		transaction.setAddressLine1(rs.getString(16));
		transaction.setAddressLine2(rs.getString(17));
		transaction.setCity(rs.getString(18));
		transaction.setState(rs.getString(19));
		transaction.setPostalCode(rs.getString(20));
		transaction.setCountry(rs.getString(21));
		transaction.setTerritory(rs.getString(22));
		transaction.setContactLastName(rs.getString(23));
		transaction.setContactFirstName(rs.getString(24));
		transaction.setDealSize(rs.getString(25));
		return transaction;

	}

}
