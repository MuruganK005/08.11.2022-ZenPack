package com.ZenPack.controller;


import com.ZenPack.Dto.FilterNewDTO.SpecificationResponse;
import com.ZenPack.Dto.ZenPackFilterDTO;
import com.ZenPack.Dto.SearchFilterDto;
import com.ZenPack.Dto.ZenPackReportDto;
import com.ZenPack.model.Report;
import com.ZenPack.service.Impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ReportController {
    @Autowired
    private ReportServiceImpl service;

    @PostMapping("/report_list")
    public List<Report> getAllReportsWithFilters(@RequestBody ZenPackFilterDTO zenpackFilterDTO){
        return service.getReportList(zenpackFilterDTO);
    }
    @PostMapping("/report_list_get")
    public SpecificationResponse getAllReport(@RequestBody SearchFilterDto filterDto){
        return service.getReports(filterDto);
    }

    @PostMapping("/createZenPackReport")
    public ResponseEntity<ZenPackReportDto> createZenPackReport(@RequestBody ZenPackReportDto zenPackReportDto){
        return service.save(zenPackReportDto);
    }

}
