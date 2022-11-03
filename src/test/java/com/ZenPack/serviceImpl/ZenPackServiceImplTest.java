package com.ZenPack.serviceImpl;


import com.ZenPack.Dto.FeatureDto;
import com.ZenPack.Dto.MenuDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.Specification.FilterRequest;
import com.ZenPack.Specification.SearchRequest;
import com.ZenPack.exception.ZenPackException;
import com.ZenPack.model.ReportHeader;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ReportHeaderRepository;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ZenPackServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ZenPackServiceImplTest {
    @MockBean
    private EntityManager entityManager;

    @MockBean
    private ReportHeaderRepository reportHeaderRepository;

    @MockBean
    private ZenPackRepository zenPackRepository;

    @Autowired
    private ZenPackServiceImpl zenPackServiceImpl;

    @Test
    void testSaveZenPack() {
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
        when(zenPackRepository.save((ZenPack) any())).thenReturn(zenPack);

        ZenPack zenPack1 = new ZenPack();
        zenPack1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack1.setCreatedDate("2020-03-01");
        zenPack1.setFeatures(new ArrayList<>());
        zenPack1.setInActive(true);
        zenPack1.setMenus(new ArrayList<>());
        zenPack1.setName("Name");
        zenPack1.setUpdatedBy("2020-03-01");
        zenPack1.setUpdatedTime("2020-03-01");
        zenPack1.setZenPackId(123L);
        ResponseEntity<ZenPack> actualSaveZenPackResult = zenPackServiceImpl.saveZenPack(zenPack1);
        assertTrue(actualSaveZenPackResult.hasBody());
        assertTrue(actualSaveZenPackResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualSaveZenPackResult.getStatusCode());
        verify(zenPackRepository).save((ZenPack) any());
    }
    // @Test
    void testCreateZenPack() throws ZenPackException {
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

        ZenPack zenPack1 = new ZenPack();
        zenPack1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack1.setCreatedDate("2020-03-01");
        zenPack1.setFeatures(new ArrayList<>());
        zenPack1.setInActive(true);
        zenPack1.setMenus(new ArrayList<>());
        zenPack1.setName("Name");
        zenPack1.setUpdatedBy("2020-03-01");
        zenPack1.setUpdatedTime("2020-03-01");
        zenPack1.setZenPackId(123L);
        Optional<ZenPack> ofResult = Optional.of(zenPack1);
        when(zenPackRepository.save((ZenPack) any())).thenReturn(zenPack);
        when(zenPackRepository.findByName((String) any())).thenReturn(ofResult);

        ZenPackDto zenPackDto = new ZenPackDto();
        zenPackDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPackDto.setCreatedDate("2020-03-01");
        zenPackDto.setFeatures(new ArrayList<>());
        zenPackDto.setInActive(true);
        zenPackDto.setMenus(new ArrayList<>());
        zenPackDto.setName("Name");
        zenPackDto.setUpdatedBy("2020-03-01");
        zenPackDto.setUpdatedTime("2020-03-01");
        zenPackDto.setZenPackId(123L);
        ResponseEntity<ZenPackDto> actualCreateZenPackResult = zenPackServiceImpl.createZenPack(zenPackDto);
        assertTrue(actualCreateZenPackResult.hasBody());
        assertTrue(actualCreateZenPackResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCreateZenPackResult.getStatusCode());
        ZenPackDto body = actualCreateZenPackResult.getBody();
        assertEquals("02-11-2022 11:42:31", body.getCreatedDate());
        assertEquals(123L, body.getZenPackId().longValue());
        assertEquals("02-11-2022 11:42:31", body.getUpdatedTime());
        verify(zenPackRepository).save((ZenPack) any());
        verify(zenPackRepository).findByName((String) any());
    }
    // @Test
    void testCreateZenPack2() throws ZenPackException {
        ArrayList<MenuDto> menus = new ArrayList<>();

        ZenPack zenPack = new ZenPack(123L, "Name", menus, new ArrayList<>(), "2020-03-01", "2020-03-01",
                "Jan 1, 2020 8:00am GMT+0100", "2020-03-01", true);
        zenPack.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack.setCreatedDate("2020-03-01");
        zenPack.setFeatures(new ArrayList<>());
        zenPack.setInActive(true);
        zenPack.setMenus(new ArrayList<>());
        zenPack.setName("Name");
        zenPack.setUpdatedBy("2020-03-01");
        zenPack.setUpdatedTime("2020-03-01");
        zenPack.setZenPackId(123L);

        ZenPack zenPack1 = new ZenPack();
        zenPack1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack1.setCreatedDate("2020-03-01");
        zenPack1.setFeatures(new ArrayList<>());
        zenPack1.setInActive(true);
        zenPack1.setMenus(new ArrayList<>());
        zenPack1.setName("Name");
        zenPack1.setUpdatedBy("2020-03-01");
        zenPack1.setUpdatedTime("2020-03-01");
        zenPack1.setZenPackId(123L);
        Optional<ZenPack> ofResult = Optional.of(zenPack1);
        when(zenPackRepository.save((ZenPack) any())).thenReturn(zenPack);
        when(zenPackRepository.findByName((String) any())).thenReturn(ofResult);

        ZenPackDto zenPackDto = new ZenPackDto();
        zenPackDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPackDto.setCreatedDate("2020-03-01");
        zenPackDto.setFeatures(new ArrayList<>());
        zenPackDto.setInActive(true);
        zenPackDto.setMenus(new ArrayList<>());
        zenPackDto.setName("Name");
        zenPackDto.setUpdatedBy("2020-03-01");
        zenPackDto.setUpdatedTime("2020-03-01");
        zenPackDto.setZenPackId(123L);
        ResponseEntity<ZenPackDto> actualCreateZenPackResult = zenPackServiceImpl.createZenPack(zenPackDto);
        assertTrue(actualCreateZenPackResult.hasBody());
        assertTrue(actualCreateZenPackResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCreateZenPackResult.getStatusCode());
        ZenPackDto body = actualCreateZenPackResult.getBody();
        assertEquals("02-11-2022 11:42:31", body.getCreatedDate());
        assertEquals(123L, body.getZenPackId().longValue());
        assertEquals("02-11-2022 11:42:31", body.getUpdatedTime());
        verify(zenPackRepository).save((ZenPack) any());
        verify(zenPackRepository).findByName((String) any());
    }
    @Test
    void testCreateZenPack3() throws ZenPackException {
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
        ZenPack zenPack1 = mock(ZenPack.class);
        when(zenPack1.getZenPackId()).thenReturn(1L);
        doNothing().when(zenPack1).setCreatedBy((String) any());
        doNothing().when(zenPack1).setCreatedDate((String) any());
        doNothing().when(zenPack1).setFeatures((List<FeatureDto>) any());
        doNothing().when(zenPack1).setInActive((Boolean) any());
        doNothing().when(zenPack1).setMenus((List<MenuDto>) any());
        doNothing().when(zenPack1).setName((String) any());
        doNothing().when(zenPack1).setUpdatedBy((String) any());
        doNothing().when(zenPack1).setUpdatedTime((String) any());
        doNothing().when(zenPack1).setZenPackId((Long) any());
        zenPack1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack1.setCreatedDate("2020-03-01");
        zenPack1.setFeatures(new ArrayList<>());
        zenPack1.setInActive(true);
        zenPack1.setMenus(new ArrayList<>());
        zenPack1.setName("Name");
        zenPack1.setUpdatedBy("2020-03-01");
        zenPack1.setUpdatedTime("2020-03-01");
        zenPack1.setZenPackId(123L);
        Optional<ZenPack> ofResult = Optional.of(zenPack1);
        when(zenPackRepository.save((ZenPack) any())).thenReturn(zenPack);
        when(zenPackRepository.findByName((String) any())).thenReturn(ofResult);

        ZenPackDto zenPackDto = new ZenPackDto();
        zenPackDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPackDto.setCreatedDate("2020-03-01");
        zenPackDto.setFeatures(new ArrayList<>());
        zenPackDto.setInActive(true);
        zenPackDto.setMenus(new ArrayList<>());
        zenPackDto.setName("Name");
        zenPackDto.setUpdatedBy("2020-03-01");
        zenPackDto.setUpdatedTime("2020-03-01");
        zenPackDto.setZenPackId(123L);
        assertThrows(ZenPackException.class, () -> zenPackServiceImpl.createZenPack(zenPackDto));
        verify(zenPackRepository).findByName((String) any());
        verify(zenPack1).getZenPackId();
        verify(zenPack1).setCreatedBy((String) any());
        verify(zenPack1).setCreatedDate((String) any());
        verify(zenPack1).setFeatures((List<FeatureDto>) any());
        verify(zenPack1).setInActive((Boolean) any());
        verify(zenPack1).setMenus((List<MenuDto>) any());
        verify(zenPack1).setName((String) any());
        verify(zenPack1).setUpdatedBy((String) any());
        verify(zenPack1).setUpdatedTime((String) any());
        verify(zenPack1).setZenPackId((Long) any());
    }
    //  @Test
    void testCreateZenPack4() throws ZenPackException {
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
        when(zenPackRepository.save((ZenPack) any())).thenReturn(zenPack);
        when(zenPackRepository.findByName((String) any())).thenReturn(Optional.empty());
        ZenPack zenPack1 = mock(ZenPack.class);
        when(zenPack1.getZenPackId()).thenReturn(123L);
        doNothing().when(zenPack1).setCreatedBy((String) any());
        doNothing().when(zenPack1).setCreatedDate((String) any());
        doNothing().when(zenPack1).setFeatures((List<FeatureDto>) any());
        doNothing().when(zenPack1).setInActive((Boolean) any());
        doNothing().when(zenPack1).setMenus((List<MenuDto>) any());
        doNothing().when(zenPack1).setName((String) any());
        doNothing().when(zenPack1).setUpdatedBy((String) any());
        doNothing().when(zenPack1).setUpdatedTime((String) any());
        doNothing().when(zenPack1).setZenPackId((Long) any());
        zenPack1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack1.setCreatedDate("2020-03-01");
        zenPack1.setFeatures(new ArrayList<>());
        zenPack1.setInActive(true);
        zenPack1.setMenus(new ArrayList<>());
        zenPack1.setName("Name");
        zenPack1.setUpdatedBy("2020-03-01");
        zenPack1.setUpdatedTime("2020-03-01");
        zenPack1.setZenPackId(123L);

        ZenPackDto zenPackDto = new ZenPackDto();
        zenPackDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPackDto.setCreatedDate("2020-03-01");
        zenPackDto.setFeatures(new ArrayList<>());
        zenPackDto.setInActive(true);
        zenPackDto.setMenus(new ArrayList<>());
        zenPackDto.setName("Name");
        zenPackDto.setUpdatedBy("2020-03-01");
        zenPackDto.setUpdatedTime("2020-03-01");
        zenPackDto.setZenPackId(123L);
        ResponseEntity<ZenPackDto> actualCreateZenPackResult = zenPackServiceImpl.createZenPack(zenPackDto);
        assertTrue(actualCreateZenPackResult.hasBody());
        assertTrue(actualCreateZenPackResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCreateZenPackResult.getStatusCode());
        ZenPackDto body = actualCreateZenPackResult.getBody();
        assertEquals("02-11-2022 11:42:31", body.getCreatedDate());
        assertEquals(123L, body.getZenPackId().longValue());
        assertEquals("02-11-2022 11:42:31", body.getUpdatedTime());
        verify(zenPackRepository).save((ZenPack) any());
        verify(zenPackRepository).findByName((String) any());
        verify(zenPack1).setCreatedBy((String) any());
        verify(zenPack1).setCreatedDate((String) any());
        verify(zenPack1).setFeatures((List<FeatureDto>) any());
        verify(zenPack1).setInActive((Boolean) any());
        verify(zenPack1).setMenus((List<MenuDto>) any());
        verify(zenPack1).setName((String) any());
        verify(zenPack1).setUpdatedBy((String) any());
        verify(zenPack1).setUpdatedTime((String) any());
        verify(zenPack1).setZenPackId((Long) any());
    }
    //  @Test
    void testCreateZenPack5() throws ZenPackException {
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
        ZenPack zenPack1 = mock(ZenPack.class);
        when(zenPack1.getZenPackId()).thenReturn(123L);
        doNothing().when(zenPack1).setCreatedBy((String) any());
        doNothing().when(zenPack1).setCreatedDate((String) any());
        doNothing().when(zenPack1).setFeatures((List<FeatureDto>) any());
        doNothing().when(zenPack1).setInActive((Boolean) any());
        doNothing().when(zenPack1).setMenus((List<MenuDto>) any());
        doNothing().when(zenPack1).setName((String) any());
        doNothing().when(zenPack1).setUpdatedBy((String) any());
        doNothing().when(zenPack1).setUpdatedTime((String) any());
        doNothing().when(zenPack1).setZenPackId((Long) any());
        zenPack1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack1.setCreatedDate("2020-03-01");
        zenPack1.setFeatures(new ArrayList<>());
        zenPack1.setInActive(true);
        zenPack1.setMenus(new ArrayList<>());
        zenPack1.setName("Name");
        zenPack1.setUpdatedBy("2020-03-01");
        zenPack1.setUpdatedTime("2020-03-01");
        zenPack1.setZenPackId(123L);
        Optional<ZenPack> ofResult = Optional.of(zenPack1);
        when(zenPackRepository.save((ZenPack) any())).thenReturn(zenPack);
        when(zenPackRepository.findByName((String) any())).thenReturn(ofResult);

        FeatureDto featureDto = new FeatureDto();
        featureDto.setFeatureId("42");
        featureDto.setFeatureUrl("https://example.org/example");
        featureDto.setIcon("Icon");
        featureDto.setId(1);
        featureDto.setIsSettingMenu(true);
        featureDto.setParent(1);
        featureDto.setText("Text");

        ArrayList<FeatureDto> featureDtoList = new ArrayList<>();
        featureDtoList.add(featureDto);

        ZenPackDto zenPackDto = new ZenPackDto();
        zenPackDto.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPackDto.setCreatedDate("2020-03-01");
        zenPackDto.setFeatures(featureDtoList);
        zenPackDto.setInActive(true);
        zenPackDto.setMenus(new ArrayList<>());
        zenPackDto.setName("Name");
        zenPackDto.setUpdatedBy("2020-03-01");
        zenPackDto.setUpdatedTime("2020-03-01");
        zenPackDto.setZenPackId(123L);
        ResponseEntity<ZenPackDto> actualCreateZenPackResult = zenPackServiceImpl.createZenPack(zenPackDto);
        assertTrue(actualCreateZenPackResult.hasBody());
        assertTrue(actualCreateZenPackResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCreateZenPackResult.getStatusCode());
        ZenPackDto body = actualCreateZenPackResult.getBody();
        assertEquals("02-11-2022 11:42:31", body.getCreatedDate());
        assertEquals(123L, body.getZenPackId().longValue());
        assertEquals("02-11-2022 11:42:31", body.getUpdatedTime());
        verify(zenPackRepository).save((ZenPack) any());
        verify(zenPackRepository).findByName((String) any());
        verify(zenPack1).getZenPackId();
        verify(zenPack1).setCreatedBy((String) any());
        verify(zenPack1).setCreatedDate((String) any());
        verify(zenPack1).setFeatures((List<FeatureDto>) any());
        verify(zenPack1).setInActive((Boolean) any());
        verify(zenPack1).setMenus((List<MenuDto>) any());
        verify(zenPack1).setName((String) any());
        verify(zenPack1).setUpdatedBy((String) any());
        verify(zenPack1).setUpdatedTime((String) any());
        verify(zenPack1).setZenPackId((Long) any());
    }
    @Test
    void testGetAllZenPack() throws JsonProcessingException {
        when(zenPackRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(zenPackServiceImpl.getAllZenPack().isEmpty());
        verify(zenPackRepository).findAll();
    }
    @Test
    void testGetAllZenPack2() throws JsonProcessingException {
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

        ArrayList<ZenPack> zenPackList = new ArrayList<>();
        zenPackList.add(zenPack);
        when(zenPackRepository.findAll()).thenReturn(zenPackList);
        assertEquals(1, zenPackServiceImpl.getAllZenPack().size());
        verify(zenPackRepository).findAll();
    }
    @Test
    void testGetAllZenPack3() throws JsonProcessingException {
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

        ZenPack zenPack1 = new ZenPack();
        zenPack1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack1.setCreatedDate("2020-03-01");
        zenPack1.setFeatures(new ArrayList<>());
        zenPack1.setInActive(true);
        zenPack1.setMenus(new ArrayList<>());
        zenPack1.setName("Name");
        zenPack1.setUpdatedBy("2020-03-01");
        zenPack1.setUpdatedTime("2020-03-01");
        zenPack1.setZenPackId(123L);

        ArrayList<ZenPack> zenPackList = new ArrayList<>();
        zenPackList.add(zenPack1);
        zenPackList.add(zenPack);
        when(zenPackRepository.findAll()).thenReturn(zenPackList);
        assertEquals(2, zenPackServiceImpl.getAllZenPack().size());
        verify(zenPackRepository).findAll();
    }
    @Test
    void testGetAllZenPack4() throws JsonProcessingException {
        ZenPack zenPack = mock(ZenPack.class);
        when(zenPack.getInActive()).thenReturn(true);
        when(zenPack.getZenPackId()).thenReturn(123L);
        when(zenPack.getCreatedBy()).thenReturn("Jan 1, 2020 8:00am GMT+0100");
        when(zenPack.getCreatedDate()).thenReturn("2020-03-01");
        when(zenPack.getName()).thenReturn("Name");
        when(zenPack.getUpdatedBy()).thenReturn("2020-03-01");
        when(zenPack.getUpdatedTime()).thenReturn("2020-03-01");
        when(zenPack.getFeatures()).thenReturn(new ArrayList<>());
        when(zenPack.getMenus()).thenReturn(new ArrayList<>());
        doNothing().when(zenPack).setCreatedBy((String) any());
        doNothing().when(zenPack).setCreatedDate((String) any());
        doNothing().when(zenPack).setFeatures((List<FeatureDto>) any());
        doNothing().when(zenPack).setInActive((Boolean) any());
        doNothing().when(zenPack).setMenus((List<MenuDto>) any());
        doNothing().when(zenPack).setName((String) any());
        doNothing().when(zenPack).setUpdatedBy((String) any());
        doNothing().when(zenPack).setUpdatedTime((String) any());
        doNothing().when(zenPack).setZenPackId((Long) any());
        zenPack.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack.setCreatedDate("2020-03-01");
        zenPack.setFeatures(new ArrayList<>());
        zenPack.setInActive(true);
        zenPack.setMenus(new ArrayList<>());
        zenPack.setName("Name");
        zenPack.setUpdatedBy("2020-03-01");
        zenPack.setUpdatedTime("2020-03-01");
        zenPack.setZenPackId(123L);

        ArrayList<ZenPack> zenPackList = new ArrayList<>();
        zenPackList.add(zenPack);
        when(zenPackRepository.findAll()).thenReturn(zenPackList);
        assertEquals(1, zenPackServiceImpl.getAllZenPack().size());
        verify(zenPackRepository).findAll();
        verify(zenPack).getInActive();
        verify(zenPack, atLeast(1)).getZenPackId();
        verify(zenPack).getCreatedBy();
        verify(zenPack).getCreatedDate();
        verify(zenPack, atLeast(1)).getName();
        verify(zenPack).getUpdatedBy();
        verify(zenPack).getUpdatedTime();
        verify(zenPack, atLeast(1)).getFeatures();
        verify(zenPack, atLeast(1)).getMenus();
        verify(zenPack).setCreatedBy((String) any());
        verify(zenPack).setCreatedDate((String) any());
        verify(zenPack).setFeatures((List<FeatureDto>) any());
        verify(zenPack).setInActive((Boolean) any());
        verify(zenPack).setMenus((List<MenuDto>) any());
        verify(zenPack).setName((String) any());
        verify(zenPack).setUpdatedBy((String) any());
        verify(zenPack).setUpdatedTime((String) any());
        verify(zenPack).setZenPackId((Long) any());
    }
    @Test
    void testGetAllZenPack5() throws JsonProcessingException {
        FeatureDto featureDto = new FeatureDto();
        featureDto.setFeatureId("42");
        featureDto.setFeatureUrl("https://example.org/example");
        featureDto.setIcon("Icon");
        featureDto.setId(1);
        featureDto.setIsSettingMenu(true);
        featureDto.setParent(1);
        featureDto.setText("Text");

        ArrayList<FeatureDto> featureDtoList = new ArrayList<>();
        featureDtoList.add(featureDto);
        ZenPack zenPack = mock(ZenPack.class);
        when(zenPack.getInActive()).thenReturn(true);
        when(zenPack.getZenPackId()).thenReturn(123L);
        when(zenPack.getCreatedBy()).thenReturn("Jan 1, 2020 8:00am GMT+0100");
        when(zenPack.getCreatedDate()).thenReturn("2020-03-01");
        when(zenPack.getName()).thenReturn("Name");
        when(zenPack.getUpdatedBy()).thenReturn("2020-03-01");
        when(zenPack.getUpdatedTime()).thenReturn("2020-03-01");
        when(zenPack.getFeatures()).thenReturn(featureDtoList);
        when(zenPack.getMenus()).thenReturn(new ArrayList<>());
        doNothing().when(zenPack).setCreatedBy((String) any());
        doNothing().when(zenPack).setCreatedDate((String) any());
        doNothing().when(zenPack).setFeatures((List<FeatureDto>) any());
        doNothing().when(zenPack).setInActive((Boolean) any());
        doNothing().when(zenPack).setMenus((List<MenuDto>) any());
        doNothing().when(zenPack).setName((String) any());
        doNothing().when(zenPack).setUpdatedBy((String) any());
        doNothing().when(zenPack).setUpdatedTime((String) any());
        doNothing().when(zenPack).setZenPackId((Long) any());
        zenPack.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack.setCreatedDate("2020-03-01");
        zenPack.setFeatures(new ArrayList<>());
        zenPack.setInActive(true);
        zenPack.setMenus(new ArrayList<>());
        zenPack.setName("Name");
        zenPack.setUpdatedBy("2020-03-01");
        zenPack.setUpdatedTime("2020-03-01");
        zenPack.setZenPackId(123L);

        ArrayList<ZenPack> zenPackList = new ArrayList<>();
        zenPackList.add(zenPack);
        when(zenPackRepository.findAll()).thenReturn(zenPackList);
        assertEquals(1, zenPackServiceImpl.getAllZenPack().size());
        verify(zenPackRepository).findAll();
        verify(zenPack).getInActive();
        verify(zenPack, atLeast(1)).getZenPackId();
        verify(zenPack).getCreatedBy();
        verify(zenPack).getCreatedDate();
        verify(zenPack, atLeast(1)).getName();
        verify(zenPack).getUpdatedBy();
        verify(zenPack).getUpdatedTime();
        verify(zenPack, atLeast(1)).getFeatures();
        verify(zenPack, atLeast(1)).getMenus();
        verify(zenPack).setCreatedBy((String) any());
        verify(zenPack).setCreatedDate((String) any());
        verify(zenPack).setFeatures((List<FeatureDto>) any());
        verify(zenPack).setInActive((Boolean) any());
        verify(zenPack).setMenus((List<MenuDto>) any());
        verify(zenPack).setName((String) any());
        verify(zenPack).setUpdatedBy((String) any());
        verify(zenPack).setUpdatedTime((String) any());
        verify(zenPack).setZenPackId((Long) any());
    }
    @Test
    void testDeleteByzenPackId() {
        doNothing().when(zenPackRepository).deleteByZenPackId((Long) any());
        assertEquals(" Id 123 Deleted SuccessFully", zenPackServiceImpl.deleteByzenPackId(123L));
        verify(zenPackRepository).deleteByZenPackId((Long) any());
    }
    @Test
    void testGetByZenPackId() {
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
        Optional<ZenPack> ofResult = Optional.of(zenPack);
        when(zenPackRepository.findByZenPackId((Long) any())).thenReturn(ofResult);
        ZenPackDto actualByZenPackId = zenPackServiceImpl.getByZenPackId(123L);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualByZenPackId.getCreatedBy());
        assertEquals(123L, actualByZenPackId.getZenPackId().longValue());
        assertEquals("2020-03-01", actualByZenPackId.getUpdatedTime());
        assertEquals("2020-03-01", actualByZenPackId.getUpdatedBy());
        assertEquals("Name", actualByZenPackId.getName());
        assertTrue(actualByZenPackId.getMenus().isEmpty());
        assertFalse(actualByZenPackId.getInActive());
        assertTrue(actualByZenPackId.getFeatures().isEmpty());
        assertEquals("2020-03-01", actualByZenPackId.getCreatedDate());
        verify(zenPackRepository).findByZenPackId((Long) any());
    }
    @Test
    void testGetByZenPackId2() {
        when(zenPackRepository.findByZenPackId((Long) any())).thenReturn(null);
        assertNull(zenPackServiceImpl.getByZenPackId(123L));
        verify(zenPackRepository).findByZenPackId((Long) any());
    }
    @Test
    void testGetByZenPackId3() {
        when(zenPackRepository.findByZenPackId((Long) any())).thenReturn(Optional.empty());
        assertNull(zenPackServiceImpl.getByZenPackId(123L));
        verify(zenPackRepository).findByZenPackId((Long) any());
    }
    @Test
    void testGetByZenPackId4() {
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
        Optional<ZenPack> ofResult = Optional.of(zenPack);
        when(zenPackRepository.findByZenPackId((Long) any())).thenReturn(ofResult);
        ZenPackDto actualByZenPackId = zenPackServiceImpl.getByZenPackId(null);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualByZenPackId.getCreatedBy());
        assertEquals(123L, actualByZenPackId.getZenPackId().longValue());
        assertEquals("2020-03-01", actualByZenPackId.getUpdatedTime());
        assertEquals("2020-03-01", actualByZenPackId.getUpdatedBy());
        assertEquals("Name", actualByZenPackId.getName());
        assertTrue(actualByZenPackId.getMenus().isEmpty());
        assertFalse(actualByZenPackId.getInActive());
        assertTrue(actualByZenPackId.getFeatures().isEmpty());
        assertEquals("2020-03-01", actualByZenPackId.getCreatedDate());
        verify(zenPackRepository).findByZenPackId((Long) any());
    }
    @Test
    void testCheckZenPackName() {
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
        Optional<ZenPack> ofResult = Optional.of(zenPack);
        when(zenPackRepository.findByName((String) any())).thenReturn(ofResult);
        assertTrue(zenPackServiceImpl.checkZenPackName("Name"));
        verify(zenPackRepository).findByName((String) any());
    }
    @Test
    void testCheckZenPackName2() {
        when(zenPackRepository.findByName((String) any())).thenReturn(Optional.empty());
        assertFalse(zenPackServiceImpl.checkZenPackName("Name"));
        verify(zenPackRepository).findByName((String) any());
    }
    @Test
    void testSearchZenPack() {
        PageImpl<ZenPack> pageImpl = new PageImpl<>(new ArrayList<>());
        when(zenPackRepository.findAll((Specification<ZenPack>) any(), (Pageable) any())).thenReturn(pageImpl);
        Page<ZenPack> actualSearchZenPackResult = zenPackServiceImpl.searchZenPack(new SearchRequest());
        assertSame(pageImpl, actualSearchZenPackResult);
        assertTrue(actualSearchZenPackResult.toList().isEmpty());
        verify(zenPackRepository).findAll((Specification<ZenPack>) any(), (Pageable) any());
    }
    @Test
    void testSearchZenPack2() {
        PageImpl<ZenPack> pageImpl = new PageImpl<>(new ArrayList<>());
        when(zenPackRepository.findAll((Specification<ZenPack>) any(), (Pageable) any())).thenReturn(pageImpl);
        ArrayList<FilterRequest> filters = new ArrayList<>();
        Page<ZenPack> actualSearchZenPackResult = zenPackServiceImpl
                .searchZenPack(new SearchRequest(filters, new ArrayList<>(), 100, 3));
        assertSame(pageImpl, actualSearchZenPackResult);
        assertTrue(actualSearchZenPackResult.toList().isEmpty());
        verify(zenPackRepository).findAll((Specification<ZenPack>) any(), (Pageable) any());
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testSearchZenPack3() {
        when(zenPackRepository.findAll((Specification<ZenPack>) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        zenPackServiceImpl.searchZenPack(null);
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testSearchZenPack4() {
        when(zenPackRepository.findAll((Specification<ZenPack>) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        SearchRequest searchRequest = mock(SearchRequest.class);
        when(searchRequest.getPage()).thenReturn(-1);
        when(searchRequest.getSize()).thenReturn(3);
        zenPackServiceImpl.searchZenPack(searchRequest);
    }
    @Test
    void testSearchZenPack5() {
        PageImpl<ZenPack> pageImpl = new PageImpl<>(new ArrayList<>());
        when(zenPackRepository.findAll((Specification<ZenPack>) any(), (Pageable) any())).thenReturn(pageImpl);
        SearchRequest searchRequest = mock(SearchRequest.class);
        when(searchRequest.getPage()).thenReturn(1);
        when(searchRequest.getSize()).thenReturn(0);
        Page<ZenPack> actualSearchZenPackResult = zenPackServiceImpl.searchZenPack(searchRequest);
        assertSame(pageImpl, actualSearchZenPackResult);
        assertTrue(actualSearchZenPackResult.toList().isEmpty());
        verify(zenPackRepository).findAll((Specification<ZenPack>) any(), (Pageable) any());
        verify(searchRequest).getPage();
        verify(searchRequest).getSize();
    }
    @Test
    void testSetActiveOrInActive() {
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
        Optional<ZenPack> ofResult = Optional.of(zenPack);

        ZenPack zenPack1 = new ZenPack();
        zenPack1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        zenPack1.setCreatedDate("2020-03-01");
        zenPack1.setFeatures(new ArrayList<>());
        zenPack1.setInActive(true);
        zenPack1.setMenus(new ArrayList<>());
        zenPack1.setName("Name");
        zenPack1.setUpdatedBy("2020-03-01");
        zenPack1.setUpdatedTime("2020-03-01");
        zenPack1.setZenPackId(123L);
        when(zenPackRepository.save((ZenPack) any())).thenReturn(zenPack1);
        when(zenPackRepository.findByZenPackId((Long) any())).thenReturn(ofResult);
        assertEquals("ZenPack 123 Set InActive Successful", zenPackServiceImpl.setActiveOrInActive(123L));
        verify(zenPackRepository).save((ZenPack) any());
        verify(zenPackRepository).findByZenPackId((Long) any());
    }
    @Test
    void testSetActiveOrInActive2() {
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
        when(zenPackRepository.save((ZenPack) any())).thenReturn(zenPack);
        when(zenPackRepository.findByZenPackId((Long) any())).thenReturn(Optional.empty());
        assertEquals("ZenPack 123 Set InActive Successful", zenPackServiceImpl.setActiveOrInActive(123L));
        verify(zenPackRepository).findByZenPackId((Long) any());
    }
    @Test
    void testCreateReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setColumnOrder(new ArrayList<>());
        reportHeader.setHeaderInfo(new ArrayList<>());
        reportHeader.setReportId(123L);
        reportHeader.setReportName("Report Name");
        when(reportHeaderRepository.save((ReportHeader) any())).thenReturn(reportHeader);

        ReportHeader reportHeader1 = new ReportHeader();
        reportHeader1.setColumnOrder(new ArrayList<>());
        reportHeader1.setHeaderInfo(new ArrayList<>());
        reportHeader1.setReportId(123L);
        reportHeader1.setReportName("Report Name");
        assertSame(reportHeader, zenPackServiceImpl.createReportHeader(reportHeader1));
        verify(reportHeaderRepository).save((ReportHeader) any());
    }
    @Test
    void testGetAllReportHeader() {
        ArrayList<ReportHeader> reportHeaderList = new ArrayList<>();
        when(reportHeaderRepository.findAll()).thenReturn(reportHeaderList);
        List<ReportHeader> actualAllReportHeader = zenPackServiceImpl.getAllReportHeader();
        assertSame(reportHeaderList, actualAllReportHeader);
        assertTrue(actualAllReportHeader.isEmpty());
        verify(reportHeaderRepository).findAll();
    }
    @Test
    void testDeleteReportHeaderById() {
        doNothing().when(reportHeaderRepository).deleteById((Long) any());
        assertEquals("Deleted Successfully", zenPackServiceImpl.deleteReportHeaderById(123L));
        verify(reportHeaderRepository).deleteById((Long) any());
    }
    @Test
    void testGetReportHeaderById() {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setColumnOrder(new ArrayList<>());
        reportHeader.setHeaderInfo(new ArrayList<>());
        reportHeader.setReportId(123L);
        reportHeader.setReportName("Report Name");
        Optional<ReportHeader> ofResult = Optional.of(reportHeader);
        when(reportHeaderRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(reportHeader, zenPackServiceImpl.getReportHeaderById(123L));
        verify(reportHeaderRepository).findById((Long) any());
    }
    @Test
    void testGetReportHeaderById2() {
        when(reportHeaderRepository.findById((Long) any())).thenReturn(null);
        assertNull(zenPackServiceImpl.getReportHeaderById(123L));
        verify(reportHeaderRepository).findById((Long) any());
    }
    @Test
    void testGetReportHeaderById3() {
        when(reportHeaderRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertNull(zenPackServiceImpl.getReportHeaderById(123L));
        verify(reportHeaderRepository).findById((Long) any());
    }
    @Test
    void testGetReportHeaderByName() {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setColumnOrder(new ArrayList<>());
        reportHeader.setHeaderInfo(new ArrayList<>());
        reportHeader.setReportId(123L);
        reportHeader.setReportName("Report Name");
        Optional<ReportHeader> ofResult = Optional.of(reportHeader);
        when(reportHeaderRepository.findByReportName((String) any())).thenReturn(ofResult);
        assertSame(reportHeader, zenPackServiceImpl.getReportHeaderByName("Report Name"));
        verify(reportHeaderRepository).findByReportName((String) any());
    }
    @Test
    void testGetReportHeaderByName2() {
        when(reportHeaderRepository.findByReportName((String) any())).thenReturn(null);
        assertNull(zenPackServiceImpl.getReportHeaderByName("Report Name"));
        verify(reportHeaderRepository).findByReportName((String) any());
    }
    @Test
    void testGetReportHeaderByName3() {
        when(reportHeaderRepository.findByReportName((String) any())).thenReturn(Optional.empty());
        assertNull(zenPackServiceImpl.getReportHeaderByName("Report Name"));
        verify(reportHeaderRepository).findByReportName((String) any());
    }
}


