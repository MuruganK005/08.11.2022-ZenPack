package com.ZenPack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ZenPack.model.ZenPack;

public interface ZenPackRepository extends JpaRepository<ZenPack,Integer>,JpaSpecificationExecutor<ZenPack> {

    void deleteByZenPackId(Long zenPackId);

    Optional<ZenPack> findByZenPackId(Long zenPackId);
    
    Optional<ZenPack> findByName(String name);

}