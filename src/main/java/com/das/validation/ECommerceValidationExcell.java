package com.das.validation;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.das.android.pom.CartPage;
import com.das.android.pom.FormPage;
import com.das.android.pom.ProductCatalogue;
import com.das.base.BaseTest;
import com.das.base.DataDrivenExcel;
import com.das.pojo.CustomerInfo;

public class ECommerceValidationExcell extends BaseTest {

	@Test(dataProvider = "getData")
	public void FillForm(CustomerInfo customerInfo, CustomerInfo customerInfoCopy) throws InterruptedException {
		FormPage formPage = new FormPage(getDriver());
		formPage.setActivity();
		formPage.setNameField(customerInfo.getFullName());
		formPage.setGender(customerInfo.getGender());
		formPage.setCountrySelection(customerInfo.getCountry());
		formPage.submitForm();
		ProductCatalogue productCatalogue = new ProductCatalogue(getDriver());
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(getDriver());

		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		System.out.println(
				getDriver().getCapabilities().getCapability("avd").toString() + " " + customerInfo.getFullName());
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();

	}

	@DataProvider(parallel = true)
	public Object[][] getData() throws CloneNotSupportedException {

		DataDrivenExcel dataDrivenExcel = new DataDrivenExcel();
		String temp[] = dataDrivenExcel.fetchRangeDataFromSource();
		dataDrivenExcel.mapExcellDataInCollection(temp);
		return dataDrivenExcel.mapCollectionDataInPOJO();

	}

}