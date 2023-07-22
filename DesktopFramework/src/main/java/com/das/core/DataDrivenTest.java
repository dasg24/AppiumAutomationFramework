package com.das.core;

import java.util.ArrayList;

import com.das.pojo.CustomerInfo;

public abstract class DataDrivenTest {

	public static ArrayList<String> innerArrayList = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> outerArrayList = new ArrayList<ArrayList<String>>();
	public static ArrayList<CustomerInfo> customerInfoList = new ArrayList<CustomerInfo>();

	private static int startValue;
	private static int endValue;

	public static int getStartValue() {
		return startValue;
	}

	public static void setStartValue(int startValue) {
		DataDrivenTest.startValue = startValue;
	}

	public static int getEndValue() {
		return endValue;
	}

	public static void setEndValue(int endValue) {
		DataDrivenTest.endValue = endValue;
	}

	abstract String[] fetchRangeDataFromSource();

	abstract void setResultValuesBackToSource();

	public void mapExcellDataInCollection(String temp[]) {
		for (int j = 0; j < temp.length; j++) {

			System.out.println(temp[j]);
			innerArrayList.add(temp[j]);
			if ((temp[j].isEmpty()) || j == temp.length - 1) {
				outerArrayList.add(innerArrayList);
				innerArrayList = new ArrayList<String>();
				continue;
			}
		}

	}

	public Object[][] mapCollectionDataInPOJO() throws CloneNotSupportedException {
		Object[][] temp = new Object[outerArrayList.size() - 1][2];
		for (int i = 1; i < outerArrayList.size(); i++) {
			CustomerInfo customerInfo = new CustomerInfo();
			// CustomerInfo customerInfoCopy = new CustomerInfo();
			for (int j = 0; j < outerArrayList.get(i).size(); j++) {
				switch (outerArrayList.get(0).get(j)) {
				case "OrderNumber":
					customerInfo.setOrderNumber(outerArrayList.get(i).get(j));
					break;

				case "OrderDate":
					customerInfo.setOrderDate(outerArrayList.get(i).get(j));
					break;

				case "TotalPrice":
					customerInfo.setTotalPrice(outerArrayList.get(i).get(j));
					break;

				case "PaymentMethod":
					customerInfo.setPaymentMethod(outerArrayList.get(i).get(j));
					break;

				case "SearchBy":
					customerInfo.setSearchBy(outerArrayList.get(i).get(j));
					break;

				case "SearchItem":
					customerInfo.setSearchItem(outerArrayList.get(i).get(j));
					break;

				case "ItemName":
					customerInfo.setItemName(outerArrayList.get(i).get(j));
					break;

				case "FirstName":
					customerInfo.setFirstName(outerArrayList.get(i).get(j));
					break;

				case "LastName":
					customerInfo.setLastName(outerArrayList.get(i).get(j));
					break;

				case "AddressLineOne":
					customerInfo.setAddressLineOne(outerArrayList.get(i).get(j));
					break;

				case "City":
					customerInfo.setCity(outerArrayList.get(i).get(j));
					break;

				case "PostalCode":
					customerInfo.setPostalCode(outerArrayList.get(i).get(j));
					break;

				case "Country":
					customerInfo.setCountry(outerArrayList.get(i).get(j));
					break;

				case "State":
					customerInfo.setState(outerArrayList.get(i).get(j));
					break;

				case "Email":
					customerInfo.setEmail(outerArrayList.get(i).get(j));
					break;

				default:
					System.out.println("CustomerInfo not have fields for the value that you're passing from Excel");

				}

			}
			customerInfoList.add(customerInfo);
			temp[i - 1][0] = customerInfo;
			temp[i - 1][1] = customerInfo.clone();
		}
		return temp;
	}

}
