package com.das.pom;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.das.common.functions.AndroidActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidActions {

	AndroidDriver driver;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	// driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO
	// CART']"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cart;

	public ProductCatalogue(AppiumDriver driver) {
		super(driver);
		this.driver = (AndroidDriver) driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); //

	}

	public void addItemToCartByIndex(int index) {
		addToCart.get(index).click();

	}

	public void goToCartPage() throws InterruptedException {
		cart.click();
		Thread.sleep(2000);

	}

}
