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

package org.przybyl.ddj14.records.s3_serialization;

import java.io.*;
import java.util.*;

public class Serialization {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Creating record");
		checkSerialization(new SerializationRecordCheck("ABC123"));
		checkSerialization(new SerializationBeanCheck("ABC123"));

	}

	private static void checkSerialization(Object check) throws IOException, ClassNotFoundException {
		byte[] bytez = serialize(check);
		examineBytes(bytez);
		deserialize(bytez);
	}

	private static byte[] serialize(Object check) throws IOException {
		byte[] bytez;
		try (var baos = new ByteArrayOutputStream(); var out = new ObjectOutputStream(baos)) {
			System.out.println("Serializing object %s".formatted(check));
			out.writeObject(check);
			bytez = baos.toByteArray();
		}
		return bytez;
	}

	private static void deserialize(byte[] bytez) throws IOException, ClassNotFoundException {
		try (var bais = new ByteArrayInputStream(bytez); var in = new ObjectInputStream(bais)) {
			System.out.println("Deserializing object");
			var loaded = in.readObject();
			System.out.println("Deserialized %s".formatted(loaded));
		}
	}

	private static void examineBytes(byte[] bytez) {
		System.out.println("Stored bytes: %s".formatted(Arrays.toString(bytez)));
		for (byte b : bytez) {
			System.out.print((char)b);
		}
		System.out.println();
	}
}


