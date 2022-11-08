package com.ZenPack.repository;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.model.ZenPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeaturedListRepository extends JpaRepository<FeaturedList, Integer> {
    @Query("SELECT p FROM FeaturedList p WHERE p.text LIKE %:keyword%" )
    List<FeaturedList> findByKeyword(String keyword);

}
