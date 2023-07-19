package com.das.validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.das.driversession.AVD_DeviceStartStop;

public class EmulatorStarter {

	public static List<Runnable> tasks = new ArrayList<Runnable>();
	public static AVD_DeviceStartStop aVD_DeviceStartStop = new AVD_DeviceStartStop();
	public static List<Thread> threads;

	public static void start() {

		try {
			System.out.println(Thread.currentThread().getName());
			for (int i = 0; i < aVD_DeviceStartStop.devicesNameList.size(); i++) {
				tasks.add(new AVD_DeviceStartRunnable(aVD_DeviceStartStop.devicesNameList.get(i)));
			}
			EmulatorStarter emulatorStarter = new EmulatorStarter();
			emulatorStarter.executeAll();
			System.out.println(Thread.currentThread().getName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void executeAll() throws InterruptedException {
		threads = new ArrayList<>(tasks.size());

		for (int i = 0; i < tasks.size(); i++) {
			Thread thread = new Thread(tasks.get(i));
			threads.add(thread);
		}

		for (int i = 0; i < threads.size(); i++) {

			threads.get(i).start();
		}

		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).join();
		}

	}

}

class AVD_DeviceStartRunnable implements Runnable {
	String deviceName;

	public AVD_DeviceStartRunnable(String deviceName) {
		this.deviceName = deviceName;
	}

	public void run() {
		AVD_DeviceStartStop avd_DeviceStartStop = new AVD_DeviceStartStop();
		try {
			System.out.println("Starting Android device " + deviceName);
			avd_DeviceStartStop.startEmulatorDevices(deviceName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
