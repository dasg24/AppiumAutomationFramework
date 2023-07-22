package com.das.pojo;

public class CustomerInfo implements Cloneable {
	private String firstName;
	private String lastName;
	private String addressLineOne;
	private String city;
	private String postalCode;
	private String country;
	private String state;
	private String itemName;
	private String SearchItem;
	private String SearchBy;
	private String PaymentMethod;
	private String TotalPrice;
	private String OrderDate;
	private String OrderNumber;
	private String email;

//	public BillingInformation(String firstName, String lastName, String addressLineOne, String city, String postalCode,
//			String email) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.addressLineOne = addressLineOne;
//		this.city = city;
//		this.postalCode = postalCode;
//		this.email = email;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSearchItem() {
		return SearchItem;
	}

	public void setSearchItem(String searchItem) {
		SearchItem = searchItem;
	}

	public String getSearchBy() {
		return SearchBy;
	}

	public void setSearchBy(String searchBy) {
		SearchBy = searchBy;
	}

	public String getPaymentMethod() {
		return PaymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		PaymentMethod = paymentMethod;
	}

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public CustomerInfo clone() throws CloneNotSupportedException {
		return (CustomerInfo) super.clone();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
