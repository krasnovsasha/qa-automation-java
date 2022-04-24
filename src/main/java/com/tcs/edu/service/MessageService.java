package com.tcs.edu.service;

import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.printer.ConsolePrinter;

import java.util.ArrayList;

import static com.tcs.edu.decorator.SeverityDecorator.decorateSeverityLevel;
import static com.tcs.edu.decorator.TimestampMessageDecorator.decorateTimeStamp;
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
    public static void processMessage(Severity level, String message, String... messages) {
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

    public static void processMessage(Severity level, MessageOrder order, String message, String... messages) {
        ArrayList<String> messagesIncome = incomeMessageGenerate(message, messages);
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
}