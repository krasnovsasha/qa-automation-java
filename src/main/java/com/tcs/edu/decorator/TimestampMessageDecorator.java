package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * @author a.a.krasnov
 * The {@code TimestampMessageDecorator} class contains method {@code decorate} for change
 * base message output.
 */
public class TimestampMessageDecorator {
    private static int messageCount;
    private static final int PAGE_SIZE = 2;

    /**
     * @param message incoming base message
     * @return new message with changes
     * <p>
     * side-effect method changes console output
     * <p>
     * @see com.tcs.edu.printer.ConsolePrinter#print(String)
     */
    public static String decorate(String message) {
        String decoratedMessage = String.format("%d %s %s", ++messageCount, Instant.now(), message);
        if (messageCount % PAGE_SIZE == 0) {
            decoratedMessage = String.format("%s%n---", decoratedMessage);
        }
        return decoratedMessage;
    }
}
