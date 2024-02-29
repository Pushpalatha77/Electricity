package com.electricity.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.electricity.api.dto.Message;

public class MessageTest {

    @Test
    void testGetMsg() {
        // Create a mock Message object
        Message mockMessage = mock(Message.class);

        // Set the expected message value
        String expectedMessage = "Hello, world!";
        when(mockMessage.getMsg()).thenReturn(expectedMessage);

        // Call the getMsg() method
        String actualMessage = mockMessage.getMsg();

        // Verify that the method was called
        assertEquals(expectedMessage, actualMessage);
    }
}
