package com.das.driversession;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverManager {

	protected ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	// protected ThreadLocal<RemoteWebDriver> remoteDdriver = new ThreadLocal<>();

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
				UiAutomator2Options options = new UiAutomator2Options();
				options.setDeviceName("Pixel5Appium");
				options.setCapability("avd", "Pixel5Appium");
				options.setCapability("avdLaunchTimeout", "180000");
				String appUrl = System.getProperty("user.dir") + "//src//main//resources//General-Store.apk";
				options.setApp(appUrl);

				driver.set(new AndroidDriver(new URL(ipAddress), options));
				driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

				break;
			case "ios":

				break;
			default:
				throw new IllegalStateException("Unexpected value of Browser type");

			}

		}

		driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver.get();

	}
}
