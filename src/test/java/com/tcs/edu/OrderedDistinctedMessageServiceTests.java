package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.tcs.edu.enums.OutputOrder.ASC;
import static com.tcs.edu.enums.Severity.MAJOR;
import static com.tcs.edu.enums.Severity.MINOR;

class OrderedDistinctedMessageServiceTests {
    private MessageService service;
    private String messageBody = "test message";
    private String anotherMessageBody = "another test message";

    @BeforeEach
    public void setUp() {
        service = new OrderedDistinctedMessageService(
                new HashMapMessageRepository()
        );
    }

    @Test
    @DisplayName("Find message by UUID")
    void shouldFindByPrimaryKey() {

        //region Given
        Message message = new Message(MAJOR, messageBody);
        //region When
        UUID key = service.log(ASC, message);
        //region Then
        Assertions.assertEquals(message, service.findByPrimaryKey(key), "message " + message + " was not found by UUID " + key);
    }

    @Test
    @DisplayName("Not found if UUID != message UUID")
    void shouldNotFindByWrongPrimaryKey() {

        //region Given
        Message message = new Message(MAJOR, messageBody);
        //region When
        UUID anotherKey = service.log(ASC, new Message(MAJOR, anotherMessageBody));
        //region Then
        Assertions.assertNotEquals(message, service.findByPrimaryKey(anotherKey), "message was found using UUID from another message");
    }

    @Test
    @DisplayName("Find all messages that was logged by service")
    void shouldFindAllLoggedMessages() {

        //region Given
        Message message = new Message(MAJOR, messageBody);
        Message anotherMessage = new Message(MINOR, anotherMessageBody);
        //region When
        service.log(ASC, message);
        service.log(ASC, anotherMessage);
        //region Then
        Assertions.assertEquals(2, service.findAll().size(), "service contains wrong count of messages");
        Assertions.assertTrue(service.findAll().contains(message), "message " + message + " was not logged by service");
        Assertions.assertTrue(service.findAll().contains(anotherMessage), "message " + anotherMessage + " was not logged by service");
    }

    @Test
    @DisplayName("Not found if message was not logged by service")
    void shouldNotFindMessageWasNotLogged() {

        //region Given
        Message message = new Message(MAJOR, messageBody);
        Message anotherMessage = new Message(MINOR, anotherMessageBody);
        //region When
        service.log(ASC, message);
        //region Then
        Assertions.assertEquals(1, service.findAll().size(), "service contains wrong count of messages");
        Assertions.assertTrue(service.findAll().contains(message));
        Assertions.assertFalse(service.findAll().contains(anotherMessage));
    }

    @Test
    @DisplayName("Find messages by severity level")
    void shouldFindAllMessagesBySeverity() {

        //region Given
        Severity severity = MAJOR;
        Message message = new Message(severity, messageBody);
        Message anotherMessage = new Message(MINOR, anotherMessageBody);
        //region When
        service.log(ASC, message);
        service.log(ASC, anotherMessage);
        //region Then
        Assertions.assertTrue(service.findBySeverity(severity).contains(message), "message " + message + " was not found by severity " + severity);
        Assertions.assertFalse(service.findBySeverity(severity).contains(anotherMessage), "message " + anotherMessage + " was found by severity " + severity);
    }
}
