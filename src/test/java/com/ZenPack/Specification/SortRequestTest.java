package com.ZenPack.Specification;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortRequestTest {
    @Test
    void testBuilder() {
        SortRequest.builder();
    }

    @Test
    void testCanEqual() {
        assertFalse((new SortRequest("Key", SortDirection.ASC)).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        SortRequest sortRequest = new SortRequest("Key", SortDirection.ASC);
        assertTrue(sortRequest.canEqual(new SortRequest("Key", SortDirection.ASC)));
    }

    @Test
    void testConstructor() {
        SortRequest actualSortRequest = new SortRequest();
        actualSortRequest.setDirection(SortDirection.ASC);
        actualSortRequest.setKey("Key");
        assertEquals(SortDirection.ASC, actualSortRequest.getDirection());
        assertEquals("Key", actualSortRequest.getKey());
    }

    @Test
    void testConstructor2() {
        SortRequest actualSortRequest = new SortRequest("Key", SortDirection.ASC);
        actualSortRequest.setDirection(SortDirection.ASC);
        actualSortRequest.setKey("Key");
        assertEquals(SortDirection.ASC, actualSortRequest.getDirection());
        assertEquals("Key", actualSortRequest.getKey());
    }

    @Test
    void testEquals() {
        assertNotEquals(new SortRequest("Key", SortDirection.ASC), null);
        assertNotEquals(new SortRequest("Key", SortDirection.ASC), "Different type to SortRequest");
    }

    @Test
    void testEquals2() {
        SortRequest sortRequest = new SortRequest("Key", SortDirection.ASC);
        assertEquals(sortRequest, sortRequest);
        int expectedHashCodeResult = sortRequest.hashCode();
        assertEquals(expectedHashCodeResult, sortRequest.hashCode());
    }

    @Test
    void testEquals3() {
        SortRequest sortRequest = new SortRequest("Key", SortDirection.ASC);
        SortRequest sortRequest1 = new SortRequest("Key", SortDirection.ASC);

        assertEquals(sortRequest, sortRequest1);
        int expectedHashCodeResult = sortRequest.hashCode();
        assertEquals(expectedHashCodeResult, sortRequest1.hashCode());
    }

    @Test
    void testEquals4() {
        SortRequest sortRequest = new SortRequest(null, SortDirection.ASC);
        assertNotEquals(sortRequest, new SortRequest("Key", SortDirection.ASC));
    }

    @Test
    void testEquals5() {
        SortRequest sortRequest = new SortRequest("com.ZenPack.Specification.SortRequest", SortDirection.ASC);
        assertNotEquals(sortRequest, new SortRequest("Key", SortDirection.ASC));
    }

    @Test
    void testEquals6() {
        SortRequest sortRequest = new SortRequest("Key", null);
        assertNotEquals(sortRequest, new SortRequest("Key", SortDirection.ASC));
    }

    @Test
    void testEquals7() {
        SortRequest sortRequest = new SortRequest("Key", SortDirection.DESC);
        assertNotEquals(sortRequest, new SortRequest("Key", SortDirection.ASC));
    }

    @Test
    void testEquals8() {
        SortRequest sortRequest = new SortRequest(null, SortDirection.ASC);
        SortRequest sortRequest1 = new SortRequest(null, SortDirection.ASC);

        assertEquals(sortRequest, sortRequest1);
        int expectedHashCodeResult = sortRequest.hashCode();
        assertEquals(expectedHashCodeResult, sortRequest1.hashCode());
    }

    @Test
    void testEquals9() {
        SortRequest sortRequest = new SortRequest("Key", null);
        SortRequest sortRequest1 = new SortRequest("Key", null);

        assertEquals(sortRequest, sortRequest1);
        int expectedHashCodeResult = sortRequest.hashCode();
        assertEquals(expectedHashCodeResult, sortRequest1.hashCode());
    }
}

