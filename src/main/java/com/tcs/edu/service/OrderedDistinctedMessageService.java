package com.tcs.edu.service;

import com.tcs.edu.decorator.CountDecorator;
import com.tcs.edu.decorator.Decorator;
import com.tcs.edu.decorator.PageDecorator;
import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.OutputOrder;
import com.tcs.edu.exception.LogException;
import com.tcs.edu.printer.Printer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author a.a.krasnov
 */
public class OrderedDistinctedMessageService extends ValidatedService implements MessageService {
    private final Printer printer;
    private final Decorator decorator;
    private final Decorator severityDecorator = new SeverityDecorator();
    private final Decorator countDecorator = new CountDecorator();
    private final Decorator pageDecorator = new PageDecorator();

    public OrderedDistinctedMessageService(Decorator decorator, Printer printer) {
        this.decorator = decorator;
        this.printer = printer;
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
        printDescOrAscMsg(order, messagesIncome);
    }

    /**
     * @param order          incoming order ASC or DESC
     * @param messagesIncome incoming messages
     *                       <p>
     *                       method printDescOrAscMsg help to print info about order number of message in direct or reverse sort
     */
    private void printDescOrAscMsg(OutputOrder order, ArrayList<Message> messagesIncome) {
        if (order.equals(OutputOrder.DESC)) {
            for (int i = messagesIncome.size() - 1; i >= 0; i--) {
                printer.print(
                        getDecoratedMessage(messagesIncome, i)
                );
            }
        } else if (order.equals(OutputOrder.ASC)) {
            for (int i = 0; i <= messagesIncome.size() - 1; i++) {
                printer.print(
                        getDecoratedMessage(messagesIncome, i)
                );
            }
        }
    }

    private String getDecoratedMessage(ArrayList<Message> messagesIncome, int i) {
        return countDecorator.decorate(messagesIncome.get(i)) +
                decorator.decorate(messagesIncome.get(i)) +
                severityDecorator.decorate(messagesIncome.get(i)) +
                pageDecorator.decorate(messagesIncome.get(i));
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