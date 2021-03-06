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

import java.lang.instrument.*;

import jdk.jfr.consumer.*;


// To use this agent please
// ./mvnw package
// then run the GarbageGenerator with
// -javaagent:target/ddj14-1.0-SNAPSHOT.jar
public class JFRMonitoringAgent {

	public static void premain(String args, Instrumentation instrumentation) {
		JFRMonitor.monitorEvents(() -> new RecordingStream(JFRMonitor.getConfiguration("default")));
	}

}
