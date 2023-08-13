package com.das.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;

import com.das.common.functions.Common_Functions;
import com.das.datastructure.CircularQueue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverManager {

	protected ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected ThreadLocal<UiAutomator2Options> options = new ThreadLocal<UiAutomator2Options>();
	protected ThreadLocal<String> deviceName = new ThreadLocal<String>();

	public AppiumDriver initDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/property.txt");
		prop.load(fis);
		String platformName = prop.getProperty("platform");
		String dockerName = prop.getProperty("dockerName");
		String ipAddress = prop.getProperty("ipAddress");

		if (StringUtils.trim(dockerName).equalsIgnoreCase("true")) {
			URL u = new URL("http://localhost:4444/wd/hub");
			switch (platformName) {

			}

		} else {

			switch (platformName) {

			case "android":

				options.set(new UiAutomator2Options());
				deviceName.set(CircularQueue.deQueue());
				System.out.println("deviceName " + deviceName.get());
				options.get().setDeviceName(deviceName.get());
				options.get().setCapability("avd", deviceName.get());
				options.get().setCapability("threadName", deviceName.get() + "_" + Common_Functions.dateTime());
				options.get().setCapability("platformName", "Android");
				options.get().setCapability("avdLaunchTimeout", "180000");
				String appUrl = System.getProperty("user.dir") + "//src//main//resources//General-Store.apk";
				options.get().setApp(appUrl);

				driver.set(new AndroidDriver(new URL(ipAddress), options.get()));

				break;
			case "ios":

				break;
			default:
				throw new IllegalStateException("Unexpected value of Browser type");

			}

			String strFile = "logs" + File.separator + platformName + "_"
					+ driver.get().getCapabilities().getCapability("threadName").toString();
			File logFile = new File(strFile);
			if (!logFile.exists()) {
				logFile.mkdirs();
			}
			// route logs to separate file for each thread
			ThreadContext.put("ROUTINGKEY", strFile);

		}

		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver.get();

	}
}
