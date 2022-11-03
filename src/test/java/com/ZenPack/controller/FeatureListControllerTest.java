package com.ZenPack.controller;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.service.Impl.FeaturedListServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {FeatureListController.class})
@ExtendWith(SpringExtension.class)
class FeatureListControllerTest {
    @Autowired
    private FeatureListController featureListController;

    @MockBean
    private FeaturedListServiceImpl featuredListServiceImpl;

    @MockBean
    private ModelMapper modelMapper;


    @Test
    void testCreate() throws Exception {
        FeaturedList featuredList = new FeaturedList();
        featuredList.setFeatureId("42");
        featuredList.setFeatureUrl("https://example.org/example");
        featuredList.setIcon("Icon");
        featuredList.setId(1);
        featuredList.setIsSettingMenu(true);
        featuredList.setParent(1);
        featuredList.setText("Text");
        when(featuredListServiceImpl.save((FeaturedList) any())).thenReturn(featuredList);

        FeaturedList featuredList1 = new FeaturedList();
        featuredList1.setFeatureId("42");
        featuredList1.setFeatureUrl("https://example.org/example");
        featuredList1.setIcon("Icon");
        featuredList1.setId(1);
        featuredList1.setIsSettingMenu(true);
        featuredList1.setParent(1);
        featuredList1.setText("Text");
        String content = (new ObjectMapper()).writeValueAsString(featuredList1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/create_features")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(featureListController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"parent\":1,\"featureId\":\"42\",\"text\":\"Text\",\"icon\":\"Icon\",\"isSettingMenu\":true,\"featureUrl\":"
                                        + "\"https://example.org/example\"}"));
    }


    @Test
    void testDelete() throws Exception {
        when(featuredListServiceImpl.deleteList(anyInt())).thenReturn("Delete List");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/deleteList/{id}", 1);
        MockMvcBuilders.standaloneSetup(featureListController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete List"));
    }

    @Test
    void testDelete2() throws Exception {
        when(featuredListServiceImpl.deleteList(anyInt())).thenReturn("Delete List");
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/deleteList/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(featureListController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete List"));
    }


    @Test
    void testFindAllFeatureList() throws Exception {
        when(featuredListServiceImpl.findAllList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/get_features");
        MockMvcBuilders.standaloneSetup(featureListController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFindAllFeatureList2() throws Exception {
        when(featuredListServiceImpl.findAllList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/get_features");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(featureListController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFindByName() throws Exception {
        when(featuredListServiceImpl.findByKeyword((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/search_by_name")
                .param("keyword", "foo");
        MockMvcBuilders.standaloneSetup(featureListController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetList() throws Exception {
        FeaturedList featuredList = new FeaturedList();
        featuredList.setFeatureId("42");
        featuredList.setFeatureUrl("https://example.org/example");
        featuredList.setIcon("Icon");
        featuredList.setId(1);
        featuredList.setIsSettingMenu(true);
        featuredList.setParent(1);
        featuredList.setText("Text");
        Optional<FeaturedList> ofResult = Optional.of(featuredList);
        when(featuredListServiceImpl.getListById(anyInt())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/{id}", 1);
        MockMvcBuilders.standaloneSetup(featureListController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"parent\":1,\"featureId\":\"42\",\"text\":\"Text\",\"icon\":\"Icon\",\"isSettingMenu\":true,\"featureUrl\":"
                                        + "\"https://example.org/example\"}"));
    }


    @Test
    void testUpdateEmployee() throws Exception {
        FeaturedList featuredList = new FeaturedList();
        featuredList.setFeatureId("42");
        featuredList.setFeatureUrl("https://example.org/example");
        featuredList.setIcon("Icon");
        featuredList.setId(1);
        featuredList.setIsSettingMenu(true);
        featuredList.setParent(1);
        featuredList.setText("Text");
        Optional<FeaturedList> ofResult = Optional.of(featuredList);

        FeaturedList featuredList1 = new FeaturedList();
        featuredList1.setFeatureId("42");
        featuredList1.setFeatureUrl("https://example.org/example");
        featuredList1.setIcon("Icon");
        featuredList1.setId(1);
        featuredList1.setIsSettingMenu(true);
        featuredList1.setParent(1);
        featuredList1.setText("Text");
        when(featuredListServiceImpl.updatedList((FeaturedList) any())).thenReturn(featuredList1);
        when(featuredListServiceImpl.getListById(anyInt())).thenReturn(ofResult);

        FeaturedList featuredList2 = new FeaturedList();
        featuredList2.setFeatureId("42");
        featuredList2.setFeatureUrl("https://example.org/example");
        featuredList2.setIcon("Icon");
        featuredList2.setId(1);
        featuredList2.setIsSettingMenu(true);
        featuredList2.setParent(1);
        featuredList2.setText("Text");
        String content = (new ObjectMapper()).writeValueAsString(featuredList2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(featureListController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"parent\":1,\"featureId\":\"42\",\"text\":\"Text\",\"icon\":\"Icon\",\"isSettingMenu\":true,\"featureUrl\":"
                                        + "\"https://example.org/example\"}"));
    }


    @Test
    void testUpdateEmployee2() throws Exception {
        FeaturedList featuredList = new FeaturedList();
        featuredList.setFeatureId("42");
        featuredList.setFeatureUrl("https://example.org/example");
        featuredList.setIcon("Icon");
        featuredList.setId(1);
        featuredList.setIsSettingMenu(true);
        featuredList.setParent(1);
        featuredList.setText("Text");
        when(featuredListServiceImpl.updatedList((FeaturedList) any())).thenReturn(featuredList);
        when(featuredListServiceImpl.getListById(anyInt())).thenReturn(Optional.empty());

        FeaturedList featuredList1 = new FeaturedList();
        featuredList1.setFeatureId("42");
        featuredList1.setFeatureUrl("https://example.org/example");
        featuredList1.setIcon("Icon");
        featuredList1.setId(1);
        featuredList1.setIsSettingMenu(true);
        featuredList1.setParent(1);
        featuredList1.setText("Text");
        String content = (new ObjectMapper()).writeValueAsString(featuredList1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(featureListController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

