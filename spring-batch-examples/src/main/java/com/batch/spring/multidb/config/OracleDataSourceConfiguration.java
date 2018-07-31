package com.batch.spring.multidb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.oracle.datasource")
@Component
public class OracleDataSourceConfiguration {

	private String url;
	private String password;
	private String userName;
	private String driverClassName;

	public String getUrl() {
		return url;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	@Override
	public String toString() {
		return "OracleDataSourceConfiguration [url=" + url + ", password=" + password + ", userName=" + userName
				+ ", driverClassName=" + driverClassName + "]";
	}

}
