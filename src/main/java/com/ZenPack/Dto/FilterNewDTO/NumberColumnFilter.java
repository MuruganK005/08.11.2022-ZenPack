package com.ZenPack.Dto.FilterNewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NumberColumnFilter extends ColumnFilter{
    private String type;
    private Integer filter;
    private Integer filterTo;

    public String getFilterType() {
        return filterType;
    }
}
