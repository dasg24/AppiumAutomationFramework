package com.das.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.das.functions.Common_Functions;

public class DriverManager {

	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	// protected ThreadLocal<RemoteWebDriver> remoteDdriver = new ThreadLocal<>();

	public WebDriver initDriver(String dockerName, String browserName) throws IOException {

		if (StringUtils.trim(dockerName).equalsIgnoreCase("true")) {
			URL u = new URL("http://localhost:4444/wd/hub");
			switch (browserName) {

			case "chrome":
				DesiredCapabilities capChrome = DesiredCapabilities.chrome();
				capChrome.setCapability("DateTime", Common_Functions.dateTime());
				driver.set(new RemoteWebDriver(u, capChrome));
				break;
			case "firefox":
				DesiredCapabilities capFirefox = DesiredCapabilities.firefox();
				capFirefox.setCapability("DateTime", Common_Functions.dateTime());
				driver.set(new RemoteWebDriver(u, capFirefox));
				break;
			default:
				throw new IllegalStateException("Unexpected value of Docker Browser type");
			}

		} else {

			switch (browserName) {

			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Drivers/ChromeDriver/chromedriver.exe");
				driver.set(new ChromeDriver());
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/Drivers/FirefoxDriver/geckodriver.exe");
				driver.set(new FirefoxDriver());
				break;
			default:
				throw new IllegalStateException("Unexpected value of Browser type");

			}

		}
		Thread.currentThread().getName();
		String strFile = "logs" + File.separator + Common_Functions.dateTime() + "_" + Thread.currentThread().getId();

		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		// route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);

		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver.get();

	}
}
