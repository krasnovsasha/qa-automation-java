package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

import java.time.Instant;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code TimestampDecorator} class implements method {@code decorate} for change
 * base message output.
 */
public class TimestampDecorator implements Decorator {
    /**
     * @param message incoming base message
     * @return new message with changes
     * <p>
     * side-effect method changes console output cause it adds time
     */
    @Override
    public String decorate(Message message) {
        return String.format(" %s %s ", Instant.now(), message.getBody());
    }
}