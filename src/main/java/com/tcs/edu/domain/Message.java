package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

/**
 * @author a.a.krasnov
 * <p>
 * JavaBean
 */
public class Message {
    /**
     * Instance var = field
     */
    private Severity level;
    private String body;

    public Message(Severity level, String body) {
        this.level = level;
        this.body = body;
    }

    public Severity getLevel() {
        return level;
    }

    public String getBody() {
        return body;
    }
}
