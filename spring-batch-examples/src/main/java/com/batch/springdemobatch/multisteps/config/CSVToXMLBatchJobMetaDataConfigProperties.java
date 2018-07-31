package com.batch.springdemobatch.multisteps.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Batch Job Configuration Metadata Holder.
 * 
 * 
 * @author prashantsingh
 * @since 07/31/2018
 *
 */

@Component
@ConfigurationProperties(prefix = "batch.csv.xml.metadata")
@Profile({ "steps", "csv-xml" })
public class CSVToXMLBatchJobMetaDataConfigProperties {

	private String batchName;
	private String csvReaderStepName;
	private String xmlWriterStepName;
	private String jdbcReaderStepName;
	private String jdbcWriterStepName;
	private int csvReadChunkSize;
	private int jdbcReadChunkSize;
	private int csvReadSkipLimit;
	private int jdbcReadSkipLimit;

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getCsvReaderStepName() {
		return csvReaderStepName;
	}

	public void setCsvReaderStepName(String csvReaderStepName) {
		this.csvReaderStepName = csvReaderStepName;
	}

	public String getXmlWriterStepName() {
		return xmlWriterStepName;
	}

	public void setXmlWriterStepName(String xmlWriterStepName) {
		this.xmlWriterStepName = xmlWriterStepName;
	}

	public String getJdbcReaderStepName() {
		return jdbcReaderStepName;
	}

	public void setJdbcReaderStepName(String jdbcReaderStepName) {
		this.jdbcReaderStepName = jdbcReaderStepName;
	}

	public String getJdbcWriterStepName() {
		return jdbcWriterStepName;
	}

	public void setJdbcWriterStepName(String jdbcWriterStepName) {
		this.jdbcWriterStepName = jdbcWriterStepName;
	}

	public int getCsvReadChunkSize() {
		return csvReadChunkSize;
	}

	public void setCsvReadChunkSize(int csvReadChunkSize) {
		this.csvReadChunkSize = csvReadChunkSize;
	}

	public int getJdbcReadChunkSize() {
		return jdbcReadChunkSize;
	}

	public void setJdbcReadChunkSize(int jdbcReadChunkSize) {
		this.jdbcReadChunkSize = jdbcReadChunkSize;
	}

	public int getCsvReadSkipLimit() {
		return csvReadSkipLimit;
	}

	public void setCsvReadSkipLimit(int csvReadSkipLimit) {
		this.csvReadSkipLimit = csvReadSkipLimit;
	}

	public int getJdbcReadSkipLimit() {
		return jdbcReadSkipLimit;
	}

	public void setJdbcReadSkipLimit(int jdbcReadSkipLimit) {
		this.jdbcReadSkipLimit = jdbcReadSkipLimit;
	}

}
