package com.ZenPack.Dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ZenPackDtoTest {
    @Test
    void testConstructor() {
        ZenPackDto actualZenPackDto = new ZenPackDto();
        actualZenPackDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        actualZenPackDto.setCreatedDate("2020-03-01");
        ArrayList<FeatureDto> featureDtoList = new ArrayList<>();
        actualZenPackDto.setFeatures(featureDtoList);
        ArrayList<MenuDto> menuDtoList = new ArrayList<>();
        actualZenPackDto.setMenus(menuDtoList);
        actualZenPackDto.setName("Name");
        actualZenPackDto.setUpdatedBy("2020-03-01");
        actualZenPackDto.setUpdatedTime("2020-03-01");
        actualZenPackDto.setZenPackId(123L);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualZenPackDto.getCreatedBy());
        assertEquals("2020-03-01", actualZenPackDto.getCreatedDate());
        assertSame(featureDtoList, actualZenPackDto.getFeatures());
        assertSame(menuDtoList, actualZenPackDto.getMenus());
        assertEquals("Name", actualZenPackDto.getName());
        assertEquals("2020-03-01", actualZenPackDto.getUpdatedBy());
        assertEquals("2020-03-01", actualZenPackDto.getUpdatedTime());
        assertEquals(123L, actualZenPackDto.getZenPackId().longValue());
    }
}

