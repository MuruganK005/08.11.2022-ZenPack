package com.ZenPack.service.Services;

import com.ZenPack.Dto.SpecificationDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.Specification.ReportColumnsSpecification;
import com.ZenPack.Specification.ReportSpecification;
import com.ZenPack.Specification.ZenPackSpecification;
import com.ZenPack.model.Report;
import com.ZenPack.model.ReportColumns;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ReportRepository;
import com.ZenPack.repository.ZenPackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecificationService {
    @Autowired
    private ZenPackRepository repository;
    
    @Autowired ReportRepository reportRepository;

    public ResponseEntity<Page<ZenPack>> getBySpecification(SpecificationDto specificationDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Sort.Direction sort = Sort.Direction.DESC;
        ZenPackSpecification spec = new ZenPackSpecification(specificationDto);
        Page<ZenPack> zenPacks=repository.findAll(spec, Pageable.unpaged());
        return new ResponseEntity<>(zenPacks,HttpStatus.OK);
    }

    public ResponseEntity<Page<Report>> getReportBySpecification(SpecificationDto specificationDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Sort.Direction sort = Sort.Direction.ASC;
        ReportSpecification spec = new ReportSpecification(specificationDto);
        Page<Report> reports=reportRepository.findAll(spec, Pageable.unpaged());
        return new ResponseEntity<>(reports,HttpStatus.OK);
    }

	/*
	 * public ResponseEntity<Page<ReportColumns>>
	 * getReportColumnsBySpecification(SpecificationDto specificationDto) {
	 * ModelMapper modelMapper = new ModelMapper();
	 * modelMapper.getConfiguration().setAmbiguityIgnored(true); Sort.Direction sort
	 * = Sort.Direction.ASC; ReportColumnsSpecification spec = new
	 * ReportColumnsSpecification(specificationDto); Page<ReportColumns>
	 * reportColumns=repository.findAll(spec, Pageable.unpaged()); return new
	 * ResponseEntity<>(reportColumns,HttpStatus.OK); }
	 */}
