package com.ZenPack.Dto.FilterNewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
public class SpecificationResponse {
    private List<Map<String, Object>> data;
    private int lastRow;
}
