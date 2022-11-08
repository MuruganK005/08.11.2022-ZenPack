package com.ZenPack.service.Services;

import com.ZenPack.Dto.FilterNewDTO.SpecificationResponse;
import com.ZenPack.Dto.ZenPackFilterDTO;
import com.ZenPack.Dto.filterRequestDTO.SearchFilterDto;
import com.ZenPack.model.Report;

import java.util.List;

public interface ReportService {
    List<Report> getReportList(ZenPackFilterDTO zenpackFilterDTO);

    SpecificationResponse getReports(SearchFilterDto filterDto);
}
