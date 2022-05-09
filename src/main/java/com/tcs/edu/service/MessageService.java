package com.tcs.edu.service;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.OutputOrder;
import com.tcs.edu.printer.ConsolePrinter;

import java.util.ArrayList;

import static com.tcs.edu.decorator.SeverityDecorator.decorateSeverityLevel;
import static com.tcs.edu.decorator.TimestampDecorator.decorateTimeStamp;
import static com.tcs.edu.decorator.PageDecorator.*;
import static com.tcs.edu.decorator.CountDecorator.decorateCount;

/**
 * @author a.a.krasnov
 */
public class MessageService {
    /**
     * API
     * <p>
     *
     * @param messages incoming messages
     *                 <p>
     *                 In method ConsolePrinter.print()
     */
    public static void log(Message message, Message... messages) {
        ArrayList<Message> messagesIncome = incomeMessageGenerate(message, messages);
        for (Message incomeMessage : messagesIncome) {
            ConsolePrinter.print(
                    decorateCount() +
                            decorateTimeStamp(incomeMessage.getBody()) +
                            decorateSeverityLevel(incomeMessage.getLevel()) +
                            separateByPage()
            );
        }
    }

    /**
     * API
     * <p>
     *
     * @param order    incoming order ASC or DESC
     * @param messages incoming messages
     */
    public static void log(OutputOrder order, Message message, Message... messages) {
        ArrayList<Message> messagesIncome = incomeMessageGenerate(message, messages);
        printDescOrAscMsg(order, messagesIncome);
    }

    /**
     * API
     * <p>
     *
     * @param order    incoming order ASC or DESC
     * @param doubling incoming setting for print all or unique messages
     * @param messages incoming messages
     */
    public static void log(OutputOrder order, Doubling doubling, Message message, Message... messages) {
        ArrayList<Message> messagesIncome = incomeMessageGenerate(doubling, message, messages);
        printDescOrAscMsg(order, messagesIncome);
    }

    /**
     * @param order          incoming order ASC or DESC
     * @param messagesIncome incoming messages
     *                       <p>
     *                       method printDescOrAscMsg help to print info about order number of message in direct or reverse sort
     */
    private static void printDescOrAscMsg(OutputOrder order, ArrayList<Message> messagesIncome) {
        if (order.equals(OutputOrder.DESC)) {
            for (int i = messagesIncome.size() - 1; i >= 0; i--) {
                ConsolePrinter.print(
                        decorateCount() +
                                decorateTimeStamp(messagesIncome.get(i).getBody() + " " + (i + 1)) +
                                decorateSeverityLevel(messagesIncome.get(i).getLevel()) +
                                separateByPage()
                );
            }
        } else if (order.equals(OutputOrder.ASC)) {
            for (int i = 0; i <= messagesIncome.size() - 1; i++) {
                ConsolePrinter.print(
                        decorateCount() +
                                decorateTimeStamp(messagesIncome.get(i).getBody() + " " + (i + 1)) +
                                decorateSeverityLevel(messagesIncome.get(i).getLevel()) +
                                separateByPage()
                );
            }
        }
    }

    /**
     * @param message  incoming message
     * @param messages incoming array of messages
     * @return messagesIncome
     * <p>
     * method incomeMessageGenerate help to create list of incoming messages without nulls
     */
    private static ArrayList<Message> incomeMessageGenerate(Message message, Message... messages) {
        ArrayList<Message> messagesIncome = new ArrayList<>();
        if (message != null) {
            messagesIncome.add(message);
        }
        if (messages != null) {
            for (Message messageIncome : messages) {
                if (messageIncome != null) {
                    messagesIncome.add(messageIncome);
                }
            }
        }
        return messagesIncome;
    }

    /**
     * @param doubling incoming enum which help to add all or only distinct messages
     * @param message  incoming message
     * @param messages incoming array of messages
     * @return messagesIncome
     * <p>
     * method incomeMessageGenerate help to create list of incoming messages without nulls and to add all messages or only distinct ones
     */
    private static ArrayList<Message> incomeMessageGenerate(Doubling doubling, Message message, Message... messages) {
        ArrayList<Message> messagesIncome = new ArrayList<>();
        if (doubling.equals(Doubling.DISTINCT)) {
            if (message != null) {
                messagesIncome.add(message);
            }
            if (messages != null) {
                for (Message messageIncome : messages) {
                    if (messageIncome != null && !messagesIncome.contains(messageIncome)) {
                        messagesIncome.add(messageIncome);
                    }
                }
            }
        }
        if (doubling.equals(Doubling.DOUBLES)) {
            if (message != null) {
                messagesIncome.add(message);
            }
            if (messages != null) {
                for (Message messageIncome : messages) {
                    if (messageIncome != null) {
                        messagesIncome.add(messageIncome);
                    }
                }
            }
        }
        return messagesIncome;
    }
}