package com.ZenPack.Specification;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.junit.jupiter.api.Assertions.*;

class SearchSpecificationTest {

    @Test
    @Disabled("TODO: Complete this test")
    void testToPredicate() {
        SearchSpecification<Object> searchSpecification = null;
        Root<Object> root = null;
        CriteriaQuery<?> query = null;
        CriteriaBuilder cb = null;

        Predicate actualToPredicateResult = searchSpecification.toPredicate(root, query, cb);

    }
    @Test
    void testGetPageable() {
        Pageable actualPageable = SearchSpecification.getPageable(1, 3);
        assertTrue(actualPageable.hasPrevious());
        assertEquals(3, actualPageable.getPageSize());
        assertTrue(actualPageable.getSort().toList().isEmpty());
    }
    @Test
    void testGetPageable2() {
        Pageable actualPageable = SearchSpecification.getPageable(null, null);
        assertFalse(actualPageable.hasPrevious());
        assertEquals(100, actualPageable.getPageSize());
        assertTrue(actualPageable.getSort().toList().isEmpty());
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPageable3() {
        SearchSpecification.getPageable(-1, 3);
    }
    @Test
    void testGetPageable4() {
        Pageable actualPageable = SearchSpecification.getPageable(1, 0);
        assertTrue(actualPageable.hasPrevious());
        assertEquals(100, actualPageable.getPageSize());
        assertTrue(actualPageable.getSort().toList().isEmpty());
    }
}


