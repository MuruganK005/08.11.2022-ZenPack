package com.ZenPack.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ZenPack.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long>, JpaSpecificationExecutor<Report>{

}
