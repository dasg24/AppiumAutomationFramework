package com.das.driversession;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;

public class TestListener extends BaseTest implements ITestListener {
	ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	ThreadLocal<String> dateTime = new ThreadLocal<String>();

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
		}

		ITestContext context = result.getTestContext();
		driver.set((AppiumDriver) context.getAttribute("AppiumDriver"));
		dateTime.set((String) context.getAttribute("DateTime"));

		System.out.println(getDriver());
		File file = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);

		String imagePath = "Screenshots" + File.separator
				+ driver.get().getCapabilities().getCapability("platformName").toString() + "_"
				+ driver.get().getCapabilities().getCapability("avd").toString() + File.separator + dateTime.get()
				+ File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator
				+ result.getName() + ".png";

		String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;

		try {
			FileUtils.copyFile(file, new File(imagePath));
			Reporter.log("<a href='" + completeImagePath + "'> <img src='" + completeImagePath
					+ "' height='400' width='400'/> </a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
	}
}
