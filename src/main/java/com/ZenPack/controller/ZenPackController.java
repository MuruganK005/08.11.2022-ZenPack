package com.ZenPack.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ZenPack.excel.ZenPackExcelExporter;
import com.ZenPack.exception.ZenPackException;
import com.ZenPack.repository.ExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ZenPack.Dto.SearchFilterDto;
import com.ZenPack.Dto.SortSpecificationDto;
import com.ZenPack.Dto.SpecificationDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.Specification.FieldType;
import com.ZenPack.Specification.FilterRequest;
import com.ZenPack.Specification.SearchRequest;
import com.ZenPack.Specification.SortDirection;
import com.ZenPack.Specification.SortRequest;
import com.ZenPack.Specification.ZenpackOperator;
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

//    @PostMapping("/create")
//    public ResponseEntity<ZenPackDto> createZenPack(@RequestBody ZenPackDto zenPackDto){
//        if(zenPackDto == null || service.checkZenPackName(zenPackDto.getName())){
//    		return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
//    	}
//        return service.createZenPack(zenPackDto);
//    }

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
    public void exportToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename=ZenPack_info"+currentDateTime+".xlsx";

        response.setHeader(headerKey, headervalue);
        List<ZenPack> listStudent = excelRepository.findAll();
        ZenPackExcelExporter exp = new ZenPackExcelExporter(listStudent);
        exp.export(response);
    }

    @DeleteMapping("/set_in_active/{zenPackId}")
    public String setZenPackActiveOrInActive(@PathVariable Long zenPackId){
        return  service.setActiveOrInActive(zenPackId);
    }

    @PutMapping("/setActiveOrInActive")
    public String setActiveOrInActive(@RequestParam Boolean inActive,@RequestParam Long zenPackId){
        return service.setActiveOrInActive(inActive,zenPackId);
    }

}
