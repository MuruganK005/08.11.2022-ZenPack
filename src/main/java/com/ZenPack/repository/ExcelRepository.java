package com.ZenPack.repository;

import com.ZenPack.model.ZenPack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelRepository extends JpaRepository<ZenPack,Integer>, JpaSpecificationExecutor<ZenPack> {

}
