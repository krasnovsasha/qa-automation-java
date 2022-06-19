package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;

import java.util.UUID;

import static com.tcs.edu.enums.OutputOrder.ASC;
import static com.tcs.edu.enums.Severity.MAJOR;
import static com.tcs.edu.enums.Severity.MINOR;

class Application {

    public static void main(String[] args) {
        MessageService service = new OrderedDistinctedMessageService(
                new HashMapMessageRepository()
        );
        //test data
        Message message1 = new Message(MAJOR, "test message 1");
        Message message2 = new Message(MINOR, "test message 2");
        Message message3 = new Message(MINOR, "test message 3");
        Message message4 = new Message(MINOR, "test message 4");
        Message message5 = new Message(MINOR, "test message 5");

        UUID key1 = service.log(ASC, message1);
        UUID key2 = service.log(ASC, message2);
        service.log(ASC, message3);
        service.log(ASC, message4);
        service.log(ASC, message5);

        System.out.println(service.findByPrimaryKey(key1));
        System.out.println(service.findByPrimaryKey(key2));
        service.findAll().forEach(System.out::println);
        service.findBySeverity(MINOR).forEach(System.out::println);
    }
}