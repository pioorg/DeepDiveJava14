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

package org.przybyl.ddj14.records.s0_hello;

public record Complex(double real, double imaginary) {
	public Complex add(Complex that) {
		return new Complex(this.real + that.real, this.imaginary + that.imaginary);
	}

	public Complex subtract(Complex that) {
		return new Complex(this.real - that.real, this.imaginary - that.imaginary);
	}

	public Complex multiply(Complex that) {
		return new Complex(this.real * that.real - this.imaginary * that.imaginary, this.real * that.imaginary + that.real * this.imaginary);
	}

	@Override
	public String toString() {
		return "(%g, %gi)".formatted(real, imaginary);
	}


}
