package com.tcs.edu.service;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.OutputOrder;
import com.tcs.edu.enums.Severity;

import java.util.Collection;
import java.util.UUID;

public interface MessageService {
    UUID log(OutputOrder order, Message message, Message... messages);

    Message findByPrimaryKey(UUID key);

    Collection<Message> findAll();

    Collection<Message> findBySeverity(Severity by);
}