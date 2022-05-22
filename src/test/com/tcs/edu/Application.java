package com.tcs.edu;

import com.tcs.edu.decorator.TimestampDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;

import static com.tcs.edu.enums.OutputOrder.ASC;
import static com.tcs.edu.enums.Severity.*;

class Application {

    private static final String TEST_HEADER = "@Test\n@Description = ";
    private static final String TEST_SEPARATOR = "*************\n";

    public static void main(String[] args) {
        //test data
        Message message1 = new Message(MAJOR, "test message 1");
        Message message2 = new Message(MAJOR, "test message 1");
        Message message3 = new Message(MINOR, "test message 2");
        Message message4 = new Message(MINOR, "test message 3");
        Message message5 = new Message(REGULAR, null);
        Message message6 = null;
        Message message7 = new Message(MAJOR, "");
        Message message8 = new Message(MAJOR, "system out message");

        makeCompareTest(message1, message2);
        makeCompareTest(message1, message3);

        makeTest("message body is null", message5);
        makeTest("message is null:", message6);
        makeTest("message body is empty:", message7);
        makeTest("array of messages is null:", message1, null);
        makeTest("direct console output", message8);
    }

    private static void makeCompareTest(Message messageToCompare1, Message messageToCompare2) {
        System.out.printf(
                "%s equals/hashcode%nmessage1 hashcode: %s%nmessage2 hashcode: %s%nmessage1 == message 2%n%s%s",
                TEST_HEADER, messageToCompare1.hashCode(), messageToCompare2.hashCode(),
                messageToCompare1.equals(messageToCompare2), TEST_SEPARATOR);
    }

    private static void makeTest(String scenario, Message message, Message... messages) {
        MessageService service = new OrderedDistinctedMessageService(
                new TimestampDecorator(),
                new ConsolePrinter()
        );
        System.out.println(TEST_HEADER + scenario);
        service.log(ASC, message, messages);
        System.out.println(TEST_SEPARATOR);
    }
}