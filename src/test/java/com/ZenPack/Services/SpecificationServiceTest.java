package com.ZenPack.Services;

import com.ZenPack.Dto.SpecificationDto;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Services.SpecificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {SpecificationService.class})
@ExtendWith(SpringExtension.class)
class SpecificationServiceTest {
    @Autowired
    private SpecificationService specificationService;

    @MockBean
    private ZenPackRepository zenPackRepository;


    @Test
    void testGetBySpecification() {
        when(this.zenPackRepository.findAll((org.springframework.data.jpa.domain.Specification<ZenPack>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ResponseEntity<Page<ZenPack>> actualBySpecification = this.specificationService
                .getBySpecification(new SpecificationDto());
        assertTrue(actualBySpecification.hasBody());
        assertTrue(actualBySpecification.getBody().toList().isEmpty());
        assertEquals(HttpStatus.OK, actualBySpecification.getStatusCode());
        assertTrue(actualBySpecification.getHeaders().isEmpty());
        verify(this.zenPackRepository).findAll((org.springframework.data.jpa.domain.Specification<ZenPack>) any(),
                (org.springframework.data.domain.Pageable) any());
    }


    @Test
    void testGetBySpecification2() {
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
        PageImpl<ZenPack> pageImpl = new PageImpl<>(zenPackList);
        when(this.zenPackRepository.findAll((org.springframework.data.jpa.domain.Specification<ZenPack>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        ResponseEntity<Page<ZenPack>> actualBySpecification = this.specificationService
                .getBySpecification(new SpecificationDto());
        assertTrue(actualBySpecification.hasBody());
        assertEquals(1, actualBySpecification.getBody().toList().size());
        assertEquals(HttpStatus.OK, actualBySpecification.getStatusCode());
        assertTrue(actualBySpecification.getHeaders().isEmpty());
        verify(this.zenPackRepository).findAll((org.springframework.data.jpa.domain.Specification<ZenPack>) any(),
                (org.springframework.data.domain.Pageable) any());
    }
}

