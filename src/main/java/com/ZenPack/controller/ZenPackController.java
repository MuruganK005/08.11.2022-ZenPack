package com.ZenPack.controller;

import java.util.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.ZenPack.Dto.*;
import com.ZenPack.Dto.FilterNewDTO.SpecificationResponse;
import com.ZenPack.Dto.SearchFilterDto;
import com.ZenPack.Specification.*;
import com.ZenPack.excel.ExcelRequest;
import com.ZenPack.excel.ZenPackExcelExporter;
import com.ZenPack.exception.ZenPackException;
import com.ZenPack.repository.ExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ZenPack.model.Report;
import com.ZenPack.model.ReportColumns;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import com.ZenPack.service.Services.SpecificationService;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public class ZenPackController {

    @Autowired
    private ZenPackServiceImpl service;

    @Autowired
    private ZenPackRepository zenPackRepository;

    @Autowired
    private SpecificationService specificationService;

    @Autowired
    private ExcelRepository excelRepository;


    @PostMapping("/save")
    public ResponseEntity<ZenPack> saveZenPack(@RequestBody ZenPack zenPack) {
        return service.saveZenPack(zenPack);
    }

    @PostMapping("/create")
    public ResponseEntity<ZenPackDto> createZenPack(@RequestBody ZenPackDto zenPackDto) throws ZenPackException, ZenPackException {
        return service.createZenPack(zenPackDto);
    }
    @GetMapping(value = "get_all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ZenPackDto> getAllZenPack() throws JsonProcessingException {
        return service.getAllZenPack();
    }
    @DeleteMapping("/delete/{zenPackId}")
    public String deleteByZenPackId(@PathVariable Long zenPackId){
        return service.deleteByzenPackId(zenPackId);
    }
    @GetMapping(value = "/getByZenPackId/{zenPackId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZenPackDto> getByZenPackId(@PathVariable Long zenPackId){
        com.ZenPack.Dto.ZenPackDto result = service.getByZenPackId(zenPackId);
        if(result == null) {
        	return ResponseEntity.notFound().eTag(zenPackId + " not found").build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<ZenPack>> getBySpecification(@RequestBody SpecificationDto specificationDto){
        ResponseEntity<Page<ZenPack>> response = specificationService.getBySpecification(specificationDto);
        return new ResponseEntity<>(response.getBody(),response.getStatusCode());
    }
    @PostMapping("/searchZenPack")
    public Page<ZenPack> searchZenPack(@RequestBody SearchFilterDto request) {
        return service.searchZenPack(getSearchRequest(request));
    }
    @GetMapping(value = "checkZenPackName",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkZenPackNameExists(@RequestParam String name) throws JsonProcessingException {
        return service.checkZenPackName(name);
    }
    private SearchRequest getSearchRequest(SearchFilterDto searchFilter) {
    	SearchRequest request = new SearchRequest();
    	Map<String, Map<String,String>> filter = searchFilter.getFilterModel();
    	List<SortSpecificationDto> sortModel = searchFilter.getSortModel();
    	List<FilterRequest> filterRequest = new ArrayList<>();
    	List<SortRequest> sortRequest = new ArrayList<>();
    	Iterator<String> keyItr = filter.keySet().iterator();
    	while(keyItr.hasNext()) {
    		String filterName = keyItr.next();
    		Map<String,String> filterValues = filter.get(filterName);
    		FilterRequest filterReq = new FilterRequest();
    		filterReq.setFieldType(FieldType.valueOf(filterValues.get("filterType").toUpperCase()));
        	filterReq.setOperator(ZenpackOperator.valueOf(filterValues.get("type").toUpperCase()));
        	filterReq.setKey(filterName);
        	filterReq.setValue(filterValues.get("filter"));
        	filterRequest.add(filterReq);
    	}
    	for(SortSpecificationDto sort: sortModel) {
    		SortRequest req = new SortRequest();
    		req.setDirection(SortDirection.valueOf(sort.getSort().toUpperCase()));
    		req.setKey(sort.getColId());
    		sortRequest.add(req);
    	}
    	request.setFilters(filterRequest);
    	request.setSorts(sortRequest);
    	return request;
    }
    @GetMapping("/export/excel")//new one
    public void exportToExcel(@RequestBody SearchFilterDto excelRequest, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename=ZenPack_info"+currentDateTime+".xlsx";
        response.setContentType(excelRequest.toString());
        response.setHeader(headerKey, headervalue);
        PageRequest pageRequest = PageRequest.of(excelRequest.getStartRow(),excelRequest.getEndRow());
        Page<ZenPack> zenPacks=null;
        if (excelRequest.getFilterModel() != null){
           SearchRequest searchRequest= getSearchRequest(excelRequest);
            SearchSpecification<ZenPack> specification = new SearchSpecification<>(searchRequest);
            Pageable pageable = SearchSpecification.getPageable(excelRequest.getStartRow(), excelRequest.getEndRow());
            zenPacks=excelRepository.findAll(specification, pageable);
        }
        Page<ZenPack> listStudent = excelRepository.findAll(pageRequest);
        if (zenPacks !=null){
            ZenPackExcelExporter exp = new ZenPackExcelExporter(zenPacks.getContent());
            exp.export(excelRequest, response);
        }else {
            ZenPackExcelExporter exp = new ZenPackExcelExporter(listStudent.getContent());
            exp.export(excelRequest, response);
        }
    }
    @DeleteMapping("/set_in_active/{zenPackId}")
    public String setZenPackActiveOrInActive(@PathVariable Long zenPackId){
        return  service.setActiveOrInActive(zenPackId);
    }

    @PutMapping("/setActiveOrInActive")
    public String setActiveOrInActive(@RequestParam Boolean inActive,@RequestParam Long zenPackId){
        return service.setActiveOrInActive(inActive,zenPackId);
    }

    @PostMapping("/searchReport")
    public ResponseEntity<Page<Report>> getReportBySpecification(@RequestBody SpecificationDto specificationDto){
        ResponseEntity<Page<Report>> response = specificationService.getReportBySpecification(specificationDto);
        return new ResponseEntity<>(response.getBody(),response.getStatusCode());
    }

    @PostMapping("/searchReportByFilter")
    public Page<Report> searchReport(@RequestBody SearchFilterDto request) {
    	
        return service.searchReport(getSearchRequest(request));
    }
    @PostMapping("/searchReportColumns")
    public ResponseEntity<Page<ReportColumns>> getReportColumnsBySpecification(@RequestBody SpecificationDto specificationDto){
        ResponseEntity<Page<ReportColumns>> response = null;
        return new ResponseEntity<>(response.getBody(),response.getStatusCode());
    }

    @PostMapping("/searchReportColumnsByFilter")
    public Page<ReportColumns> searchReportColumns(@RequestBody SearchFilterDto request) {
    	
        return service.searchReportColumns(getSearchRequest(request));
    }

    @PostMapping("/search_zen_pack_list")
    public SpecificationResponse getList(@RequestBody SearchFilterDto zenpackFilterDTO) {
        return service.getList(zenpackFilterDTO);
    }

}
