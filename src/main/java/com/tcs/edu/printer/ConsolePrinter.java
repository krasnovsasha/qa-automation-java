package com.tcs.edu.printer;

import static com.tcs.edu.decorator.TimestampMessageDecorator.decorate;

public class ConsolePrinter {

    public static void print(String message) {
        System.out.println(decorate(message));
    }
}
