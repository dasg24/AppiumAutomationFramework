package com.das.core;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

public class MapDrivers {
	public static HashMap<Long, WebDriver> map = new HashMap<>();

	public void addElement(WebDriver driver) {
		map.put(Thread.currentThread().getId(), driver);

	}

	public WebDriver getCurrentDriver(long threadID) {
		return map.get(threadID);
	}
}
