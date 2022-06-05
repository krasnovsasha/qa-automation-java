package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

import java.util.Objects;
import java.util.UUID;

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
    private UUID id;

    public UUID getId() {
        return id;
    }

    public UUID setId(UUID id) {
        this.id = id;
        return id;
    }

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

    @Override
    public String toString() {
        return "Message{" +
                "level=" + level +
                ", body='" + body + '\'' +
                ", id=" + id +
                '}';
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