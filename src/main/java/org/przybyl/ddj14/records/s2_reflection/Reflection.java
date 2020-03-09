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

package org.przybyl.ddj14.records.s2_reflection;

import java.beans.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

public class Reflection {
	public static void main(String[] args) throws Exception {
		var enumObject = JustAnEnum.A;
		var recordObject = new ReflectionCheck(555, "call me maybe");

		System.out.println("Is enum an enum? " + enumObject.getClass().isEnum());
		System.out.println("Is record a record? " + recordObject.getClass().isRecord());

		RecordComponent[] recordComponents = recordObject.getClass().getRecordComponents();
		Stream.of(recordComponents).forEach(rc -> investigateRecordComponent(rc, recordObject));

		describeBeanProperties(recordObject);
		describeBeanProperties(new BeanWithSetters(UUID.randomUUID(), "just a String"));

	}

	private static void investigateRecordComponent(RecordComponent rc, Record r) {
		var value = getValueByReflection(rc, r);
		System.out.println("The component %s is of type %s and belongs to record %s and has value [%s].".formatted(rc.getName(), rc.getType().getCanonicalName(), rc.getDeclaringRecord().getCanonicalName(), value));
	}

	private static Object getValueByReflection(RecordComponent rc, Record r) {
		try {
			return rc.getAccessor().invoke(r);
		} catch (Exception e) {
			//we want it blow if bad things happen
			throw new RuntimeException(e);
		}
	}

	private static void describeBeanProperties(Object object) throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());

		System.out.println("Properties from BeanInfo for [%s] - START".formatted(object.getClass().getCanonicalName()));
		Stream.of(beanInfo.getPropertyDescriptors()).forEach(System.out::println);
		System.out.println("Properties from BeanInfo for [%s] - STOP".formatted(object.getClass().getCanonicalName()));
	}
}

enum JustAnEnum {
	A, B, C;
}

record ReflectionCheck(int a, String b) {
}

class BeanWithSetters {

	private final UUID id;
	private String stringField;

	public BeanWithSetters(UUID id, String stringField) {
		this.id = id;
		this.stringField = stringField;
	}

	public UUID getId() {
		return id;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", BeanWithSetters.class.getSimpleName() + "[", "]")
			.add("id=" + id)
			.add("stringField='" + stringField + "'")
			.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BeanWithSetters that = (BeanWithSetters) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}



