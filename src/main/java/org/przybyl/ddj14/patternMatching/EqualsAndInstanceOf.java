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


import java.util.*;


// Remember, equals should be symmetric.
// This code is intentionally implemented badly.
// Violating LSP, implementing equals using "pattern matching instance of" badly does not make the code immune.
// And please stop saying "inheritance is bad". It is not bad itself, it is used badly. Difference.
// https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/lang/Object.html#equals(java.lang.Object)
// https://en.wikipedia.org/wiki/Liskov_substitution_principle
public class EqualsAndInstanceOf {

	public static void main(String[] args) {
		var point = new Point (1,1);
		var point3D = new Point3D(1,1,1);
		System.out.println(point.equals(point3D));
		System.out.println(point3D.equals(point));
	}
}


class Point {
	public final int x;
	public final int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// with equals, always use @Override
	// thanks to pattern matching, we can simplify equals accepting subclasses
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o instanceof Point that) {
			return this.x == that.x && this.y == that.y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Point.class.getSimpleName() + "[", "]")
			.add("x=" + x)
			.add("y=" + y)
			.toString();
	}
}


// Remember, violating Liskov substitution principle is not cool.
// Sometimes it is needed though, like for dynamic proxy classes.
// This code is intentionally implemented badly.
class Point3D extends Point {
	public final int z;

	Point3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o instanceof Point3D that) {
			if (!super.equals(o))
				return false;
			return z == that.z;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), z);
	}
}