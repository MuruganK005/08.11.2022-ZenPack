package com.ZenPack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ZenPack.model.ZenPack;

public interface ZenPackRepository extends JpaRepository<ZenPack,Integer>, JpaSpecificationExecutor<ZenPack> {

    void deleteByZenPackId(Long zenPackId);

    Optional<ZenPack> findByZenPackId(Long zenPackId);
    
    Optional<ZenPack> findByName(String name);

   /* @Query("select p from Zenpack p where p.updatedTime >= :startDate and p.updatedTime <= :endDate")
    public Page<ZenPack> getZenpackByDateRange(String startDate, String endDate, Specification<ZenPack> spec, PageRequest pageRequest);
*/
}