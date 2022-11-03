package com.ZenPack.controller;

import com.ZenPack.Dto.SearchFilterDto;
import com.ZenPack.Dto.SpecificationDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.Specification.SearchRequest;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ExcelRepository;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import com.ZenPack.service.Services.SpecificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ZenPackController.class})
@ExtendWith(SpringExtension.class)
class ZenPackControllerTest {
    @MockBean
    private ExcelRepository excelRepository;

    @MockBean
    private SpecificationService specificationService;

    @Autowired
    private ZenPackController zenPackController;

    @MockBean
    private ZenPackRepository zenPackRepository;

    @MockBean
    private ZenPackServiceImpl zenPackServiceImpl;

    @Test
    void testSaveZenPack() throws Exception {
        when(zenPackServiceImpl.saveZenPack((ZenPack) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        ZenPack zenPack = new ZenPack();
        zenPack.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack.setCreatedDate("2020-03-01");
        zenPack.setFeatures(new ArrayList<>());
        zenPack.setInActive(true);
        zenPack.setMenus(new ArrayList<>());
        zenPack.setName("Name");
        zenPack.setUpdatedBy("2020-03-01");
        zenPack.setUpdatedTime("2020-03-01");
        zenPack.setZenPackId(123L);
        String content = (new ObjectMapper()).writeValueAsString(zenPack);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
    @Test
    void testCheckZenPackNameExists() throws Exception {
        when(zenPackServiceImpl.checkZenPackName((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/checkZenPackName")
                .param("name", "foo");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }
    @Test
    void testCreateZenPack() throws Exception {
        when(zenPackServiceImpl.createZenPack((ZenPackDto) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        ZenPackDto zenPackDto = new ZenPackDto();
        zenPackDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPackDto.setCreatedDate("2020-03-01");
        zenPackDto.setFeatures(new ArrayList<>());
        zenPackDto.setMenus(new ArrayList<>());
        zenPackDto.setName("Name");
        zenPackDto.setUpdatedBy("2020-03-01");
        zenPackDto.setUpdatedTime("2020-03-01");
        zenPackDto.setZenPackId(123L);
        String content = (new ObjectMapper()).writeValueAsString(zenPackDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
    @Test
    void testGetAllZenPack() throws Exception {
        when(zenPackServiceImpl.getAllZenPack()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/get_all");
        MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    @Test
    void testGetByZenPackId() throws Exception {
        ZenPackDto zenPackDto = new ZenPackDto();
        zenPackDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPackDto.setCreatedDate("2020-03-01");
        zenPackDto.setFeatures(new ArrayList<>());
        zenPackDto.setMenus(new ArrayList<>());
        zenPackDto.setName("Name");
        zenPackDto.setUpdatedBy("2020-03-01");
        zenPackDto.setUpdatedTime("2020-03-01");
        zenPackDto.setZenPackId(123L);
        zenPackDto.setInActive(false);
        when(zenPackServiceImpl.getByZenPackId((Long) any())).thenReturn(zenPackDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getByZenPackId/{zenPackId}",
                123L);
        MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"zenPackId\":123,\"name\":\"Name\",\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"createdDate\":\"2020-03-01\","
                                        + "\"updatedBy\":\"2020-03-01\",\"updatedTime\":\"2020-03-01\",\"menus\":[],\"features\":[],\"inActive\":false}"));
    }
    @Test
    void testGetBySpecification() throws Exception {
        when(specificationService.getBySpecification((SpecificationDto) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        SpecificationDto specificationDto = new SpecificationDto();
        specificationDto.setKey("Key");
        specificationDto.setOperation("Operation");
        specificationDto.setVaLues(new ArrayList<>());
        specificationDto.setValue("42");
        String content = (new ObjectMapper()).writeValueAsString(specificationDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
    @Test
    void testSearchZenPack() throws Exception {
        when(zenPackServiceImpl.searchZenPack((SearchRequest) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        SearchFilterDto searchFilterDto = new SearchFilterDto();
        searchFilterDto.setEndRow(3);
        searchFilterDto.setFilterModel(new HashMap<>());
        searchFilterDto.setGroupKeys(new ArrayList<>());
        searchFilterDto.setPivotCols(new ArrayList<>());
        searchFilterDto.setPivotMode(true);
        searchFilterDto.setRowGroupCols(new ArrayList<>());
        searchFilterDto.setSortModel(new ArrayList<>());
        searchFilterDto.setStartRow(1);
        searchFilterDto.setValueCols(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(searchFilterDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/searchZenPack")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testDeleteByZenPackId() throws Exception {
        when(zenPackServiceImpl.deleteByzenPackId((Long) any())).thenReturn("42");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/delete/{zenPackId}", 123L);
        MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42"));
    }
    @Test
    void testDeleteByZenPackId2() throws Exception {
        when(zenPackServiceImpl.deleteByzenPackId((Long) any())).thenReturn("42");
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/delete/{zenPackId}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42"));
    }
    @Test
    void testSetZenPackActiveOrInActive() throws Exception {
        when(zenPackServiceImpl.setActiveOrInActive((Long) any())).thenReturn("Active Or In Active");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/set_in_active/{zenPackId}",
                123L);
        MockMvcBuilders.standaloneSetup(zenPackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Active Or In Active"));
    }
}

