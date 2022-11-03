package com.ZenPack.serviceImpl;


import com.ZenPack.model.FeaturedList;
import com.ZenPack.repository.FeaturedListRepository;
import com.ZenPack.service.Impl.FeaturedListServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {FeaturedListServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FeaturedListServiceImplTest {
    @MockBean
    private FeaturedListRepository featuredListRepository;

    @Autowired
    private FeaturedListServiceImpl featuredListServiceImpl;

    @Test
    void testSave() {
        FeaturedList featuredList = new FeaturedList();
        featuredList.setFeatureId("42");
        featuredList.setFeatureUrl("https://example.org/example");
        featuredList.setIcon("Icon");
        featuredList.setId(1);
        featuredList.setIsSettingMenu(true);
        featuredList.setParent(1);
        featuredList.setText("Text");
        when(featuredListRepository.save((FeaturedList) any())).thenReturn(featuredList);

        FeaturedList featuredList1 = new FeaturedList();
        featuredList1.setFeatureId("42");
        featuredList1.setFeatureUrl("https://example.org/example");
        featuredList1.setIcon("Icon");
        featuredList1.setId(1);
        featuredList1.setIsSettingMenu(true);
        featuredList1.setParent(1);
        featuredList1.setText("Text");
        assertSame(featuredList, featuredListServiceImpl.save(featuredList1));
        verify(featuredListRepository).save((FeaturedList) any());
    }
    @Test
    void testFindAllList() {
        ArrayList<FeaturedList> featuredListList = new ArrayList<>();
        when(featuredListRepository.findAll()).thenReturn(featuredListList);
        List<FeaturedList> actualFindAllListResult = featuredListServiceImpl.findAllList();
        assertSame(featuredListList, actualFindAllListResult);
        assertTrue(actualFindAllListResult.isEmpty());
        verify(featuredListRepository).findAll();
    }
    @Test
    void testFindByKeyword() {
        ArrayList<FeaturedList> featuredListList = new ArrayList<>();
        when(featuredListRepository.findByKeyword((String) any())).thenReturn(featuredListList);
        List<FeaturedList> actualFindByKeywordResult = featuredListServiceImpl.findByKeyword("Keyword");
        assertSame(featuredListList, actualFindByKeywordResult);
        assertTrue(actualFindByKeywordResult.isEmpty());
        verify(featuredListRepository).findByKeyword((String) any());
    }
    @Test
    void testUpdatedList() {
        FeaturedList featuredList = new FeaturedList();
        featuredList.setFeatureId("42");
        featuredList.setFeatureUrl("https://example.org/example");
        featuredList.setIcon("Icon");
        featuredList.setId(1);
        featuredList.setIsSettingMenu(true);
        featuredList.setParent(1);
        featuredList.setText("Text");
        when(featuredListRepository.save((FeaturedList) any())).thenReturn(featuredList);

        FeaturedList featuredList1 = new FeaturedList();
        featuredList1.setFeatureId("42");
        featuredList1.setFeatureUrl("https://example.org/example");
        featuredList1.setIcon("Icon");
        featuredList1.setId(1);
        featuredList1.setIsSettingMenu(true);
        featuredList1.setParent(1);
        featuredList1.setText("Text");
        assertSame(featuredList, featuredListServiceImpl.updatedList(featuredList1));
        verify(featuredListRepository).save((FeaturedList) any());
    }
    @Test
    void testGetListById() {
        FeaturedList featuredList = new FeaturedList();
        featuredList.setFeatureId("42");
        featuredList.setFeatureUrl("https://example.org/example");
        featuredList.setIcon("Icon");
        featuredList.setId(1);
        featuredList.setIsSettingMenu(true);
        featuredList.setParent(1);
        featuredList.setText("Text");
        Optional<FeaturedList> ofResult = Optional.of(featuredList);
        when(featuredListRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<FeaturedList> actualListById = featuredListServiceImpl.getListById(123);
        assertSame(ofResult, actualListById);
        assertTrue(actualListById.isPresent());
        verify(featuredListRepository).findById((Integer) any());
    }
    @Test
    void testDeleteList() {
        FeaturedList featuredList = new FeaturedList();
        featuredList.setFeatureId("42");
        featuredList.setFeatureUrl("https://example.org/example");
        featuredList.setIcon("Icon");
        featuredList.setId(1);
        featuredList.setIsSettingMenu(true);
        featuredList.setParent(1);
        featuredList.setText("Text");
        Optional<FeaturedList> ofResult = Optional.of(featuredList);
        doNothing().when(featuredListRepository).delete((FeaturedList) any());
        when(featuredListRepository.findById((Integer) any())).thenReturn(ofResult);
        assertEquals(" Id 1 Deleted SuccessFully", featuredListServiceImpl.deleteList(1));
        verify(featuredListRepository).findById((Integer) any());
        verify(featuredListRepository).delete((FeaturedList) any());
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteList2() {
        doNothing().when(featuredListRepository).delete((FeaturedList) any());
        when(featuredListRepository.findById((Integer) any())).thenReturn(Optional.empty());
        featuredListServiceImpl.deleteList(1);
    }
}


