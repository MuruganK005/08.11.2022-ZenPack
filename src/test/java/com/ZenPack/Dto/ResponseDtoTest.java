package com.ZenPack.Dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDtoTest {
    @Test
    void testConstructor() {
        ResponseDto actualResponseDto = new ResponseDto();
        actualResponseDto.setData("Data");
        ZenPackDto zenPackDto = new ZenPackDto();
        zenPackDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPackDto.setCreatedDate("2020-03-01");
        zenPackDto.setFeatures(new ArrayList<>());
        zenPackDto.setMenus(new ArrayList<>());
        zenPackDto.setName("Name");
        zenPackDto.setUpdatedBy("2020-03-01");
        zenPackDto.setUpdatedTime("2020-03-01");
        zenPackDto.setZenPackId(123L);
        actualResponseDto.setJdata(zenPackDto);
        actualResponseDto.setResponseCode("Response Code");
        actualResponseDto.setResponseDescription("Response Description");
        actualResponseDto.setResponseMessage("Response Message");
        actualResponseDto.setStatusCode(1);
        actualResponseDto.setValidation(true);
        assertSame(zenPackDto, actualResponseDto.getJdata());
        assertEquals("Response Code", actualResponseDto.getResponseCode());
        assertEquals("Response Description", actualResponseDto.getResponseDescription());
        assertEquals("Response Message", actualResponseDto.getResponseMessage());
        assertEquals(1, actualResponseDto.getStatusCode().intValue());
        assertTrue(actualResponseDto.getValidation());
    }
}

