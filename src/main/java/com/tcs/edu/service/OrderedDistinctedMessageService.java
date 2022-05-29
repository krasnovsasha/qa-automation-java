package com.tcs.edu.service;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.OutputOrder;
import com.tcs.edu.exception.LogException;
import com.tcs.edu.repository.MessageRepository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

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
     */
    public void log(OutputOrder order, Message message, Message... messages) {
        ArrayList<Message> messagesIncome = new ArrayList<>(getDistinct(message, messages));
        messagesIncome.forEach(messageRepository::create);
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