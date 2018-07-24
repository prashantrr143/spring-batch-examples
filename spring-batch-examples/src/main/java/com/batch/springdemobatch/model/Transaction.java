package com.batch.springdemobatch.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction implements Serializable {

	public static String[] FIELDS_METADATA = new String[] { "orderNumber", "quantityOrdered", "priceEach",
			"orderLineNumber", "sales", "orderDate", "status", "qtrId", "monthId", "yearId", "productLine", "msrp",
			"productCode", "customerName", "phone", "addressLine1", "addressLine2", "city", "state", "postalCode",
			"country", "territory", "contactLastName", "contactFirstName", "dealSize" };

	public static final String INSERT_TRANSACTION_SQL = " INSERT INTO SALES_TRANSACTION VALUES ("
			+ " :orderNumber , :quantityOrdered,:priceEach,"
			+ " :orderLineNumber,:sales,:orderDate,:status,:qtrId,:monthId,:yearId,:productLine,:msrp ,"
			+ ":productCode,:customerName,:phone,:addressLine1,:addressLine2,:city,:state,:postalCode,"
			+ ":country,:territory,:contactLastName,:contactFirstName,:dealSize)";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * ORDERNUMBER QUANTITYORDERED PRICEEACH ORDERLINENUMBER SALES ORDERDATE STATUS
	 * QTR_ID MONTH_ID YEAR_ID PRODUCTLINE MSRP PRODUCTCODE CUSTOMERNAME PHONE
	 * ADDRESSLINE1 ADDRESSLINE2 CITY STATE POSTALCODE COUNTRY TERRITORY
	 * CONTACTLASTNAME CONTACTFIRSTNAME DEALSIZE
	 */

	private long orderNumber;
	private long quantityOrdered;
	private BigDecimal priceEach;
	private int orderLineNumber;
	private BigDecimal sales;
	private Date orderDate;
	private String status;
	private int qtrId;
	private int monthId;
	private int yearId;
	private String productLine;
	private int msrp;
	private String productCode;
	private String customerName;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String territory;
	private String state;
	private String postalCode;
	private String country;
	private String contactLastName;
	private String contactFirstName;
	private String dealSize;

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(long quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}

	public int getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(int orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	public BigDecimal getSales() {
		return sales;
	}

	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQtrId() {
		return qtrId;
	}

	public void setQtrId(int qtrId) {
		this.qtrId = qtrId;
	}

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public int getMsrp() {
		return msrp;
	}

	public void setMsrp(int msrp) {
		this.msrp = msrp;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getDealSize() {
		return dealSize;
	}

	public void setDealSize(String dealSize) {
		this.dealSize = dealSize;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	@Override
	public String toString() {
		return "Transaction [orderNumber=" + orderNumber + ", quantityOrdered=" + quantityOrdered + ", priceEach="
				+ priceEach + ", orderLineNumber=" + orderLineNumber + ", sales=" + sales + ", orderDate=" + orderDate
				+ ", status=" + status + ", qtrId=" + qtrId + ", monthId=" + monthId + ", yearId=" + yearId
				+ ", productLine=" + productLine + ", msrp=" + msrp + ", productCode=" + productCode + ", customerName="
				+ customerName + ", phone=" + phone + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", city=" + city + ", territory=" + territory + ", state=" + state + ", postalCode="
				+ postalCode + ", country=" + country + ", contactLastName=" + contactLastName + ", contactFirstName="
				+ contactFirstName + ", dealSize=" + dealSize + "]";
	}

}
