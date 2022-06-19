package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Severity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }

    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return messages.values().stream().filter(m->m.getLevel().equals(by)).collect(Collectors.toList());
    }
}