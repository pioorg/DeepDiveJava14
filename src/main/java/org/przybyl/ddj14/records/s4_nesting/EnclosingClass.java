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

package org.przybyl.ddj14.records.s4_nesting;

/**
 * Created by Piotr Przybył (piotr@przybyl.org) on 2020-03-09.
 */
class EnclosingClass {
	private final int field;

	EnclosingClass(int field) {
		this.field = field;
	}
	record NestedRecord(int nested){
		void check() {
			System.out.println("Can I get access to fields in enclosing classes?");
//			System.out.println(EnclosingClass.this.field);
		}
	}

	/*static*/ class InsideClass {
		void check() {
			System.out.println("Can I get access to fields in enclosing classes?");
			System.out.println(EnclosingClass.this.field);
		}
	}
}
