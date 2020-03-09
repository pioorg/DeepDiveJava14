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

package org.przybyl.ddj14.patternMatching;

import java.io.*;
import java.time.*;

public class InstanceOfPatternMatching {

	public static void main(String[] args) {
		whoAreYou(Instant.now());
		whoAreYou("Hi");
		whoAreYou("Hello");
		whoAreYou(-2);
		whoAreYou(12);
		whoAreYou(777L);
	}

	public static void whoAreYou(Object obj) {
		if (obj instanceof Serializable serializable) {
			System.out.println("You're serializable " + serializable + "!");
		}
		if (obj instanceof String string && string.length() >= 3) {
			System.out.println("You're String, at least 3 characters long!");
		} else if (obj instanceof Integer integer && integer > 0) {
			System.out.println("You're a positive Integer");
		} else if (obj instanceof Long aLong && (aLong < -9 || aLong > 9)) {
			System.out.println("You're a Long with at least two digits");
		}
	}
}
