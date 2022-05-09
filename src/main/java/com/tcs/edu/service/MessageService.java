package com.tcs.edu.service;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;
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
     * @param level    incoming severity
     * @param messages incoming messages
     * <p>
     * In method ConsolePrinter.print()
     * It is possible to change decorators positions or turn any of them off for more agile output
     */
    public static void log(Severity level, String message, String... messages) {
        ArrayList<String> messagesIncome = incomeMessageGenerate(message, messages);
        for (String s : messagesIncome) {
            ConsolePrinter.print(
                    decorateCount() +
                    decorateTimeStamp(s) +
                    decorateSeverityLevel(level) +
                    separateByPage()
            );
        }
    }
    /**
     * API
     * <p>
     * @param level    incoming severity
     * @param order    incoming order ASC or DESC
     * @param messages incoming messages
     */
    public static void log(Severity level, MessageOrder order, String message, String... messages) {
        ArrayList<String> messagesIncome = incomeMessageGenerate(message, messages);
        printDescOrAscMsg(level, order, messagesIncome);
    }
    /**
     * API
     * <p>
     * @param level    incoming severity
     * @param order    incoming order ASC or DESC
     * @param doubling incoming setting for print all or unique messages
     * @param messages incoming messages
     */
    public static void log(Severity level, MessageOrder order, Doubling doubling, String message, String... messages) {
        ArrayList<String> messagesIncome = incomeMessageGenerate(doubling, message, messages);
        printDescOrAscMsg(level, order, messagesIncome);
    }
    /**
     * @param level    incoming severity
     * @param order    incoming order ASC or DESC
     * @param messagesIncome incoming messages
     * <p>
     * method printDescOrAscMsg help to print info about order number of message in direct or reverse sort
     */
    private static void printDescOrAscMsg(Severity level, MessageOrder order, ArrayList<String> messagesIncome) {
        if (order.equals(MessageOrder.DESC)) {
            for (int i = messagesIncome.size() - 1; i >= 0; i--) {
                ConsolePrinter.print(
                        decorateCount() +
                        decorateTimeStamp(messagesIncome.get(i) + " " + (i + 1)) +
                        decorateSeverityLevel(level) +
                        separateByPage()
                );
            }
        } else if (order.equals(MessageOrder.ASC)) {
            for (int i = 0; i <= messagesIncome.size() - 1; i++) {
                ConsolePrinter.print(
                        decorateCount() +
                        decorateTimeStamp(messagesIncome.get(i) + " " + (i + 1)) +
                        decorateSeverityLevel(level) +
                        separateByPage()
                );
            }
        }
    }
    /**
     * @param message    incoming message
     * @param messages    incoming array of messages
     * @return messagesIncome
     * <p>
     * method incomeMessageGenerate help to create list of incoming messages without nulls
     */
    private static ArrayList<String> incomeMessageGenerate(String message, String... messages) {
        ArrayList<String> messagesIncome = new ArrayList<>();
        if (message != null) {
            messagesIncome.add(message);
        }
        if (messages != null) {
            for (String s : messages) {
                if (s != null) {
                    messagesIncome.add(s);
                }
            }
        }
        return messagesIncome;
    }
    /**
     * @param doubling   incoming enum which help to add all or only distinct messages
     * @param message    incoming message
     * @param messages   incoming array of messages
     * @return messagesIncome
     * <p>
     * method incomeMessageGenerate help to create list of incoming messages without nulls and to add all messages or only distinct ones
     */
    private static ArrayList<String> incomeMessageGenerate(Doubling doubling, String message, String... messages) {
        ArrayList<String> messagesIncome = new ArrayList<>();
        if (doubling.equals(Doubling.DISTINCT)) {
            if (message != null) {
                messagesIncome.add(message);
            }
            if (messages != null) {
                for (String s : messages) {
                    if (s != null && !messagesIncome.contains(s)) {
                        messagesIncome.add(s);
                    }
                }
            }
        }
        if (doubling.equals(Doubling.DOUBLES)) {
            if (message != null) {
                messagesIncome.add(message);
            }
            if (messages != null) {
                for (String s : messages) {
                    if (s != null) {
                        messagesIncome.add(s);
                    }
                }
            }
        }
        return messagesIncome;
    }
}