package com.ZenPack.Dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SearchFilterDtoTest {
    @Test
    void testConstructor() {
        SearchFilterDto actualSearchFilterDto = new SearchFilterDto();
        actualSearchFilterDto.setEndRow(3);
        HashMap<String, Map<String, String>> stringMapMap = new HashMap<>();
        actualSearchFilterDto.setFilterModel(stringMapMap);
        ArrayList<String> stringList = new ArrayList<>();
        actualSearchFilterDto.setGroupKeys(stringList);
        ArrayList<String> stringList1 = new ArrayList<>();
        actualSearchFilterDto.setPivotCols(stringList1);
        actualSearchFilterDto.setPivotMode(true);
        ArrayList<String> stringList2 = new ArrayList<>();
        actualSearchFilterDto.setRowGroupCols(stringList2);
        ArrayList<SortSpecificationDto> sortSpecificationDtoList = new ArrayList<>();
        actualSearchFilterDto.setSortModel(sortSpecificationDtoList);
        actualSearchFilterDto.setStartRow(1);
        ArrayList<String> stringList3 = new ArrayList<>();
        actualSearchFilterDto.setValueCols(stringList3);
        assertEquals(3, actualSearchFilterDto.getEndRow().intValue());
        assertSame(stringMapMap, actualSearchFilterDto.getFilterModel());
        assertSame(stringList, actualSearchFilterDto.getGroupKeys());
        assertSame(stringList1, actualSearchFilterDto.getPivotCols());
        assertSame(stringList2, actualSearchFilterDto.getRowGroupCols());
        assertSame(sortSpecificationDtoList, actualSearchFilterDto.getSortModel());
        assertEquals(1, actualSearchFilterDto.getStartRow().intValue());
        assertSame(stringList3, actualSearchFilterDto.getValueCols());
        assertTrue(actualSearchFilterDto.isPivotMode());
    }
}

