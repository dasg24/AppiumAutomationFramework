package com.das.core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.das.utils.ExtentReport;
import com.das.utils.TestUtils;

public class BasePage extends TestUtils {
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForVisibility(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void selectByValue(By xpath, String value) {
		Select select = new Select(driver.findElement(xpath));
		select.selectByValue(value);
	}

	public void selectByText(By xpath, String value) {
		Select select = new Select(driver.findElement(xpath));
		select.selectByVisibleText(value);
	}

	public void selectByIndex(By xpath, int value) {
		Select select = new Select(driver.findElement(xpath));
		select.selectByIndex(value);
	}

	public String replaceTextInXpath(String xpath, String value) {
		String replacedText = xpath.replaceAll("REPLACETEXT", value);
		return replacedText;
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
		getAttribute(e, "text");
		log().info(msg + txt);
		ExtentReport.getTest().log(Status.INFO, msg + txt);
		return txt;
	}

}
