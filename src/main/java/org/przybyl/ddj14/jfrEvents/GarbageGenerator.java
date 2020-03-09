/*
 *  Copyright (C) 2020 Piotr Przybył
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.przybyl.ddj14.jfrEvents;


import java.time.*;
import java.util.*;

import jdk.jfr.*;
import jdk.jfr.consumer.*;


// You may want to run this with
// -XX:StartFlightRecording=name=garbageCheck -XX:FlightRecorderOptions=repository=/tmp/jfrEvents
// Alternatively, you can use jcmd, e.g.
// jcmd `jcmd -l | grep GarbageGenerator | awk '{print $1}'` JFR.start name=garbageCheck
// To change the repository path, you can use:
// jcmd `jcmd -l | grep GarbageGenerator | awk '{print $1}'` JFR.configure repositorypath=/tmp/jfrEvents
// Remember, this is demo only. In real usage never store important stuff in /tmp!

public class GarbageGenerator {

	private static final Random random = new Random();
	private static List<Object> linkedList;

	public static void main(String[] args) {
		waitUntilReady();
//		doSomeInternalMonitoring();
		useSomeMemAndCpu();
	}

	private static void waitUntilReady() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Press enter when ready to throw some garbage in memory...");
			scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Okay, we can't wait, going straight ahead!");
		}
	}

	private static void useSomeMemAndCpu() {
		System.out.println("We're going to generate some trash now for demo purposes. In real life, please try to eliminate the garbage you generate...");
		while (Math.round(1.2) < 3) {
			int times = random.nextInt(100_000_000) + 100_000_000;
			linkedList = new LinkedList<>();
			for (int i = 0; i < times; i++) {
				linkedList.add(new Object());
			}
			sleepSomeTime();

			System.out.println(Instant.now());
		}
	}

	private static void sleepSomeTime() {
		try {
			Thread.sleep(3000 + random.nextInt(3000));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private static void doSomeInternalMonitoring() {
		try {
			System.out.println("Available JFR configurations:");
			Configuration.getConfigurations().forEach(c -> System.out.println("%s: %s".formatted(c.getName(), c.getDescription())));

			Configuration configuration = Configuration.getConfiguration("default");
			System.out.println("Configuration [%s] settings:".formatted(configuration.getName()));
			configuration.getSettings().forEach((k, v) -> System.out.println("%s=%s".formatted(k, v)));

			JFRMonitor.monitorEvents(() -> new RecordingStream(configuration));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
