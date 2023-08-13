package com.das.common.functions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.das.utils.ExtentReport;
import com.das.utils.TestUtils;

import io.appium.java_client.AppiumDriver;

public class CommonActions extends TestUtils {
	AppiumDriver driver;

	public CommonActions(AppiumDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void waitForVisibility(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void clear(WebElement e) {
		waitForVisibility(e);
		e.clear();
	}

	public void click(WebElement e) {
		waitForVisibility(e);
		e.click();
	}

	public void click(WebElement e, String msg) {
		waitForVisibility(e);
		log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		e.click();
	}

	public void clickByIndex(List<WebElement> e, int index, String msg) {
		waitForVisibility(e.get(index));
		log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		e.get(index).click();
	}

	public void sendKeys(WebElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
	}

	public void sendKeys(WebElement e, String txt, String msg) {
		waitForVisibility(e);
		log().info(msg + " " + txt);
		ExtentReport.getTest().log(Status.INFO, msg + " " + txt);
		e.sendKeys(txt);
	}

	public String getAttribute(WebElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}

	public String getText(WebElement e, String msg) {
		String txt = null;
		switch (driver.getCapabilities().getCapability("platformName").toString().trim()) {
		case "Android":
			txt = getAttribute(e, "text");
			break;
		case "iOS":
			txt = getAttribute(e, "label");
			break;
		}
		log().info(msg + txt);
		ExtentReport.getTest().log(Status.INFO, msg + txt);
		return txt;
	}
}
