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

package org.przybyl.ddj14.switchExpressions;

import java.util.*;
import java.util.stream.*;

enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static Optional<DayOfWeek> fromString(String day) {
        return Stream.of(DayOfWeek.values()).filter(dow -> dow.name().equalsIgnoreCase(day)).findFirst();
    }
}

public class LessonsChecker {
    public static void main(String[] args) {
        System.out.println("Hello Java 14!");

        if (args == null || args.length == 0) {
            System.out.println("I have no lessons!");
        } else {
            Stream.of(args)
                    .map(DayOfWeek::fromString)
                    .forEach(od -> od.ifPresent(d ->
                            System.out.printf("On %s I have %d lessons.%n", d,
                            howManyLessonsArrow(d)
                            //howManyLessonsExpressionWithYield(d)
                            )));
        }

    }

    private static int howManyLessonsArrow(DayOfWeek day) {
        return switch (day) {
            case MONDAY, FRIDAY -> 6;
            case TUESDAY -> 7;
            case WEDNESDAY, THURSDAY -> 8;
            case SATURDAY, SUNDAY -> 0;
        };
    }

    private static int howManyLessonsArrowBlocks(DayOfWeek day) {
        return switch (day) {
            case MONDAY, FRIDAY -> 6;
            case TUESDAY -> 7;
            case WEDNESDAY, THURSDAY -> 8;
            case SATURDAY, SUNDAY -> {
                System.out.println("How dare you asking about the weekend?");
                yield 0;
            }
        };
    }

    private static int howManyLessonsInstruction(DayOfWeek day) {
        int lessons = 0;
        switch (day) {
            case MONDAY, FRIDAY:
                lessons = 6;
                break;
            case TUESDAY:
                lessons = 7;
                break;
			case WEDNESDAY:
				// the line below shows fallthrough..
				System.out.println("Pourquoi mercredi ?");
            case THURSDAY:
                lessons = 8;
                break;
            case SATURDAY, SUNDAY:
                lessons = 0;
                break;
        }
        return lessons;
    }

    private static int howManyLessonsExpressionWithYield(DayOfWeek day) {
        var lessons = switch (day) {
            case MONDAY, FRIDAY:
                yield 6;
            case TUESDAY:
                yield 7;
            case WEDNESDAY:
                // the line below shows fallthrough..
                System.out.println("Pourquoi mercredi ?");
            case THURSDAY:
                yield 8;
            case SATURDAY, SUNDAY:
                yield 0;
        };
        return lessons;
    }

//	static int switchStatementsNeedToBeExhaustive(int input) {
//		//generates compiler error
//		return switch (input) {
//			case 0, 1, 2 -> input + 1;
//			case 3, 4, 5 -> 2 * input;
////			default -> -1;
//		};
//	}

//	private static int returnFromSwitchExpressionDoesNotWork(int input) {
//		int output = switch (input) {
//			case 0, 1, 2 -> input + 1;
//			case 3, 4, 5 -> {
//				return 2 * input;
//			}
//			default -> -1;
//		};
//		return output;
//	}

    private static int returnFromSwitchStatementWorks(int input) {
        switch (input) {
            case 0, 1, 2 -> {
                return input + 1;
            }
            case 3, 4, 5 -> {
                return 2 * input;
            }
            default -> {
                return -1;
            }
        }
    }

//	private static int switchStatementsNeedToBeExhaustive(int input) {
//		//generates compiler error
//		return switch (input) {
//			case 0, 1, 2 -> input + 1;
//			case 3, 4, 5 -> 2 * input;
//		};
//	}

    private static void switchDoesNotNeedAssignedType() {
        // don't do randoms like this in production, demo only
        var input = new Random().nextInt(10);
        var stuff = switch (input) {
            case 0, 1, 2, 3, 4 -> "I'm very smol";
            default -> input;
        };
        System.out.println(String.format("The class of [%s] is [%s].",
                stuff, stuff.getClass().getCanonicalName()));
    }
}