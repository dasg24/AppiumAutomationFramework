package com.das.driversession;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class BaseTest {
	protected ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<AppiumDriverLocalService>();
	Semaphore semaphore = new Semaphore(10);

	public AppiumDriverLocalService getService() {
		return this.service.get();
	}

	public void setService(AppiumDriverLocalService service) {
		this.service.set(service);
	}

	public void setDriver(AppiumDriver driver) {
		this.driver.set(driver);
	}

	protected AppiumDriver getDriver() {
		return this.driver.get();
	}

	@BeforeMethod
	public void startDriver() throws Exception {
		semaphore.acquire();
		startAppiumServer("127.0.0.1", 4723);
		setDriver(new DriverManager().initDriver());
	}

	@AfterMethod
	public void tearDownDriver() throws Exception {
		getDriver().quit();
		driver.remove();
		semaphore.release();
		getService().close();
	}

	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		setService(new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C://Users//goura//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress(ipAddress).usingPort(port).build());
		getService().start();
		return getService();
	}

	public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		// convert json file content to json string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}
}
