package com.tcs.edu.printer;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code ConsolePrinter} class implements method {@code print} for change
 * base message output.
 */
public class ConsolePrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
