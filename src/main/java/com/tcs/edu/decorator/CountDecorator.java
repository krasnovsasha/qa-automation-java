package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code CountDecorator} class implements method {@code decorate} for change
 * base message output. This method add count for every message row
 */
public class CountDecorator implements Decorator {
    private int counter = 1;

    /**
     * @param message incoming message
     * @return counter which was increased
     */
    @Override
    public String decorate(Message message) {
        return String.valueOf(counter++);
    }
}