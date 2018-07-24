package com.batch.springdemobatch.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.batch.springdemobatch.model.Transaction;

/**
 * A Custom FieldSetMapper , needed map a CSV row data to the POJO configured
 * 
 * @author prashantsingh
 *
 */
public class TransactionRecordFileldSetMapper implements FieldSetMapper<Transaction> {

	public static String[] FIELDS_METADATA = new String[] { "orderNumber", "quantityOrdered", "priceEach",
			"orderLineNumber", "sales", "orderDate", "status", "qtrId", "monthId", "yearId", "productLine", "msrp",
			"productCode", "customerName", "phone", "addressLine1", "addressLine2", "city", "state", "postalCode",
			"country", "territory", "contactLastName", "contactFirstName", "dealSize" };

	private SimpleDateFormat sdf = new SimpleDateFormat("M/DD/YYYY HH:MI:SS AM");

	@Override
	public Transaction mapFieldSet(FieldSet fieldSet) throws BindException {

		Transaction transaction = new Transaction();
		transaction.setOrderNumber(fieldSet.readLong(0));
		transaction.setQuantityOrdered(fieldSet.readInt(1));
		transaction.setPriceEach(fieldSet.readBigDecimal(2));
		transaction.setOrderLineNumber(fieldSet.readInt(3));
		transaction.setSales(fieldSet.readBigDecimal(4));
		try {
			transaction.setOrderDate(sdf.parse(fieldSet.readString(5)));
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		transaction.setStatus(fieldSet.readString(6));
		transaction.setQtrId(fieldSet.readInt(7));
		transaction.setMonthId(fieldSet.readInt(8));
		transaction.setYearId(fieldSet.readInt(9));
		transaction.setProductLine(fieldSet.readString(10));
		transaction.setMsrp(fieldSet.readInt(11));
		transaction.setProductCode(fieldSet.readString(12));
		transaction.setCustomerName(fieldSet.readString(13));
		transaction.setPhone(fieldSet.readString(14));
		transaction.setAddressLine1(fieldSet.readString(14));
		transaction.setAddressLine2(fieldSet.readString(15));
		transaction.setCity(fieldSet.readString(16));
		transaction.setState(fieldSet.readString(17));
		transaction.setPostalCode(fieldSet.readString(18));
		transaction.setCountry(fieldSet.readString(19));
		transaction.setTerritory(fieldSet.readString(20));
		transaction.setContactLastName(fieldSet.readString(21));
		transaction.setContactFirstName(fieldSet.readString(22));
		transaction.setDealSize(fieldSet.readString(23));
		return transaction;

	}
}
