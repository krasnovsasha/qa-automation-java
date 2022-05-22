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
        if (message == null) throw new IllegalArgumentException("message is null");
        if (message.getBody() == null) throw new IllegalArgumentException("message body is null");
        if (message.getBody().equals("")) throw new IllegalArgumentException("message body is empty");
        return message.getBody().isEmpty();
    }

    /**
     * @param messages incoming messages
     * @return true if array of messages is not null
     */
    public boolean isArgsValid(Message... messages) {
        if (messages == null) throw new IllegalArgumentException("array of messages is null");
        return messages != null;
    }
}
