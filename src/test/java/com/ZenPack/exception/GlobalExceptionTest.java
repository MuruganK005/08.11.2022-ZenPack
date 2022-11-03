package com.ZenPack.exception;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobalExceptionTest {

    @Test
    void testHandleTeamException() {
        GlobalException globalException = new GlobalException();
        ResponseEntity<?> actualHandleTeamExceptionResult = globalException
                .handleTeamException(new ZenPackException(HttpStatus.CONTINUE, "An error occurred"));
        assertTrue(actualHandleTeamExceptionResult.hasBody());
        assertTrue(actualHandleTeamExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CONTINUE, actualHandleTeamExceptionResult.getStatusCode());
        assertEquals("An error occurred", ((ErrorResponse) actualHandleTeamExceptionResult.getBody()).getMessage());
        assertEquals(HttpStatus.CONTINUE, ((ErrorResponse) actualHandleTeamExceptionResult.getBody()).getStatus());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testHandleTeamException2() {
        (new GlobalException()).handleTeamException(null);
    }
}

