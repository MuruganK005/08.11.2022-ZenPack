package com.ZenPack.Dto.FilterNewDTO;


import com.ZenPack.Dto.filterRequestDTO.ColumnVO;
import com.ZenPack.Dto.filterRequestDTO.SearchFilterDto;

import java.util.List;
import java.util.Map;

public class FilterResponseBuilder {

    public static SpecificationResponse createResponse(SearchFilterDto request,
                                                       List<Map<String, Object>> rows){
        int currentLastRow = request.getStartRow() + rows.size();
        int lastRow = currentLastRow <= request.getEndRow() ? currentLastRow : -1;

        List<ColumnVO> valueColumns = request.getValueCols();

        return new SpecificationResponse(rows,lastRow);

    }
}
