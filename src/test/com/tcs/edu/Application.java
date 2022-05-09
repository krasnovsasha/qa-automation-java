package com.tcs.edu;

import com.tcs.edu.domain.Message;

import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.OutputOrder.*;
import static com.tcs.edu.enums.Doubling.*;
import static com.tcs.edu.service.MessageService.log;

class Application {
    public static void main(String[] args) {
        //test for one message
        log(new Message(MINOR, "this message has minor priority"));
        //test for ascending messages
        log(ASC, new Message(REGULAR, "message first"), new Message(REGULAR, "message second"));
        //test for descending messages
        log(DESC, new Message(REGULAR, "message first"), new Message(REGULAR, "message second"));
        //test for distinct-only messages
        log(ASC, DISTINCT, new Message(MAJOR, "message1"), new Message(MAJOR, "message1"), new Message(REGULAR, "message3"));
        //test for messages which may have doubles
        log(DESC, DOUBLES, new Message(MAJOR, "message1"), new Message(MAJOR, "message1"), new Message(REGULAR, "message3"));
    }
}