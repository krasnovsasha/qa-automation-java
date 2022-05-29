package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author a.a.krasnov
 * <p>
 * CRUD
 */
public class HashMapMessageRepository implements MessageRepository {
    private final Map<UUID, Message> messages = new HashMap<>();

    @Override
    public UUID create(Message message) {
        messages.put(message.setId(UUID.randomUUID()), message);
        return message.getId();
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messages.get(key);
    }
}
