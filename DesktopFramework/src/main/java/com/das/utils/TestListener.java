package com.das.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.das.core.BaseTest;
import com.das.core.MapDrivers;
import com.das.pojo.Configuration;

public class TestListener extends BaseTest implements ITestListener {
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	ThreadLocal<String> dateTime = new ThreadLocal<String>();

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
		}

		ITestContext context = result.getTestContext();
		MapDrivers mapDrivers = new MapDrivers();
		driver.set(mapDrivers.getCurrentDriver(Thread.currentThread().getId()));

		dateTime.set((String) context.getAttribute("DateTime"));
		File file = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);

		String imagePath = "Screenshots" + File.separator + (Thread.currentThread().getId()) + File.separator
				+ result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".png";

		String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;
		System.out.println("completeImagePath " + completeImagePath);
		try {
			FileUtils.copyFile(file, new File(imagePath));
			Reporter.log("<a href='" + completeImagePath + "'> <img src='" + completeImagePath
					+ "' height='400' width='400'/> </a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] encoded = null;
		try {
			encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ExtentReport.getTest().fail("Test Failed",
				MediaEntityBuilder.createScreenCaptureFromPath(completeImagePath).build());
		ExtentReport.getTest().fail("Test Failed", MediaEntityBuilder
				.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
		ExtentReport.getTest().fail(result.getThrowable());
	}

	@Override
	public void onTestStart(ITestResult result) {
		ITestContext context = result.getTestContext();
		dateTime.set((String) context.getAttribute("DateTime"));
		ExtentReport.startTest(result.getName(), result.getMethod().getDescription())
				.assignCategory("Desktop Test" + "_" + context.getAttribute("DateTime"));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReport.getTest().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReport.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReport.getReporter().flush();
	}

	@Override
	public Configuration loadConfig() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
