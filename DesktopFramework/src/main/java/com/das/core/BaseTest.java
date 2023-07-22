package com.das.core;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.das.functions.Common_Functions;
import com.das.pojo.Configuration;
import com.das.pojo.CustomerInfo;
import com.das.utils.JacksonUtils;

@Listeners(com.das.utils.TestListener.class)
public abstract class BaseTest {
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	Semaphore semaphore = new Semaphore(10);
	String dockerName = "";
	String browserName = "";

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getDockerName() throws IOException {
		return dockerName;
	}

	public void setDockerName(String dockerName) {
		this.dockerName = dockerName;
	}

	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	public WebDriver getDriver() {
		return this.driver.get();
	}

	@BeforeSuite
	public void startDockerGrid() throws IOException, InterruptedException {
		setDockerName(loadConfig().getDockerName());
		setBrowserName(loadConfig().getBrowserName());
		if (StringUtils.trim(getDockerName()).equalsIgnoreCase("true")) {
			StartDocker startDocker = new StartDocker();
			startDocker.startDockerGrid();
		}

	}

	@BeforeMethod
	public void startDriver(ITestContext context) throws Exception {
		semaphore.acquire();
		setDriver(new DriverManager().initDriver(getDockerName(), getBrowserName()));
		MapDrivers mapDrivers = new MapDrivers();
		mapDrivers.addElement(getDriver());
		setContext(context);
	}

	public synchronized void setContext(ITestContext context) {
		context.setAttribute("DateTime", Common_Functions.dateTime());
	}

	@AfterMethod
	public void tearDownDriver() throws Exception {
		getDriver().quit();
		semaphore.release();
	}

	@AfterSuite
	public void removeDriver() throws Exception {
		if (StringUtils.trim(getDockerName()).equalsIgnoreCase("true")) {
			StopDocker stopDocker = new StopDocker();
			stopDocker.stopDockerGrid();
		}
		driver.remove();

	}

	public Configuration loadConfig() throws IOException {
		Configuration configuration = JacksonUtils.deserializeJson("Configuration.json", Configuration.class);
		return configuration;
	}

	public CustomerInfo loadBillingInfo() throws IOException {
		CustomerInfo billingInformation = JacksonUtils.deserializeJson("BillingAddress.json", CustomerInfo.class);
		return billingInformation;
	}

	public void loadloginPage() throws IOException {
		getDriver().get(loadConfig().getAccountURL());
	}

	public void loadNonLoginPage() throws IOException {
		getDriver().get(loadConfig().getBaseURL());
	}

}
