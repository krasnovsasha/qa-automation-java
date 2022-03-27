package com.tcs.edu.decorator;

import java.util.Date;

/**
 * The {@code TimestampMessageDecorator} class contains method {@code decorate} for change
 * base message output.
 */
public class TimestampMessageDecorator {
    private static int messageCount;
    /**
     * @param message incoming base message
     * @return decoratedMessage with changes
     * <p>
     * @see com.tcs.edu.printer.ConsolePrinter#print(String)
     */
    public static String decorate(String message){
        messageCount++;
        var decoratedMessage = messageCount + " " + new Date() + " " + message;
        return decoratedMessage;
    }
}
