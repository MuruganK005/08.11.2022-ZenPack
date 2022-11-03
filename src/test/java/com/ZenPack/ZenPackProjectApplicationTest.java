package com.ZenPack;

import org.apache.zookeeper.KeeperException;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.internal.InheritingConfiguration;
import org.modelmapper.internal.TypeResolvingList;
import org.modelmapper.spi.NameTokenizer;
import org.modelmapper.spi.ValueReader;
import org.modelmapper.spi.ValueWriter;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZenPackProjectApplicationTest {

    @Test
    void testProductApi() {
        Docket actualProductApiResult = (new ZenPackProjectApplication()).productApi();
        assertTrue(actualProductApiResult.isEnabled());
        assertEquals("default", actualProductApiResult.getGroupName());
    }

    @Test
    void testModelMapper() {
        ModelMapper actualModelMapperResult = (new ZenPackProjectApplication()).modelMapper();
        assertTrue(actualModelMapperResult.getTypeMaps().isEmpty());
        Configuration configuration = actualModelMapperResult.getConfiguration();
        assertEquals(11, configuration.getConverters().size());
        assertNull(configuration.getPropertyCondition());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertTrue(valueWriters instanceof TypeResolvingList);
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertTrue(valueReaders instanceof TypeResolvingList);
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
    }

    @Test
    void testDoSomethingAfterStartup() throws IOException, InterruptedException, KeeperException {

        (new ZenPackProjectApplication()).doSomethingAfterStartup();
    }


    @Test
    void testPrint() {

        (new ZenPackProjectApplication()).print();
    }
}

