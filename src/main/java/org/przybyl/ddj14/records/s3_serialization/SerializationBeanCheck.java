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

class SerializationBeanCheck implements Serializable {
	@com.fasterxml.jackson.annotation.JsonProperty
	private final String justOneField;

	public SerializationBeanCheck(String justOneField) {
		this.justOneField = justOneField;
		System.out.println("We're creating bean for [%s]".formatted(justOneField));
	}

//	@com.fasterxml.jackson.annotation.JsonProperty
	public String justOneField() {
		return justOneField;
	}

//	public String getJustOneField() {
//		return justOneField;
//	}
}
