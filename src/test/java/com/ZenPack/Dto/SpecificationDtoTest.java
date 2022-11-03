package com.ZenPack.Dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class SpecificationDtoTest {
    @Test
    void testConstructor() {
        SpecificationDto actualSpecificationDto = new SpecificationDto();
        actualSpecificationDto.setKey("Key");
        actualSpecificationDto.setOperation("Operation");
        ArrayList<Object> objectList = new ArrayList<>();
        actualSpecificationDto.setVaLues(objectList);
        actualSpecificationDto.setValue("42");
        assertEquals("Key", actualSpecificationDto.getKey());
        assertEquals("Operation", actualSpecificationDto.getOperation());
        assertSame(objectList, actualSpecificationDto.getVaLues());
        assertEquals("42", actualSpecificationDto.getValue());
    }
    @Test
    void testConstructor2() {
        SpecificationDto actualSpecificationDto = new SpecificationDto("Key", "Operation", "42", new ArrayList<>());
        actualSpecificationDto.setKey("Key");
        actualSpecificationDto.setOperation("Operation");
        ArrayList<Object> objectList = new ArrayList<>();
        actualSpecificationDto.setVaLues(objectList);
        actualSpecificationDto.setValue("42");
        assertEquals("Key", actualSpecificationDto.getKey());
        assertEquals("Operation", actualSpecificationDto.getOperation());
        assertSame(objectList, actualSpecificationDto.getVaLues());
        assertEquals("42", actualSpecificationDto.getValue());
    }
}

