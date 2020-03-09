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

public class Limitations {
	public static void main(String[] args) {
		var cannotExtend = new CannotExtend(12);
		var extendingRecordDoesNotWork = new ExtendingRecordDoesNotWork();
		var noExtraFields = new NoExtraFields(12);
		var noSetters = new NoSetters(12);
		System.out.println("accessing: " + noSetters.field());
//		System.out.println("setting: " + noSetters.field(13));
	}
}

record CannotExtend(int a)
//	extends Date
{}

class ExtendingRecordDoesNotWork
//	extends CannotExtend
{}

record NoExtraFields(int field1) {
//	private final int field2;
}

record NoSetters(int field) {
//	public void field(int newValue) {
//		this.field = newValue;
//	}
}
