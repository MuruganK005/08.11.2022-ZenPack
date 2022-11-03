package com.ZenPack.repository;

import java.util.Optional;

import com.ZenPack.Specification.ReportColumnsSpecification;
import com.ZenPack.Specification.ReportSpecification;
import com.ZenPack.model.Report;
import com.ZenPack.model.ReportColumns;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ZenPack.model.ZenPack;

@Repository
public interface ZenPackRepository extends JpaRepository<ZenPack,Integer>, JpaSpecificationExecutor<ZenPack> {

    void deleteByZenPackId(Long zenPackId);

    Optional<ZenPack> findByZenPackId(Long zenPackId);
    
    Optional<ZenPack> findByName(String name);

    Page<Report> findAll(ReportSpecification spec, Pageable unpaged);


    Page<ReportColumns> findAll(ReportColumnsSpecification spec, Pageable unpaged);
}