package com.ZenPack.service.Impl;

import com.ZenPack.Dto.FilterDTO;
import com.ZenPack.Dto.FilterNewDTO.SpecificationResponse;
import com.ZenPack.Dto.ZenPackFilterDTO;
import com.ZenPack.Dto.SearchFilterDto;
import com.ZenPack.Dto.ZenPackReportDto;
import com.ZenPack.Specification.ReportNewSpecification;
import com.ZenPack.model.Report;
import com.ZenPack.model.ZenPackReport;
import com.ZenPack.repository.ReportRepository;
import com.ZenPack.repository.ZenPackReportRepository;
import com.ZenPack.service.Services.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ZenPackReportRepository zenPackReportRepository;

    @Autowired
    private ReportNewSpecification specification;

    @Override
    public List<Report> getReportList(ZenPackFilterDTO zenpackFilterDTO) {

        ArrayList<Specification<Report>> zenpackSpecifications = new ArrayList<Specification<Report>>();
        for (FilterDTO zenpackDTO : zenpackFilterDTO.getFilterDTOList()) {
            zenpackSpecifications.add(specification.getReports(zenpackDTO));
        }
        if (zenpackSpecifications.isEmpty()) {
            return null;
        }

        Specification<Report> spec = zenpackSpecifications.get(0);
        if (zenpackSpecifications.size() > 1) {
            for (int i = 0; i<zenpackSpecifications.size(); i++) {
                spec = spec.and(zenpackSpecifications.get(i));
            }
        }

        return getPagedPlays(spec, zenpackFilterDTO.getPage(), zenpackFilterDTO.getSize(), zenpackFilterDTO).getContent();
    }

    @Override
    public SpecificationResponse getReports(SearchFilterDto filterDto) {
        return null;
    }

    public Page<Report> getPagedPlays(Specification<Report> spec, Integer page, Integer size, ZenPackFilterDTO zenpackFilterDTO) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, zenpackFilterDTO.getField());
        if (zenpackFilterDTO.getSortType().equals("desc")) {
            pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, zenpackFilterDTO.getField());
        }
        return reportRepository.findAll(spec, pageRequest);}


    @Override
    public ResponseEntity<ZenPackReportDto> save(ZenPackReportDto zenPackReportDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPackReport zenPackReport =mapper.map(zenPackReportDto,ZenPackReport.class);
        zenPackReport.setAnalytics(zenPackReportDto.isAnalytics());
        zenPackReport.setZenPackReportId(zenPackReportDto.getZenpackReportId());
        zenPackReport.setQuickAccess(zenPackReportDto.isQuickAccess());
        zenPackReport.setDashboard(zenPackReportDto.isDashBoard());
        zenPackReport.setAddToFavorite(zenPackReportDto.isAnalytics());
        zenPackReport.setReports(zenPackReportDto.getReports());
        zenPackReport.setZenPackReportId(zenPackReportDto.getZenpackReportId());
        zenPackReportRepository.save(zenPackReport);
        zenPackReportDto.setZenpackReportId(zenPackReport.getZenPackReportId());
        zenPackReportDto.setAnalytics(zenPackReport.isAnalytics());
        zenPackReportDto.setQuickAccess(zenPackReport.isQuickAccess());
        zenPackReportDto.setDashBoard(zenPackReport.isDashboard());
        zenPackReportDto.setAddToFavorite(zenPackReport.isAddToFavorite());
        zenPackReportDto.setFavoriteViewName(zenPackReport.getFavoriteViewName());
        return new ResponseEntity<>(zenPackReportDto, HttpStatus.CREATED);
    }
}
    
