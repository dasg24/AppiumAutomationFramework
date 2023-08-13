package com.das.base;

import java.util.HashMap;

import io.appium.java_client.AppiumDriver;

public class MapDrivers {
	public static HashMap<Long, AppiumDriver> map = new HashMap<>();

	public void addElement(AppiumDriver driver) {
		map.put(Thread.currentThread().getId(), driver);

	}

	public AppiumDriver getCurrentDriver(long threadID) {
		return map.get(threadID);
	}
}
