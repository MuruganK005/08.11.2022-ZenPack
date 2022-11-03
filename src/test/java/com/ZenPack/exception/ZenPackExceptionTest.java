package com.ZenPack.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ZenPackExceptionTest {
    @Test
    void testConstructor() {
        ZenPackException actualZenPackException = new ZenPackException(HttpStatus.CONTINUE, "An error occurred");

        assertNull(actualZenPackException.getCause());
        assertEquals(0, actualZenPackException.getSuppressed().length);
        assertEquals(HttpStatus.CONTINUE, actualZenPackException.getStatus());
        assertEquals("An error occurred", actualZenPackException.getMessage());
        assertEquals("An error occurred", actualZenPackException.getLocalizedMessage());
        assertEquals("An error occurred", actualZenPackException.getErrorMessage());
    }
}

