package com.das.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;

public class StartDocker {
	public void startDockerGrid() throws IOException, InterruptedException {

		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start DockerUp.bat");

		String f = "OutputLog.txt";

		Calendar cal = Calendar.getInstance();// 2:44 15th second
		cal.add(Calendar.SECOND, 45);// 2:44 45seconds
		long stopnow = cal.getTimeInMillis();
		Thread.sleep(3000);

		while (System.currentTimeMillis() < stopnow) {
			if (flag) {
				break;
			}

			BufferedReader reader = new BufferedReader(new FileReader(f));
			String currentLine = reader.readLine();
			while (currentLine != null && !flag)

			{

				if (currentLine.contains("Node has been added")) {
					System.out.println("found my text");
					flag = true;// 14th seconds
					break;
				}

				currentLine = reader.readLine();
			}
			reader.close();

		}

		Assert.assertTrue(flag);
		runtime.exec("cmd /c start ScaleUp.bat");
		Thread.sleep(15000);

	}
}
