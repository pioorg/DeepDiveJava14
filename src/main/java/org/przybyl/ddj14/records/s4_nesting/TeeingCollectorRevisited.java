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

import java.util.*;
import java.util.stream.*;

public class TeeingCollectorRevisited {

	public static void main(String[] args) {

//now we can use record in situ, instead of static class (see below)
		record CountAndSum(long count, long sum){}

// getting sum and count as operation on primitives
		long sum = gimmeStream().mapToLong(l -> l).sum();
		long count = gimmeStream().count();

// reducing
		CountAndSum reduced = gimmeStream().reduce(
			new CountAndSum(0, 0),
			(cas, e) -> new CountAndSum(cas.count + 1, cas.sum + e),
			(cas1, cas2) -> new CountAndSum(cas1.count + cas2.count, cas1.sum + cas2.sum));

// teeing
		CountAndSum collected = gimmeStream().collect(
			Collectors.teeing(
				Collectors.counting(),
				Collectors.summingLong(l -> l),
				CountAndSum::new));

		System.out.println("Sum: " + sum);
		System.out.println("Count: " + count);
		System.out.println("Sum & count: " + reduced);
		System.out.println("Sum & count: " + collected);

// don't re-invent the wheel when not needed ;-)
		LongSummaryStatistics stats = gimmeStream().collect(Collectors.summarizingLong(e -> e));
		System.out.println(stats);

// using array in such case is not the best option; consider creating a tuplish class
		long[] by2AndBy3 = gimmeStream().collect(Collectors.teeing(
			Collectors.filtering(l -> l % 2 == 0, Collectors.counting()),
			Collectors.filtering(l -> l % 3 == 0, Collectors.counting()),
			(by2, by3) -> new long[]{by2, by3}
		));
		System.out.println(String.format("There are %d numbers divisible by 2 and %d numbers divisible by 3.", by2AndBy3[0], by2AndBy3[1]));


	}

	private static Stream<Long> gimmeStream() {
//		System.out.println("Creating stream...");
		return Stream.iterate(0L, i -> i < 20, i -> i + 1);
	}

//	static class CountAndSum {
//		public final long count;
//		public final long sum;
//
//		public CountAndSum(long count, long sum) {
//			this.count = count;
//			this.sum = sum;
//		}
//
//		@Override
//		public String toString() {
//			return new StringJoiner(", ", CountAndSum.class.getSimpleName() + "[", "]")
//				.add("count=" + count)
//				.add("sum=" + sum)
//				.toString();
//		}
//	}
}


