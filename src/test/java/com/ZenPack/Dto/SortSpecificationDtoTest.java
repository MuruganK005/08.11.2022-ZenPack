package com.ZenPack.Dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortSpecificationDtoTest {

    @Test
    void testConstructor() {
        SortSpecificationDto actualSortSpecificationDto = new SortSpecificationDto();
        actualSortSpecificationDto.setColId("42");
        actualSortSpecificationDto.setSort("Sort");
        assertEquals("42", actualSortSpecificationDto.getColId());
        assertEquals("Sort", actualSortSpecificationDto.getSort());
    }
}

