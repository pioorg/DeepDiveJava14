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

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class JSONifocation {
	public static void main(String[] args) throws JsonProcessingException {
		var mapper = new ObjectMapper();

//		out.println(mapper.writeValueAsString(new SerializationBeanCheck("Bean")));
//		out.println(mapper.writeValueAsString(new SerializationRecordCheck("Record")));

		reflectiveExamination(SerializationBeanCheck.class);
		reflectiveExamination(SerializationRecordCheck.class);
	}

	private static void reflectiveExamination(Class<?> klazz) {
		System.out.println("Examining class "+ klazz.getCanonicalName());
		Stream.<AnnotatedElement[]>of(
			klazz.getRecordComponents(),
			klazz.getDeclaredConstructors(),
			klazz.getDeclaredFields(),
			klazz.getDeclaredMethods())
			.filter(Objects::nonNull)
			.flatMap(Stream::of)
			.filter(e -> e.isAnnotationPresent(JsonProperty.class))
			.forEach(System.out::println);
	}

}
