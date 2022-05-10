package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code PageDecorator} class imnplements method {@code decorate} for change
 * base message output.
 */
public class PageDecorator implements Decorator {
    private int messageCount = 1;
    private static final int PAGE_SIZE = 2;

    /**
     * @param message incoming message
     * @return separator
     * <p>
     * side-effect method can change console output because it may add the line between rows
     */

    @Override
    public String decorate(Message message) {
        String separator = "";
        if (messageCount++ % PAGE_SIZE == 0) {
            separator = "\n---";
        }
        return separator;
    }
}