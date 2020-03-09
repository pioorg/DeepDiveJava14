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

package org.przybyl.ddj14.helpfulNullMessages;


import java.util.*;

// Do not forget -XX:{+|-}ShowCodeDetailsInExceptionMessages
public class NullMessages {

    public static void main(String[] args) {
        showWithFields();
        showWithMap();
        showWithArrays();
        showHandThrownNPE();
    }

    private static void showWithFields() {
        var a = new A(new B(new C(new D(new E(new Fin("finito"))))));
        System.out.println(a);
        a.b.c.d.e = null;
        printNPEMessage(() -> System.out.println(a.b.c.d.e.f.fin.length()));
        printNPEMessage(() -> a.b.c.d.e.f = new Fin("ENDE!"));
    }

    private static void showWithMap() {
        var map = new HashMap<String, String>();
        map.put("foo", "bar");
        printNPEMessage(() -> System.out.println(map.get("bar").length()));
    }

    private static void showWithArrays() {
        Integer[][] intArray = null;
        printNPEMessage(() -> System.out.println(intArray[0][0].hashCode()));
        printNPEMessage(() -> intArray[1] = new Integer[3]);
        Integer[][] anotherArray = {{1, 2}, null, {5, 6}, {7, 8}};
        printNPEMessage(() -> anotherArray[1][1] = 12);
        printNPEMessage(() -> anotherArray[2][1] = anotherArray[1][1]);
    }

    private static void showHandThrownNPE() {
        printNPEMessage(NullMessages::throwNPE);
    }

    private static void printNPEMessage(Runnable r) {
        try {
            r.run();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


    private static void throwNPE() {
        if (Math.round(1.0) < 2) {
            throw new NullPointerException();
        }
    }
}

// The examples below are not the way good OOP code looks like. They are used only for easier demonstration of
// reporting NPEs. Please do not create code like that for other, non-experimental purposes.
class A {
    public B b;

    public A(B b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "A[%s]".formatted(b);
    }
}

class B {
    public C c;

    public B(C c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "B[%s]".formatted(c);
    }
}

class C {
    public D d;

    public C(D d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "C[%s]".formatted(d);
    }
}

class D {
    public E e;

    public D(E e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "D[%s]".formatted(e);
    }
}

class E {
    public Fin f;

    public E(Fin f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "E[%s]".formatted(f);
    }
}

class Fin {
    public String fin = "fin";

    public Fin(String fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "F[%s]".formatted(fin);
    }
}
