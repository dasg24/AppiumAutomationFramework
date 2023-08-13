package com.das.common.functions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common_Functions {

	public static Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;

	}

	public static String dateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
