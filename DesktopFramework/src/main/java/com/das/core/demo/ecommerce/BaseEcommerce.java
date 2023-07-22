package com.das.core.demo.ecommerce;

import org.openqa.selenium.WebDriver;

import com.das.core.BasePage;

public class BaseEcommerce extends BasePage {

	public BaseEcommerce(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

//	public Configuration loadConfig() throws IOException {
//		Configuration configuration = JacksonUtils.deserializeJson("Configuration.json", Configuration.class);
//		return configuration;
//	}
//
//	public CustomerInfo loadBillingInfo() throws IOException {
//		CustomerInfo billingInformation = JacksonUtils.deserializeJson("BillingAddress.json", CustomerInfo.class);
//		return billingInformation;
//	}
//
//	public void loadloginPage() throws IOException {
//		getDriver().get(loadConfig().getAccountURL());
//	}
//
//	public void loadNonLoginPage() throws IOException {
//		getDriver().get(loadConfig().getBaseURL());
//	}

}
