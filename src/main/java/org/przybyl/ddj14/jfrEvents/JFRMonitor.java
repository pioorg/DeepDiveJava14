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

import java.io.*;
import java.text.*;
import java.util.function.*;

import jdk.jfr.*;
import jdk.jfr.consumer.*;

public class JFRMonitor {

	public static void monitorEvents(Supplier<EventStream> eventStreamSupplier) {
		Thread monitoringThread = new Thread(() -> {
			try (EventStream stream = eventStreamSupplier.get()) {
				registerEventHandlers(stream);
				stream.start();
			}
		});
		monitoringThread.setName("JFRMonitoring");
		monitoringThread.start();
	}

	public static Configuration getConfiguration(String name) {
		try {
			return Configuration.getConfiguration(name);
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private static void registerEventHandlers(EventStream stream) {
		stream.onEvent("jdk.GarbageCollection", System.out::println);
		stream.onEvent("jdk.CPULoad", System.out::println);
		stream.onEvent("jdk.PhysicalMemory", System.out::println);
		stream.onEvent("jdk.GCHeapSummary", System.out::println);
		stream.onEvent("jdk.G1HeapSummary", System.out::println);
		stream.onEvent("jdk.MetaspaceSummary", System.out::println);
		stream.onEvent("jdk.JVMInformation", System.out::println);
	}
}
