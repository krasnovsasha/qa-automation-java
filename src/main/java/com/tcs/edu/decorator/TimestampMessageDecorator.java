package com.tcs.edu.decorator;

import java.time.Instant;
import java.util.Date;

/**
 * @author a.a.krasnov
 * The {@code TimestampMessageDecorator} class contains method {@code decorate} for change
 * base message output.
 */
public class TimestampMessageDecorator {
    private static int messageCount;
    /**
     * @param message incoming base message
     * @return decoratedMessage with changes
     * <p>
     * side-effect method changes console output
     * <p>
     * @see com.tcs.edu.printer.ConsolePrinter#print(String)
     */
    public static String decorate(String message){
        messageCount++;
        return messageCount + " " + Instant.now() + " " + message;
    }
}
