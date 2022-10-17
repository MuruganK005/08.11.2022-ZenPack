package com.ZenPack.service.Impl;

import com.ZenPack.Dto.MenuDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.StringReader;
import java.util.*;

@Service
@Slf4j
public class ZenPackServiceImpl implements ZenPackService {


    @Autowired
    private ZenPackRepository repository;

    @Autowired
    private EntityManager manager;

    private static final Logger logger=LoggerFactory.getLogger(ZenPackServiceImpl.class);

    @Override
    public ResponseEntity<ZenPack> saveZenPack(ZenPack zenPack) {
        repository.save(zenPack);
        logger.info("Zen-Pack Saved Successfully");
        return new ResponseEntity<>(zenPack, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ZenPackDto> createZenPack(ZenPackDto zenPackDto)  {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPack zenPack=mapper.map(zenPackDto,ZenPack.class);
        String menuJson = new Gson().toJson(zenPackDto.getMenus());
        zenPack.setJsonData(menuJson);
       /* zenPack.setCreatedDate(new Date());*/
        zenPack.setCreatedDate(new Date());
        zenPack.setUpdatedTime(new Date());
        repository.save(zenPack);
        zenPackDto.setZenPackId(zenPack.getZenPackId());
        /* zenPackDto.setName(zenPack.getName());*/
        zenPackDto.setCreatedDate(zenPack.getCreatedDate());
        zenPackDto.setUpdatedTime(zenPack.getUpdatedTime());
        return new ResponseEntity<>(zenPackDto,HttpStatus.OK);
    }


/*         ZenPack zenPack=new ZenPack();
    ObjectMapper mapper=new ObjectMapper();
    String gson= new Gson().toJson(createDto);
    zenPack.setJsonData(mapper.writeValueAsString(createDto));
    repository.save(zenPack);*/
   /* ResponseDto responseDto=new ResponseDto();
    responseDto.setResponseMessage("ZenPack Created Successfully");
    responseDto.setResponseCode("Ok");
    responseDto.setStatusCode(200);
    responseDto.setResponseDescription("ZenPack Created Successfully");
    responseDto.setData(null);
    Gson gson1=new Gson();
    ZenPackDto zenPackDto=gson1.fromJson(gson,ZenPackDto.class);
    responseDto.setJdata(zenPackDto);*/
  /*  return responseDto;
        return new ResponseEntity<>(zenPackDto1, HttpStatus.ACCEPTED);
    }*/
    @Override
    public List<ZenPackDto> getAllZenPack() throws JsonProcessingException {
        List<ZenPack> zenPacks = repository.findAll();
        List<ZenPackDto> zenPackDtos = new ArrayList<>();
        for (ZenPack zenpack : zenPacks) {
            Gson gson = new Gson();
            ZenPackDto zenPackDto = new ZenPackDto();
            zenPackDto.setZenPackId(zenpack.getZenPackId());
            zenPackDto.setName(zenpack.getName());
            zenPackDto.setZenPackId(zenPackDto.getZenPackId());
            JsonReader reader = new JsonReader(new StringReader(zenpack.getJsonData()));
            reader.setLenient(true);
            MenuDto[] userinfo1 = gson.fromJson(reader, MenuDto[].class);
            ArrayList<MenuDto> list = new ArrayList(Arrays.asList(userinfo1));
            zenPackDto.setMenus(list);
            zenPackDtos.add(zenPackDto);
        }
       return zenPackDtos;
    }

    @Override
    public String deleteByzenPackId(Long zenPackId) {
        repository.deleteByZenPackId(zenPackId);
        return  " Id "+zenPackId+" Deleted SuccessFully";
    }

    @Override
    public ZenPackDto getByZenPackId(Long zenPackId) {
        Optional<ZenPack> zenPack= repository.findByZenPackId(zenPackId);
        Gson gson=new Gson();
        ZenPackDto zenPackDto=new ZenPackDto();
        zenPackDto.setZenPackId(zenPack.get().getZenPackId());
        zenPackDto.setName(zenPack.get().getName());
        JsonReader reader = new JsonReader(new StringReader(zenPack.get().getJsonData()));
        reader.setLenient(true);
        MenuDto[] userinfo1 = gson.fromJson(reader, MenuDto[].class);
        ArrayList<MenuDto> list = new ArrayList(Arrays.asList(userinfo1));
        zenPackDto.setMenus(list);
        return zenPackDto;
    }
}