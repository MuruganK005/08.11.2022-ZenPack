package com.ZenPack.controller;

import com.ZenPack.model.ReportHeader;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ZenPackReportController.class})
@ExtendWith(SpringExtension.class)
class ZenPackReportControllerTest {
    @Autowired
    private ZenPackReportController zenPackReportController;

    @MockBean
    private ZenPackServiceImpl zenPackServiceImpl;

    @Test
    void testCreateReportHeader() throws Exception {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setColumnOrder(new ArrayList<>());
        reportHeader.setHeaderInfo(new ArrayList<>());
        reportHeader.setReportId(123L);
        reportHeader.setReportName("Report Name");
        when(zenPackServiceImpl.checkZenPackName((String) any())).thenReturn(true);
        when(zenPackServiceImpl.createReportHeader((ReportHeader) any())).thenReturn(reportHeader);

        ReportHeader reportHeader1 = new ReportHeader();
        reportHeader1.setColumnOrder(new ArrayList<>());
        reportHeader1.setHeaderInfo(new ArrayList<>());
        reportHeader1.setReportId(123L);
        reportHeader1.setReportName("Report Name");
        String content = (new ObjectMapper()).writeValueAsString(reportHeader1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/reportHeader/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(417));
    }

    @Test
    void testCreateReportHeader2() throws Exception {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setColumnOrder(new ArrayList<>());
        reportHeader.setHeaderInfo(new ArrayList<>());
        reportHeader.setReportId(123L);
        reportHeader.setReportName("Report Name");
        when(zenPackServiceImpl.checkZenPackName((String) any())).thenReturn(false);
        when(zenPackServiceImpl.createReportHeader((ReportHeader) any())).thenReturn(reportHeader);

        ReportHeader reportHeader1 = new ReportHeader();
        reportHeader1.setColumnOrder(new ArrayList<>());
        reportHeader1.setHeaderInfo(new ArrayList<>());
        reportHeader1.setReportId(123L);
        reportHeader1.setReportName("Report Name");
        String content = (new ObjectMapper()).writeValueAsString(reportHeader1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/reportHeader/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"reportId\":123,\"reportName\":\"Report Name\",\"headerInfo\":[],\"columnOrder\":[]}"));
    }

    @Test
    void testGetAllReportHeader() throws Exception {
        when(zenPackServiceImpl.getAllReportHeader()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/reportHeader/getAll");
        MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllReportHeader2() throws Exception {
        when(zenPackServiceImpl.getAllReportHeader()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/reportHeader/getAll");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetReportHeaderById() throws Exception {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setColumnOrder(new ArrayList<>());
        reportHeader.setHeaderInfo(new ArrayList<>());
        reportHeader.setReportId(123L);
        reportHeader.setReportName("Report Name");
        when(zenPackServiceImpl.getReportHeaderById((Long) any())).thenReturn(reportHeader);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/reportHeader/getReportHeaderById/{reportId}", 123L);
        MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"reportId\":123,\"reportName\":\"Report Name\",\"headerInfo\":[],\"columnOrder\":[]}"));
    }

    @Test
    void testGetReportHeaderById2() throws Exception {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setColumnOrder(new ArrayList<>());
        reportHeader.setHeaderInfo(new ArrayList<>());
        reportHeader.setReportId(123L);
        reportHeader.setReportName("Report Name");
        when(zenPackServiceImpl.getReportHeaderById((Long) any())).thenReturn(reportHeader);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/api/v1/reportHeader/getReportHeaderById/{reportId}", 123L);
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    @Test
    void testGetReportHeaderByName() throws Exception {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setColumnOrder(new ArrayList<>());
        reportHeader.setHeaderInfo(new ArrayList<>());
        reportHeader.setReportId(123L);
        reportHeader.setReportName("Report Name");
        when(zenPackServiceImpl.getReportHeaderByName((String) any())).thenReturn(reportHeader);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/reportHeader/getReportHeaderByName/{name}", "Name");
        MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"reportId\":123,\"reportName\":\"Report Name\",\"headerInfo\":[],\"columnOrder\":[]}"));
    }

    @Test
    void testGetReportHeaderByName2() throws Exception {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setColumnOrder(new ArrayList<>());
        reportHeader.setHeaderInfo(new ArrayList<>());
        reportHeader.setReportId(123L);
        reportHeader.setReportName("Report Name");
        when(zenPackServiceImpl.getReportHeaderByName((String) any())).thenReturn(reportHeader);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/api/v1/reportHeader/getReportHeaderByName/{name}", "Name");
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    @Test
    void testDeleteByReportHeaderId() throws Exception {
        when(zenPackServiceImpl.deleteReportHeaderById((Long) any())).thenReturn("42");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/reportHeader/delete/{reportId}", 123L);
        MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42"));
    }

    @Test
    void testDeleteByReportHeaderId2() throws Exception {
        when(zenPackServiceImpl.deleteReportHeaderById((Long) any())).thenReturn("42");
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders
                .delete("/api/v1/reportHeader/delete/{reportId}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(zenPackReportController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42"));
    }
}

