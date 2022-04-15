package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code TimestampMessageDecorator} class contains method {@code decorateTimeStamp} for change
 * base message output.
 */
public class TimestampMessageDecorator {
    /**
     * @param message incoming base message
     * @return new message with changes
     * <p>
     * side-effect method changes console output cause it adds time
     */
    public static String decorateTimeStamp(String message) {
        return String.format(" %s %s ",Instant.now(), message);
    }
}
