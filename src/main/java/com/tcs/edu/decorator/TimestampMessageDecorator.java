package com.tcs.edu.decorator;

import java.util.Date;

/**
 * The {@code TimestampMessageDecorator} class contains method for change
 * base message output.
 */
public class TimestampMessageDecorator {
    /**
     * @param message incoming base message
     * @return  message with changes
     * <p>
     * @see com.tcs.edu.printer.ConsolePrinter#print(String)
     */
    public static String decorate(String message){
        String decoratedMessage;
        decoratedMessage = new Date() + " " + message;
        return decoratedMessage;
    }
}
