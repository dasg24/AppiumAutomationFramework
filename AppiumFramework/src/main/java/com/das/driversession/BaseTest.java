package com.das.driversession;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.das.datastructure.CircularQueue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class BaseTest {
	protected ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<AppiumDriverLocalService>();
	Semaphore semaphore;// = new Semaphore(3);
	AVD_DeviceStartStop aVD_DeviceStartStop = new AVD_DeviceStartStop();

	public BaseTest() {
		try {
			AVD_DeviceStartStop.getDevicesNames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(CircularQueue.size);
		semaphore = new Semaphore(CircularQueue.size);
	}

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

	@BeforeClass
	public void startServer() throws Exception {
		startAppiumServer("127.0.0.1", 4723);
	}

	@BeforeMethod
	public void startDriver() throws Exception {

		semaphore.acquire();
		setDriver(new DriverManager().initDriver());
		aVD_DeviceStartStop.startEmulatorDevices(getDriver().getCapabilities().getCapability("avd").toString());

	}

	@AfterMethod
	public void tearDownDriver() throws Exception {
		System.out.println("CURRENT THREAD End: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
		CircularQueue.enQueue(getDriver().getCapabilities().getCapability("avd").toString());
		getDriver().quit();
		semaphore.release();

	}

	@AfterClass
	public void stopDeviceAndServer() throws Exception {
		getService().close();
		driver.remove();
		aVD_DeviceStartStop.stopEmulatorDevices();
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
