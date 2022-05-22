package com.tcs.edu.exception;

/**
 * @author a.a.krasnov
 */
public class LogException extends IllegalArgumentException {
    public LogException(String message, Throwable cause) {
        super(message,cause);
    }
}