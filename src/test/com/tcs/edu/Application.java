package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;

import static com.tcs.edu.enums.OutputOrder.ASC;
import static com.tcs.edu.enums.Severity.MAJOR;

class Application {

    public static void main(String[] args) {
        //test data
        Message message1 = new Message(MAJOR, "test message 1");

        MessageService service = new OrderedDistinctedMessageService(
                new HashMapMessageRepository()
        );
        service.log(ASC, message1);
    }
}