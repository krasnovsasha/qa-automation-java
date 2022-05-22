package com.tcs.edu;

import com.tcs.edu.decorator.TimestampDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;

import static com.tcs.edu.enums.OutputOrder.ASC;
import static com.tcs.edu.enums.OutputOrder.DESC;
import static com.tcs.edu.enums.Severity.*;

class Application {
    public static void main(String[] args) {
        //service processes only unique messages
        MessageService service = new OrderedDistinctedMessageService(
                new TimestampDecorator(),
                new ConsolePrinter()
        );
        //test data
        Message message1 = new Message(MAJOR, "test message 1");
        Message message2 = new Message(MAJOR, "test message 1");
        Message message3 = new Message(MINOR, "test message 2");
        Message message4 = new Message(MINOR, "test message 3");
        Message message5 = new Message(REGULAR, null);
        Message message6 = new Message(MAJOR, "");

        System.out.println("Test array of messages is null:");
        service.log(ASC, message1, null);
        System.out.println("*************\n");

        System.out.println("Test message is null and body is empty:");
        service.log(DESC, message1, message2, message3, message4, message5, message6);
        System.out.println("*************\n");

        System.out.println("Test direct console output");
        System.out.println(new Message(MAJOR, "system out message"));
        System.out.println("*************\n");

        System.out.println("Test compare messages");
        System.out.println(message1.equals(message2));
        System.out.println(message1.equals(message3));
        System.out.println("*************\n");

        System.out.println("Test equals/hashcode");
        System.out.println("message1 hashcode: " + message1.hashCode());
        System.out.println("message2 hashcode: " + message2.hashCode());
        System.out.println("message1 == message 2: " + message1.equals(message2));
        System.out.println("message1 hashcode: " + message1.hashCode());
        System.out.println("message3 hashcode: " + message3.hashCode());
        System.out.println("message1 == message 2: " + message1.equals(message3));
        System.out.println("*************\n");
    }
}