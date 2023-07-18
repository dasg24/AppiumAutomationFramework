package com.das.driversession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.das.datastructure.CircularQueue;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;

public class AVD_DeviceStartStop {
	public static ArrayList<String> devicesNameList = new ArrayList<String>();
	public static ArrayList<String> devicesIDList = new ArrayList<String>();
	public static CircularQueue circularQueue;

	public static void getDevicesNames() throws IOException {
		Process process = Runtime.getRuntime().exec("cmd /c emulator -list-avds");
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		while (true) {
			line = in.readLine();
			if (line == null) {
				break;
			}
			devicesNameList.add(line);

		}
		circularQueue = new CircularQueue(devicesNameList.size());
		for (int i = 0; i < devicesNameList.size(); i++) {
			CircularQueue.enQueue(devicesNameList.get(i));
		}
		System.out.println(devicesNameList);
	}

	public void getDevicesIDs() throws IOException {
		Process process = Runtime.getRuntime().exec("cmd /c adb devices");
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		while (true) {
			line = in.readLine();

			if (line == null) {
				break;
			}
			if (line.equalsIgnoreCase("List of devices attached") || line.trim().equalsIgnoreCase("")) {
				continue;
			}
			devicesIDList.add(line.replaceAll("	device", "").replaceAll("	offline", ""));
		}
		System.out.println(devicesIDList);
	}

	public void startEmulatorDevices(String deviceName) throws IOException, InterruptedException {
		if (CircularQueue.isEmpty()) {
			System.out.println("No available device in the emulator");
		} else {
			startEmulator(deviceName);
		}
	}

	public void stopEmulatorDevices() throws IOException, InterruptedException {
		getDevicesIDs();
		System.out.println(devicesIDList.size());
		if (devicesIDList == null) {
			System.out.println("No available device in the emulator");
		} else {
			for (int i = 0; i < devicesIDList.size(); i++) {

				stopEmulator(devicesIDList.get(i));
			}
		}
	}

	public static void startEmulator(String deviceName) throws IOException, InterruptedException {
		System.out.println(deviceName);
		execute("cd %userProfile%//AppData//Local//Android//sdk//emulator && emulator -avd " + deviceName);
	}

	public void stopEmulator(String deviceID) throws IOException, InterruptedException {
		execute("cd %userProfile%//AppData//Local//Android//sdk//emulator && adb -s " + deviceID + " emu kill");
	}

	public static void execute(String command) throws IOException, InterruptedException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

		TimeLimiter timeLimiter = SimpleTimeLimiter.create(executor);
		while (true) {
			String line = "";
			try {
				line = timeLimiter.callWithTimeout(r::readLine, 60, TimeUnit.SECONDS);
			} catch (TimeoutException | InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				executor.shutdown();
				break;
			}
			System.out.println(line);
			if (line == null) {
				executor.shutdown();
				break;
			}
			if (line.contains("Boot completed")) {
				executor.shutdown();
				break;
			}
		}
	}
}
