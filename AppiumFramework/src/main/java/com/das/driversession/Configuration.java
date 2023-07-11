package com.das.driversession;

public class Configuration {
	private String baseURL;
	private String accountURL;
	private String envName;
	private String userName;
	private String password;

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountURL() {
		return accountURL;
	}

	public void setAccountURL(String accountURL) {
		this.accountURL = accountURL;
	}

}
