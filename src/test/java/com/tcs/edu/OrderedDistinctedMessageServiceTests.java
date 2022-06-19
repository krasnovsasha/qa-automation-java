package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.exception.LogException;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.tcs.edu.enums.OutputOrder.ASC;
import static com.tcs.edu.enums.Severity.MAJOR;
import static com.tcs.edu.enums.Severity.MINOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderedDistinctedMessageServiceTests {
    private MessageService service;
    private final String messageBody = "test message";
    private final String anotherMessageBody = "another test message";

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
        assertEquals(message, service.findByPrimaryKey(key), "message " + message + " was not found by UUID " + key);
    }

    @Test
    @DisplayName("Not found if UUID != message UUID")
    void shouldNotFindByWrongPrimaryKey() {

        //region Given
        Message message = new Message(MAJOR, messageBody);
        //region When
        UUID anotherKey = service.log(ASC, new Message(MAJOR, anotherMessageBody));
        //region Then
        assertNotEquals(message, service.findByPrimaryKey(anotherKey), "message was found using UUID from another message");
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
        assertAll(
                () -> assertEquals(2, service.findAll().size(), "service contains wrong count of messages"),
                () -> assertTrue(service.findAll().contains(message), "message " + message + " was not logged by service"),
                () -> assertTrue(service.findAll().contains(anotherMessage), "message " + anotherMessage + " was not logged by service")
        );
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
        assertAll(
                () -> assertEquals(1, service.findAll().size(), "service contains wrong count of messages"),
                () -> assertTrue(service.findAll().contains(message)),
                () -> assertFalse(service.findAll().contains(anotherMessage))
        );

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
        assertThat(service.findBySeverity(severity)).contains(message).doesNotContain(anotherMessage);
    }

    @Test
    @DisplayName("Get exception if message is null")
    void shouldGetErrorIfMessageIsNull() {
        assertThrows(LogException.class, () -> service.log(ASC, null));
    }

    @Test
    @DisplayName("Get exception if message body is null")
    void shouldGetErrorIfMessageBodyIsNull() {
        assertThrows(LogException.class, () -> service.log(ASC, new Message(MAJOR, null)));
    }

    @Test
    @DisplayName("Get exception if message body is empty")
    void shouldGetErrorIfMessageBodyIsEmpty() {
        assertThrows(LogException.class, () -> service.log(ASC, new Message(MAJOR, "")));

    }
}
