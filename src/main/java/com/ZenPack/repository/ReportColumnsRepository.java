package com.ZenPack.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ZenPack.model.ReportColumns;

public interface ReportColumnsRepository extends JpaRepository<ReportColumns, Long>, JpaSpecificationExecutor<ReportColumns>{
	
}
