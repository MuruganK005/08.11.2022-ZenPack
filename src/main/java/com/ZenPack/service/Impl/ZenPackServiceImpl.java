package com.ZenPack.service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.ZenPack.exception.ZenPackException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.Specification.SearchRequest;
import com.ZenPack.Specification.SearchSpecification;
import com.ZenPack.model.ReportHeader;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ReportHeaderRepository;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Services.ZenPackService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ZenPackServiceImpl implements ZenPackService {

	@Autowired
	private ZenPackRepository repository;

	@Autowired
	private ReportHeaderRepository reportHeaderRepo;

	@Autowired
	private EntityManager manager;

	private static final Logger logger = LoggerFactory.getLogger(ZenPackServiceImpl.class);

	LocalDateTime myDateObj = LocalDateTime.now();
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	String formattedDate = myDateObj.format(myFormatObj);

	@Override
	public ResponseEntity<ZenPack> saveZenPack(ZenPack zenPack) {
		repository.save(zenPack);
		logger.info("Zen-Pack Saved Successfully");
		return new ResponseEntity<>(zenPack, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ZenPackDto> createZenPack(ZenPackDto zenPackDto) throws ZenPackException {
		Optional<ZenPack> zenPack1=repository.findByName(zenPackDto.getName());
		if (zenPack1.isPresent()&& zenPack1.get().getZenPackId() != zenPackDto.getZenPackId()) {
			throw new ZenPackException(HttpStatus.FOUND,"ZenPackName Already Exist");
		}
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		ZenPack zenPack = mapper.map(zenPackDto, ZenPack.class);
		zenPack.setCreatedDate(formattedDate);
		zenPack.setUpdatedTime(formattedDate);
		repository.save(zenPack);
		zenPackDto.setZenPackId(zenPack.getZenPackId());
		zenPackDto.setCreatedDate(zenPack.getCreatedDate());
		zenPackDto.setUpdatedTime(zenPack.getUpdatedTime());
		return new ResponseEntity<>(zenPackDto, HttpStatus.OK);
	}

	@Override
	public List<ZenPackDto> getAllZenPack() throws JsonProcessingException {
		List<ZenPack> zenPacks = repository.findAll();
		List<ZenPackDto> zenPackDtos = new ArrayList<>();
		for (ZenPack zenpack : zenPacks) {
			ModelMapper mapper=new ModelMapper();
			mapper.getConfiguration().setAmbiguityIgnored(true);
			ZenPackDto zenPackDto =mapper.map(zenpack, ZenPackDto.class);
			zenPackDto.setZenPackId(zenpack.getZenPackId());
			zenPackDto.setName(zenpack.getName());
			zenPackDto.setMenus(zenpack.getMenus());
			zenPackDto.setFeatures(zenpack.getFeatures());
			zenPackDtos.add(zenPackDto);
		}
		return zenPackDtos;
	}

	@Override
	public String deleteByzenPackId(Long zenPackId) {
		repository.deleteByZenPackId(zenPackId);
		return " Id " + zenPackId + " Deleted SuccessFully";
	}

	@Override
	public ZenPackDto getByZenPackId(Long zenPackId) {
		Optional<ZenPack> zenPack = repository.findByZenPackId(zenPackId);
		if(zenPack != null && zenPack.isPresent()) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		ZenPackDto zenPackDto = mapper.map(zenPack, ZenPackDto.class);
		zenPackDto.setZenPackId(zenPack.get().getZenPackId());
		zenPackDto.setCreatedBy(zenPack.get().getCreatedBy());
		zenPackDto.setUpdatedBy(zenPack.get().getUpdatedBy());
		zenPackDto.setCreatedDate(zenPack.get().getCreatedDate());
		zenPackDto.setUpdatedTime(zenPack.get().getUpdatedTime());
		zenPackDto.setName(zenPack.get().getName());
		zenPackDto.setMenus(zenPack.get().getMenus());
		zenPackDto.setFeatures(zenPack.get().getFeatures());
		return zenPackDto;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean checkZenPackName(String name) {
		Optional<ZenPack> zenPack = repository.findByName(name);
		if(zenPack.isPresent()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<ZenPack> searchZenPack(SearchRequest request) {
		SearchSpecification<ZenPack> specification = new SearchSpecification<>(request);
		Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
		return repository.findAll(specification, pageable);
	}

	@Override
	public String setActiveOrInActive(Long zenPackId) {
		Optional<ZenPack> entity = repository.findByZenPackId(zenPackId);
		if (entity.isPresent()) {
			entity.get().setInActive(true);
			repository.save(entity.get());
		}
		return "ZenPack "+zenPackId+" Set InActive Successful";
	}

	public ReportHeader createReportHeader(final ReportHeader reportHeader) {
		return this.reportHeaderRepo.save(reportHeader);
	}

	public List<ReportHeader> getAllReportHeader() {
		return this.reportHeaderRepo.findAll();
	}

	public String deleteReportHeaderById(Long reportId) {
		this.reportHeaderRepo.deleteById(reportId);
		return "Deleted Successfully";
	}

	public ReportHeader getReportHeaderById(Long reportId) {
		Optional<ReportHeader> reportHeader = this.reportHeaderRepo.findById(reportId);
		if (reportHeader != null && reportHeader.isPresent()) {
			return reportHeader.get();
		}
		return null;
	}

	public ReportHeader getReportHeaderByName(String reportName) {
		Optional<ReportHeader> reportHeader = this.reportHeaderRepo.findByReportName(reportName);
		if (reportHeader != null && reportHeader.isPresent()) {
			return reportHeader.get();
		}
		return null;
	}

	public String setActiveOrInActive(Boolean inActive, Long zenPackId) {
		Optional<ZenPack> optionalZenPack = repository.findByZenPackId(zenPackId);
		if(optionalZenPack.isPresent()){
			optionalZenPack.get().setInActive(inActive);
			repository.save(optionalZenPack.get());
		}
		return "ZenPack id "+ zenPackId + " has Successfully set to " +inActive ;
	}
}
