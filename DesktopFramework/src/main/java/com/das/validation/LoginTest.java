package com.das.validation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.das.core.BaseTest;
import com.das.core.DataDrivenExcel;
import com.das.pojo.CustomerInfo;
import com.das.pom.AccountPage;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void validateCartpage(CustomerInfo customerInfo, CustomerInfo customerInfoCopy) throws Exception {

		loadloginPage();
		AccountPage ap = new AccountPage(getDriver());
		ap.sendKeys(ap.getUsername(), customerInfo.getEmail(), "Entering email id");
		ap.sendKeys(ap.getPassword(), loadConfig().getPassword(), "Entering password");
		ap.click(ap.getLoginButton(), "Clicking on login button");

		ap.click(ap.getOrderLink(), "Clicking on order link");
		System.out.println("CURRENT THREAD End: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());

	}

	@DataProvider(parallel = true)
	public Object[][] getData() throws CloneNotSupportedException {

		DataDrivenExcel dataDrivenExcel = new DataDrivenExcel();
		String temp[] = dataDrivenExcel.fetchRangeDataFromSource();
		dataDrivenExcel.mapExcellDataInCollection(temp);
		return dataDrivenExcel.mapCollectionDataInPOJO();

	}

}
