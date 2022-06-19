package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.tcs.edu.enums.Severity.MAJOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HashMapMessageRepositoryTests {
    private HashMapMessageRepository hashMapMessageRepository;
    private Message message;

    @BeforeEach
    void setUp() {
        hashMapMessageRepository = new HashMapMessageRepository();
        message = new Message(MAJOR, "message");
    }

    @Test
    void shouldCreateUniqueUUID() {
        assertNotEquals(hashMapMessageRepository.create(message), hashMapMessageRepository.create(message), "UUID not unique");
    }

    @Test
    void shouldFindByPrimaryKey() {
        UUID key = hashMapMessageRepository.create(message);
        assertEquals(message, hashMapMessageRepository.findByPrimaryKey(key), "messages not equals");
    }
}
