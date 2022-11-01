package com.ZenPack.controller;

import com.ZenPack.Dto.FeatureDto;
import com.ZenPack.model.FeaturedList;
import com.ZenPack.service.Impl.FeaturedListServiceImpl;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class FeatureListController {

    @Autowired
    private FeaturedListServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create_features")
    @ResponseStatus(HttpStatus.CREATED)
    public FeaturedList create(@RequestBody FeaturedList featuredList){
        return service.save(featuredList);
    }


    @GetMapping("/get_features")
    @ResponseStatus(HttpStatus.OK)
    public List<FeaturedList> findAllFeatureList(){
        return service.findAllList();
    }

    @GetMapping("/search_by_name")
    @ResponseStatus(HttpStatus.OK)
    public List<FeaturedList> findByName(@RequestParam String keyword){
        return service.findByKeyword(keyword);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<FeaturedList> getList(@PathVariable int id){
        return service.getListById(id);
    }


    @DeleteMapping("/deleteList/{id}")
    public String delete(@PathVariable int id){
        return service.deleteList(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeaturedList> updateFeatures(@PathVariable("id") int listId,
                                                   @RequestBody FeaturedList featuredList){
        return service.getListById(listId)
                .map(savedList -> {
                    savedList.setText(featuredList.getText());
                    savedList.setParent(featuredList.getParent());
                    savedList.setFeatureUrl(featuredList.getFeatureUrl());
                    savedList.setIcon(featuredList.getIcon());
                    savedList.setIsSettingMenu(featuredList.getIsSettingMenu());

                    FeaturedList updatedList = service.updatedList(savedList);
                    return new ResponseEntity<>(updatedList, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
