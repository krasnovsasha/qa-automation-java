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
//        //test ASC
        service.log(ASC, message1, null);
        //test DESC
        service.log(DESC, message1, message2, message3, message4, message5, message6);
    }
}