package com.ZenPack.service.Impl;

import com.ZenPack.Dto.FilterDTO;
import com.ZenPack.Dto.FilterNewDTO.SpecificationResponse;
import com.ZenPack.Dto.ZenPackFilterDTO;
import com.ZenPack.Dto.filterRequestDTO.SearchFilterDto;
import com.ZenPack.Specification.ReportNewSpecification;
import com.ZenPack.model.Report;
import com.ZenPack.repository.ReportRepository;
import com.ZenPack.service.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

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
}
    
