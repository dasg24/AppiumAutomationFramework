package com.das.android.pom;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.das.common.functions.AndroidActions;
import com.das.utils.ExtentReport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidActions {

	AndroidDriver driver;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cart;

	public ProductCatalogue(AppiumDriver driver) {
		super((AndroidDriver) driver);
		this.driver = (AndroidDriver) driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		ExtentReport.getTest().log(Status.INFO, "<h2 style=\"color:red;\">ProductCatalog Page</h2>");

	}

	public void addItemToCartByIndex(int index) {
		clickByIndex(addToCart, index, "Clicking on AddToCart");
	}

	public void goToCartPage() throws InterruptedException {
		click(cart, "Click on cart button");
		Thread.sleep(2000);

	}

}
