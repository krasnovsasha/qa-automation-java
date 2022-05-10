package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

import java.util.Objects;

/**
 * @author a.a.krasnov
 * <p>
 * JavaBean
 */
public class Message {
    /**
     * Instance var = field
     */
    private final Severity level;
    private final String body;

    public Message(Severity level, String body) {
        this.level = level;
        this.body = body;
    }

    public Message(String body) {
        this(Severity.MINOR, body);
    }

    public Severity getLevel() {
        return level;
    }

    public String getBody() {
        return body;
    }

    /**
     * if messages has the same severity and the same body they are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return level == message.level && body.equals(message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, body);
    }
}