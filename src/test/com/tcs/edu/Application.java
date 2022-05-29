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

        UUID key1 = service.log(ASC, message1);
        UUID key2 = service.log(ASC, message2);

        System.out.println(service.findByPrimaryKey(key1));
        System.out.println(service.findByPrimaryKey(key2));
    }
}