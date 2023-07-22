package com.das.pom;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.das.core.BasePage;
import com.das.utils.ExtentReport;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		ExtentReport.getTest().log(Status.INFO, "<h2 style=\"color:red;\">Home Page</h2>");

		// TODO Auto-generated constructor stub
	}

}
