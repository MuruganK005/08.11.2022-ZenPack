package com.ZenPack.Dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeatureDtoTest {
    @Test
    void testConstructor() {
        FeatureDto actualFeatureDto = new FeatureDto();
        actualFeatureDto.setFeatureId("42");
        actualFeatureDto.setFeatureUrl("https://example.org/example");
        actualFeatureDto.setIcon("Icon");
        actualFeatureDto.setId(1);
        actualFeatureDto.setIsSettingMenu(true);
        actualFeatureDto.setParent(1);
        actualFeatureDto.setText("Text");
        assertEquals("42", actualFeatureDto.getFeatureId());
        assertEquals("https://example.org/example", actualFeatureDto.getFeatureUrl());
        assertEquals("Icon", actualFeatureDto.getIcon());
        assertEquals(1, actualFeatureDto.getId().intValue());
        assertTrue(actualFeatureDto.getIsSettingMenu());
        assertEquals(1, actualFeatureDto.getParent().intValue());
        assertEquals("Text", actualFeatureDto.getText());
    }
}

