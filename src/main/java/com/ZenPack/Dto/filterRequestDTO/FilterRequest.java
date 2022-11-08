package com.ZenPack.Dto.filterRequestDTO;

import com.ZenPack.Dto.FilterNewDTO.ColumnFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FilterRequest {
    private Map<String, ColumnFilter> filterModel;
}
