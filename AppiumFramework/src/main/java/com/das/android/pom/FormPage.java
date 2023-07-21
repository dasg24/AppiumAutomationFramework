package com.das.android.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.das.common.functions.AndroidActions;
import com.das.utils.ExtentReport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {

	AndroidDriver driver;

	public FormPage(AppiumDriver driver) {
		super((AndroidDriver) driver);
		this.driver = (AndroidDriver) driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		ExtentReport.getTest().log(Status.INFO, "<h2 style=\"color:red;\">Form Page</h2>");
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;

	@AndroidFindBy(id = "android:id/text1")
	private WebElement countrySelection;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;

	public void setNameField(String name) {
		sendKeys(nameField, name, "Entering Name");
		driver.hideKeyboard();

	}

	public void setActivity() {
		Activity activity = new Activity("com.androidsample.generalstore",
				"com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}

	public void setGender(String gender) {
		if (gender.equalsIgnoreCase("female"))
			click(femaleOption, "Clicking on Gender Female");
		else
			click(maleOption, "Clicking on Gender Male");

	}

	public void setCountrySelection(String countryName) {
		click(countrySelection, "Clicking on Country Selection");
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();

	}

	public void submitForm() {
		click(shopButton, "Clicking on Shop Button");
	}

}
