package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code SeverityDecorator} class implements method {@code decorate} for change
 * base message output.
 */
public class SeverityDecorator implements Decorator {
    /**
     * @param message incoming message
     * @return severityLevel
     * <p>
     * side-effect method changes console output cause it adds string severity designation
     */
    @Override
    public String decorate(Message message) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_YELLOW = "\u001B[33m";
        String severityLevel = null;
        switch (message.getLevel()) {
            case MINOR:
                severityLevel = "()";
                break;
            case REGULAR:
                severityLevel = "(" + ANSI_YELLOW + "!" + ANSI_RESET + ")";
                break;
            case MAJOR:
                severityLevel = "(" + ANSI_RED + "!!!" + ANSI_RESET + ")";
                break;
        }
        return severityLevel;
    }
}