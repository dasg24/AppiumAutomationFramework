package com.das.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.das.core.BasePage;
import com.das.utils.ExtentReport;

public class AccountPage extends BasePage {

	public AccountPage(WebDriver driver) {
		super(driver);
		ExtentReport.getTest().log(Status.INFO, "<h2 style=\"color:red;\">Account Page</h2>");
	}

	protected By username = By.xpath("//input[@id='username']");
	protected By password = By.xpath("//input[@id='password']");
	protected By loginButton = By.xpath("//button[@type='submit']");
	protected By orderLink = By.linkText("Orders");

	public WebElement getLoginButton() {
		return driver.findElement(loginButton);
	}

	public WebElement getUsername() {
		return driver.findElement(username);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement getOrderLink() {
		return driver.findElement(orderLink);
	}

}
