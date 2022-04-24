package com.tcs.edu.service;

import com.tcs.edu.enums.Severity;
import com.tcs.edu.printer.ConsolePrinter;

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
     *
     * @param level   incoming severity
     * @param messages incoming messages
     */
    public static void processMessage(Severity level, String... messages) {

//      It is possible to change decorators positions or turn any of them off for more agile output
        for (String s : messages) {
            ConsolePrinter.print(
                    decorateCount() +
                    decorateTimeStamp(s) +
                    decorateSeverityLevel(level) +
                    separateByPage()
            );
        }
    }
}