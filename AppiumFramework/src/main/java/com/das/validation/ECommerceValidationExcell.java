package com.das.validation;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.das.driversession.BaseTest;
import com.das.driversession.DataDrivenExcel;
import com.das.pojo.CustomerInfo;
import com.das.pom.CartPage;
import com.das.pom.FormPage;
import com.das.pom.ProductCatalogue;

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
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(getDriver());

		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		AssertJUnit.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();

	}

	@DataProvider // (parallel = true)
	public Object[][] getData() throws CloneNotSupportedException {

		DataDrivenExcel dataDrivenExcel = new DataDrivenExcel();
		String temp[] = dataDrivenExcel.fetchRangeDataFromSource();
		dataDrivenExcel.mapExcellDataInCollection(temp);
		return dataDrivenExcel.mapCollectionDataInPOJO();

	}

}