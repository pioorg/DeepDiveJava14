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
import java.nio.file.*;

import jdk.jfr.consumer.*;


// To find out the precise file path, you can run e.g.
// jinfo -sysprops `jcmd -l | grep GarbageGenerator | awk '{print $1}'` | grep jdk.jfr.repository | awk -F '=' '{print $2}'
public class ExternalJFRMonitor {
	public static void main(String[] args) {
//		String path = args[0];
		String path = "/tmp/2020_03_12_17_59_04_11446";
		JFRMonitor.monitorEvents(() -> openFileEventRepository(path));
	}

	private static EventStream openFileEventRepository(String path) {
		try {
			return EventStream.openRepository(Path.of(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
