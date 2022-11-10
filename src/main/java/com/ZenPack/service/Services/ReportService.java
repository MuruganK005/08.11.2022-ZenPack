package com.ZenPack.service.Services;

import com.ZenPack.Dto.FilterNewDTO.SpecificationResponse;
import com.ZenPack.Dto.ZenPackFilterDTO;
import com.ZenPack.Dto.SearchFilterDto;
import com.ZenPack.Dto.ZenPackReportDto;
import com.ZenPack.model.Report;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportService {
    List<Report> getReportList(ZenPackFilterDTO zenpackFilterDTO);

    SpecificationResponse getReports(SearchFilterDto filterDto);

    ResponseEntity<ZenPackReportDto> save(ZenPackReportDto zenPackReportDto);
}
