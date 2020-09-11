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

package org.przybyl.ddj14.textBlocks;


public class TextBlocks {

    public static void main(String[] args) {
        System.out.println(getPoemFromText());
        System.out.println(getPoemFromTextBlock());
        System.out.println(getPoemDetails());
        System.out.println(getPoemDetailsFromBlock());
//        System.out.println(trimmed());
//        System.out.println(notTrimmed());
//        System.out.println(getLoremIpsum().replaceAll("\n", ""));
//        System.out.println(getLoremIpsumOneLine());
//        println(getCows());
//        println(getCowTown());
//        indentationTests();
    }

    public static String getPoemFromText() {
        //piece of "Ode to Youth" by Adam Mickiewicz
        return "Dzieckiem w kolebce kto łeb urwał Hydrze,\n" +
                "Ten młody zdusi Centaury,\n" +
                "Piekłu ofiarę wydrze,\n" +
                "Do nieba pójdzie po laury.";
    }

    public static String getPoemFromTextBlock() {
        //piece of "Ode to Youth" by Adam Mickiewicz
        return """
                Tam sięgaj, gdzie wzrok nie sięga;
                Łam, czego rozum nie złamie:
                Młodości! orla twych lotów potęga,
                Jako piorun twoje ramię.
                """;
    }

    public static String getPoemDetails() {
        return "{\n" +
                "\t\"author\": \"Adam Mickiewicz\",\n" +
                "\t\"title\": \"Ode to Youth\",\n" +
                "\t\"written\": \"1820-12-26\"\n" +
                "}";
    }

    public static String getPoemDetailsFromBlock() {
        return """
                {
                	"author": "Adam Mickiewicz",
                	"title": "Ode to Youth",
                	"written": "1820-12-26"
                }
                """;
    }

	public static String trimmed() {
		return """
            Du
            hast
            mich
            gefragt
            """;
	}

	public static String notTrimmed() {
		return """
            Du     \s
            hast   \s
            mich   \s
            gefragt\s
            """;
	}

    public static String getLoremIpsum() {
        return """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et 
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip 
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore 
                eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia 
                deserunt mollit anim id est laborum.
                """;
    }

    public static String getLoremIpsumOneLine() {
        return """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et \
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip \
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore \
                eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia \
                deserunt mollit anim id est laborum.\
                """;
    }

    public static String getCows() {
        return """
                          (__)                     )__(                vv    vv     
                          (oo)                     (oo)                ||----||  *  
                   /-------\\/               *-------\\/                 ||     | / 
                  / |     ||               / |     ||                 /\\-------/   
                 *  ||----||              /  ||----||                (oo)           
                    ^^    ^^                 vv    vv                (~~)           
                
                 American Cow              Polish Cow            Australian Cow     
                
                http://instinct.org/cows/ascii-cows1.html
                """;
    }

    public static String getCowTown() {
        return """
                                    \\  |  /         ___________
                     ____________  \\ \\_# /         |  ___      |       _________
                    |            |  \\  #/          | |   |     |      | = = = = |
                    | |   |   |  |   \\\\#           | |`v'|     |      |         |
                    |            |    \\#  //       |  --- ___  |      | |  || | |
                    | |   |   |  |     #_//        |     |   | |      |         |
                    |            |  \\\\ #_/_______  |     |   | |      | |  || | |
                    | |   |   |  |   \\\\# /_____/ \\ |      ---  |      |         |
                    |            |    \\# |+ ++|  | |  |^^^^^^| |      | |  || | |
                    |            |    \\# |+ ++|  | |  |^^^^^^| |      | |  || | |
                 ^^^|    (^^^^^) |^^^^^#^| H  |_ |^|  | |||| | |^^^^^^|         |
                    |    ( ||| ) |     # ^^^^^^    |  | |||| | |      | ||||||| |
                    ^^^^^^^^^^^^^________/  /_____ |  | |||| | |      | ||||||| |
                         `v'-                      ^^^^^^^^^^^^^      | ||||||| |
                          || |`.      (__)    (__)                          ( )
                                      (oo)    (oo)                       /---V
                               /-------\\/      \\/ --------\\             * |  |
                              / |     ||        ||_______| \\
                             *  ||W---||        ||      ||  *
                                ^^    ^^        ^^      ^^
                
                                         "Cow Town"
                                         http://instinct.org/cows/ascii-cows1.html""";
    }

    private static void println(String toPrint) {
        System.out.println(toPrint);
    }

    static void indentationTests() {
//		println("""This doesn't compile, no new line after opening.""");
//		println("""
//		This doesn't compile, no matching closing.");

        println("""
			This has three ", you see? \"""
			""");

        println("""
		This is not indented.
		""");

        println("""
			This is not indented too.
			""");

        println("""
					This is not indented either.
					""");

        println("""
			This is indented.
		""");

        println("""
		This is indented.
	""");

        println("""
						This is veeery indented.
				""");

        println("""
			You don't have to close text block in the next line.""");

        println("""
			Or you can have many empty lines...

			in between...


			or at the end.


		    """);

        println("""
        spaces
			tabs
        more spaces
	 	 	 	""");

        println("""
			ene """ + """
			\t\tdue """);

        println("""
				What's your name? [%s]
				What's your number? [%d]
				""".formatted(
            ProcessHandle.current().info().user().orElse("FORBIDDEN"), ProcessHandle.current().pid()));
    }
}
