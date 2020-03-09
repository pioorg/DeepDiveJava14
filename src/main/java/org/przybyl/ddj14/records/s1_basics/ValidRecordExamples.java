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

package org.przybyl.ddj14.records.s1_basics;

import java.util.*;
import java.util.concurrent.atomic.*;

/**
 * This is demonstration of valid records in Java 14.
 * Please keep in mind that "valid" means "will compile and run" not "this is the way you should do it".
 */
public class ValidRecordExamples {
	public static void main(String[] args) {
		var noField = new NoField();
		System.out.println(noField);
		var oneField = new OneField(42);
		System.out.println(oneField);
		var customMethods = new CustomMethods(17, 56);
		customMethods.printHello("stranger");
		System.out.println(StaticMembers.bar());

		var deepMutableField = new DeepMutableField(new AtomicInteger());
		System.out.println(deepMutableField.bump());
		System.out.println(deepMutableField.bump());
		System.out.println(deepMutableField.bump());
		System.out.println(deepMutableField.counter());

		System.out.println(new CustomToString("Joe"));

		var answer = new CustomEqualsAndHashCode("answer", 42);
		System.out.println("Are answers the same? " + answer.equals(new CustomEqualsAndHashCode(answer.string(), answer.a())));

		var customAccessor = new CustomAccessor(777);
		System.out.println(customAccessor.secret());

		System.out.println(new CustomCanonicalConstructor(1, 2, 3, 4));
		System.out.println(new CustomCompactConstructor(1, 2, 3, 4));

		var one = new CanImplementInterfaces(5, 5);
		var another = new CanImplementInterfaces(5, 7);

		System.out.println("Let's compare %s with %s, what do we get? [%s]".formatted(one, another, one.compareTo(another)));
	}
}

record NoField() {
}

record OneField(int a) {
}

record CustomMethods(int a, int b) {
	public void printHello(String to) {
		System.out.println("Hello " + to);
	}
}

record StaticMembers(int a) {
	private static String FOO = "bar";

	public static String bar() {
		return "foo";
	}
}

record DeepMutableField(AtomicInteger counter) {
	//remember, just because you can, doesn't mean you should...
	public int bump() {
		return counter.incrementAndGet();
	}
}

record CustomToString(String string) {
	@Override
	public String toString() {
		return "Howdy! It's a nice day, ain't it? Oh, the string? Here it is: " + string;
	}
}

record CustomEqualsAndHashCode(String string, int a) {
	//Please keep in mind that equals and hashCode need to obey their contracts!
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CustomEqualsAndHashCode that = (CustomEqualsAndHashCode) o;
		return a == that.a &&
			Objects.equals(string, that.string);
	}

	@Override
	public int hashCode() {
		return Objects.hash(string, a);
	}
}

record CustomAccessor(int secret) {
	public int secret() {
		System.out.println("Here's my public secret. Please don't tell anybody...");
		return secret;
	}
}

record CustomCanonicalConstructor(int a, int b, int c, int d) {
	public CustomCanonicalConstructor(int a, int b, int c, int d) {
		assert (a != b);
		assert (c != d);
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		System.out.println("Canonical constructor called");
	}
}

record CustomCompactConstructor(int a, int b, int c, int d) {
	public CustomCompactConstructor {
		assert (a != b);
		assert (c != d);
		System.out.println("Compact constructor called");
	}
}

//try renaming to "hashCode" ;-)
record CustomNonCanonicalConstructor(int length, int hash) {
	public CustomNonCanonicalConstructor(String string) {
		this(string.length(), string.hashCode());
	}
}

record CanImplementInterfaces(int a, int b) implements Comparable<CanImplementInterfaces> {
	@Override
	public int compareTo(CanImplementInterfaces that) {
		return Objects.compare(this, that,
			Comparator.comparing(CanImplementInterfaces::a).thenComparing(CanImplementInterfaces::b));
	}
}