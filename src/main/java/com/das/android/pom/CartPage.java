package com.das.android.pom;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.das.common.functions.AndroidActions;
import com.das.common.functions.Common_Functions;
import com.das.utils.ExtentReport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {
	AndroidDriver driver;

	public CartPage(AppiumDriver driver) {
		super((AndroidDriver) driver);
		this.driver = (AndroidDriver) driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		ExtentReport.getTest().log(Status.INFO, "<h2 style=\"color:red;\">Cart Page</h2>");
	}

	// driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productList;

	// driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	public WebElement terms;

	@AndroidFindBy(id = "android:id/button1")
	public WebElement acceptButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	public WebElement proceed;

	@AndroidFindBy(className = "android.widget.CheckBox")
	public WebElement checkBox;

	public List<WebElement> getProductList() {

		return productList;
	}

	public double getProductsSum() {
		int count = productList.size();
		double totalSum = 0;
		for (int i = 0; i < count; i++) {
			String amountString = productList.get(i).getText();
			Double price = Common_Functions.getFormattedAmount(amountString);
			totalSum = totalSum + price; // 160.97 + 120 =280.97

		}
		return totalSum;
	}

	public Double getTotalAmountDisplayed() {
		return Common_Functions.getFormattedAmount(totalAmount.getText());
	}

	public void acceptTermsConditions() {
		System.out.println("terms " + terms);
		longPressAction(terms, "terms");
		click(acceptButton, "Click on Accept Button");
	}

	public void submitOrder() {
		click(checkBox, "Click on checkbox");
		click(proceed, "Click on proceed");
	}

}
