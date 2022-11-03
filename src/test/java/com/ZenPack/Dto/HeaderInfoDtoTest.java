package com.ZenPack.Dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeaderInfoDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new HeaderInfoDto()).canEqual("Other"));
    }
    @Test
    void testCanEqual2() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertTrue(headerInfoDto.canEqual(headerInfoDto1));
    }
    @Test
    void testConstructor() {
        HeaderInfoDto actualHeaderInfoDto = new HeaderInfoDto();
        actualHeaderInfoDto.setActualName("Actual Name");
        actualHeaderInfoDto.setDataType("Data Type");
        actualHeaderInfoDto.setDisplayName("Display Name");
        actualHeaderInfoDto.setHide(true);
        String actualToStringResult = actualHeaderInfoDto.toString();
        assertEquals("Actual Name", actualHeaderInfoDto.getActualName());
        assertEquals("Data Type", actualHeaderInfoDto.getDataType());
        assertEquals("Display Name", actualHeaderInfoDto.getDisplayName());
        assertTrue(actualHeaderInfoDto.isHide());
        assertEquals("HeaderInfoDto(actualName=Actual Name, hide=true, displayName=Display Name, dataType=Data Type)",
                actualToStringResult);
    }
    @Test
    void testEquals() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);
        assertNotEquals(headerInfoDto, null);
    }
    @Test
    void testEquals2() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);
        assertNotEquals(headerInfoDto, "Different type to HeaderInfoDto");
    }
    @Test
    void testEquals3() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);
        assertEquals(headerInfoDto, headerInfoDto);
        int expectedHashCodeResult = headerInfoDto.hashCode();
        assertEquals(expectedHashCodeResult, headerInfoDto.hashCode());
    }
    @Test
    void testEquals4() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertEquals(headerInfoDto, headerInfoDto1);
        int expectedHashCodeResult = headerInfoDto.hashCode();
        assertEquals(expectedHashCodeResult, headerInfoDto1.hashCode());
    }
    @Test
    void testEquals5() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Display Name");
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertNotEquals(headerInfoDto, headerInfoDto1);
    }
    @Test
    void testEquals6() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName(null);
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertNotEquals(headerInfoDto, headerInfoDto1);
    }
    @Test
    void testEquals7() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType("Actual Name");
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertNotEquals(headerInfoDto, headerInfoDto1);
    }
    @Test
    void testEquals8() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType(null);
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertNotEquals(headerInfoDto, headerInfoDto1);
    }
    @Test
    void testEquals9() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName("Actual Name");
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertNotEquals(headerInfoDto, headerInfoDto1);
    }
    @Test
    void testEquals10() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName(null);
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertNotEquals(headerInfoDto, headerInfoDto1);
    }
    @Test
    void testEquals11() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(false);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertNotEquals(headerInfoDto, headerInfoDto1);
    }
    @Test
    void testEquals12() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName(null);
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName(null);
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertEquals(headerInfoDto, headerInfoDto1);
        int expectedHashCodeResult = headerInfoDto.hashCode();
        assertEquals(expectedHashCodeResult, headerInfoDto1.hashCode());
    }
    @Test
    void testEquals13() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType(null);
        headerInfoDto.setDisplayName("Display Name");
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType(null);
        headerInfoDto1.setDisplayName("Display Name");
        headerInfoDto1.setHide(true);
        assertEquals(headerInfoDto, headerInfoDto1);
        int expectedHashCodeResult = headerInfoDto.hashCode();
        assertEquals(expectedHashCodeResult, headerInfoDto1.hashCode());
    }
    @Test
    void testEquals14() {
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Actual Name");
        headerInfoDto.setDataType("Data Type");
        headerInfoDto.setDisplayName(null);
        headerInfoDto.setHide(true);

        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Actual Name");
        headerInfoDto1.setDataType("Data Type");
        headerInfoDto1.setDisplayName(null);
        headerInfoDto1.setHide(true);
        assertEquals(headerInfoDto, headerInfoDto1);
        int expectedHashCodeResult = headerInfoDto.hashCode();
        assertEquals(expectedHashCodeResult, headerInfoDto1.hashCode());
    }
}

