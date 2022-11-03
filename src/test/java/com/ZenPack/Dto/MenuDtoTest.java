package com.ZenPack.Dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuDtoTest {
    @Test
    void testConstructor() {
        MenuDto actualMenuDto = new MenuDto();
        actualMenuDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        actualMenuDto.setCreatedTime("Jan 1, 2020 8:00am GMT+0100");
        actualMenuDto.setDroppable(true);
        actualMenuDto.setFeatureId("42");
        actualMenuDto.setFeatureUrl("https://example.org/example");
        actualMenuDto.setIcon("Icon");
        actualMenuDto.setId(1);
        actualMenuDto.setIsSettingMenu(true);
        actualMenuDto.setParent(1);
        actualMenuDto.setParentMenuId(123);
        actualMenuDto.setText("Text");
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualMenuDto.getCreatedBy());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualMenuDto.getCreatedTime());
        assertTrue(actualMenuDto.getDroppable());
        assertEquals("42", actualMenuDto.getFeatureId());
        assertEquals("https://example.org/example", actualMenuDto.getFeatureUrl());
        assertEquals("Icon", actualMenuDto.getIcon());
        assertEquals(1, actualMenuDto.getId().intValue());
        assertTrue(actualMenuDto.getIsSettingMenu());
        assertEquals(1, actualMenuDto.getParent().intValue());
        assertEquals(123, actualMenuDto.getParentMenuId().intValue());
        assertEquals("Text", actualMenuDto.getText());
    }
}

