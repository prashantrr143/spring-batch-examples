package com.batch.spring.multisteps.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xml.metadata.transaction")
@Profile({"steps","csv-xml"})
public class TransactionXMLMetadataConfiguration {

	/**
	 * Defines the root tag name for the transactions entity
	 */
	private String rootTagName;

	/**
	 * Defined tag name for individual transaction entity
	 */
	private String tagName;

	public String getRootTagName() {
		return rootTagName;
	}

	public void setRootTagName(String rootTagName) {
		this.rootTagName = rootTagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
