package com.ZenPack.Dto;

import java.util.List;
import java.util.Map;

import com.ZenPack.Dto.SortSpecificationDto;
import com.ZenPack.Dto.filterRequestDTO.ColumnVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilterDto {
	private Integer startRow;
	private Integer endRow;
	private List<String> rowGroupCols;
	private boolean pivotMode;
	private List<ColumnVO> pivotCols;
	private List<ColumnVO> valueCols;
	private List<ColumnVO> groupKeys;
	private Map<String, Map<String,String>> filterModel;
	private List<SortSpecificationDto> sortModel;
}
