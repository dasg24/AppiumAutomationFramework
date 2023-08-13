package com.das.common.functions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.aventstack.extentreports.Status;
import com.das.utils.ExtentReport;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends CommonActions {

	AndroidDriver driver;

	public AndroidActions(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void longPressAction(WebElement ele, String elementName) {
		log().info("Long press on element " + elementName);
		ExtentReport.getTest().log(Status.INFO, "Long press on element " + elementName);
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
	}

//	public void scrollToEndAction() {
//		boolean canScrollMore;
//		do {
//			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
//					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0
//
//					));
//		} while (canScrollMore);
//	}

	public void scrollToText(String text) {
		log().info("Scrolling to text- " + text);
		ExtentReport.getTest().log(Status.INFO, "Scrolling to text- " + text);
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
	}

	public void swipeAction(WebElement ele, String direction) {
		log().info("Swipping action towards - " + direction);
		ExtentReport.getTest().log(Status.INFO, "Swipping action towards - " + direction);
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),

						"direction", direction, "percent", 0.75));

	}

}
