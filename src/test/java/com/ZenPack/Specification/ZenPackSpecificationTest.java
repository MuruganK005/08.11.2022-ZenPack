package com.ZenPack.Specification;

import com.ZenPack.Dto.SpecificationDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class ZenPackSpecificationTest {

    @Test
    void testConstructor() {
        assertNull((new ZenPackSpecification()).criteria);
    }


    @Test
    void testConstructor2() {
        SpecificationDto specificationDto = (new ZenPackSpecification(new SpecificationDto())).criteria;
        assertNull(specificationDto.getKey());
        assertNull(specificationDto.getValue());
        assertNull(specificationDto.getVaLues());
        assertNull(specificationDto.getOperation());
    }
}

