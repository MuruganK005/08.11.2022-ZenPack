package com.ZenPack.utils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class ZKModelTest {
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProperty() {
        ZKModel.getProperty("Property");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testContainsKey() {
        ZKModel.containsKey("Key");
    }

    @Test
    void testConstructor() {
        new ZKModel();
        assertNull(ZKModel.zkData);
    }
}

