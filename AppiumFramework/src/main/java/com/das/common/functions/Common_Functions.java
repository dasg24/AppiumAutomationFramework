package com.das.common.functions;

public class Common_Functions {

	public static Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;

	}

}
