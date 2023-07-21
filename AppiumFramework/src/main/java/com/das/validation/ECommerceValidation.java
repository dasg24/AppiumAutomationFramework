package com.das.validation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.das.android.pom.CartPage;
import com.das.android.pom.FormPage;
import com.das.android.pom.ProductCatalogue;
import com.das.base.BaseTest;

public class ECommerceValidation extends BaseTest {

	@Test(dataProvider = "getData")
	public void FillForm(HashMap<String, String> input) throws InterruptedException {
		FormPage formPage = new FormPage(getDriver());
		formPage.setActivity();
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		formPage.submitForm();
		ProductCatalogue productCatalogue = new ProductCatalogue(getDriver());
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(getDriver());

		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		System.out.println(getDriver().getCapabilities().getCapability("avd").toString() + " " + input.get("name"));
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();

	}

	@DataProvider(parallel = true)
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//main//resources//eCommerce.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
