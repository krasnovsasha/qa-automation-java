package com.tcs.edu.service;

import com.tcs.edu.domain.Message;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code ValidatedService} class provide method {@code isArgValid} or {@code isArgsValid} which
 * check messages for correctness
 */
public abstract class ValidatedService {
    /**
     * @param message incoming message
     * @return true if message is not null or body not empty
     */
    public boolean isArgValid(Message message) {
        if (message == null) return true;
        if (message.getBody() == null) return true;
        return message.getBody().isEmpty();
    }

    /**
     * @param messages incoming messages
     * @return true if array of messages is not null
     */
    public boolean isArgsValid(Message... messages) {
        return messages != null;
    }
}
