package com.salonwebservice.pi.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    void testExceptionInheritance() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Test");
        assertTrue(exception instanceof RuntimeException);
    }
}