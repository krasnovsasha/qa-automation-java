package com.tcs.edu.service;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.OutputOrder;
import com.tcs.edu.exception.LogException;
import com.tcs.edu.repository.MessageRepository;

import java.util.*;

/**
 * @author a.a.krasnov
 */
public class OrderedDistinctedMessageService extends ValidatedService implements MessageService {
    private final MessageRepository messageRepository;

    public OrderedDistinctedMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * API
     * <p>
     *
     * @param order    incoming order ASC or DESC
     * @param messages incoming messages
     * @return
     */
    public UUID log(OutputOrder order, Message message, Message... messages) {
        ArrayList<Message> messagesIncome = new ArrayList<>(getDistinct(message, messages));
        for (Message m : messagesIncome) {
            return messageRepository.create(m);
        }
        return null;
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messageRepository.findByPrimaryKey(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messageRepository.findAll();
    }

    /**
     * @param message  incoming message
     * @param messages incoming array of messages
     * @return messagesIncome
     * <p>
     * method getDistinct help to create list of messages in case of need distinct only
     */
    private Set<Message> getDistinct(Message message, Message[] messages) {
        Set<Message> messagesIncome = new LinkedHashSet<>();
        try {
            super.isArgValid(message);
            messagesIncome.add(message);
            super.isArgsValid(messages);
            for (Message msg : messages) {
                if (!super.isArgValid(msg)) {
                    messagesIncome.add(msg);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new LogException("notValidArgMessage", e);
        }
        return messagesIncome;
    }
}