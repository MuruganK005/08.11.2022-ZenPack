package com.ZenPack;

import com.ZenPack.interceptor.JWTInterceptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CorsConfig.class})
@ExtendWith(SpringExtension.class)
class CorsConfigTest {
    @Autowired
    private CorsConfig corsConfig;

    @MockBean
    private JWTInterceptor jWTInterceptor;

    @Test
    void testAddInterceptors() {
        this.corsConfig.addInterceptors(new InterceptorRegistry());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddInterceptors2() {
        this.corsConfig.addInterceptors(null);
    }

    @Test
    void testAddInterceptors3() {
        InterceptorRegistry interceptorRegistry = mock(InterceptorRegistry.class);
        when(interceptorRegistry.addInterceptor((org.springframework.web.servlet.HandlerInterceptor) any()))
                .thenReturn(new InterceptorRegistration(new JWTInterceptor()));
        this.corsConfig.addInterceptors(interceptorRegistry);
        verify(interceptorRegistry).addInterceptor((org.springframework.web.servlet.HandlerInterceptor) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddInterceptors4() {
        InterceptorRegistry interceptorRegistry = mock(InterceptorRegistry.class);
        when(interceptorRegistry.addInterceptor((org.springframework.web.servlet.HandlerInterceptor) any()))
                .thenReturn(null);
        this.corsConfig.addInterceptors(interceptorRegistry);
    }

    @Test
    void testAddCorsMappings() {
        this.corsConfig.addCorsMappings(new CorsRegistry());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddCorsMappings2() {
        this.corsConfig.addCorsMappings(null);
    }

    @Test
    void testAddCorsMappings3() {
        CorsRegistry corsRegistry = mock(CorsRegistry.class);
        when(corsRegistry.addMapping((String) any())).thenReturn(new CorsRegistration("Path Pattern"));
        this.corsConfig.addCorsMappings(corsRegistry);
        verify(corsRegistry).addMapping((String) any());
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testAddCorsMappings4() {
        CorsRegistry corsRegistry = mock(CorsRegistry.class);
        when(corsRegistry.addMapping((String) any())).thenReturn(null);
        this.corsConfig.addCorsMappings(corsRegistry);
    }
}

