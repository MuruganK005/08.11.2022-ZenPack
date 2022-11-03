package com.ZenPack.interceptor;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {JWTInterceptor.class})
@ExtendWith(SpringExtension.class)
class JWTInterceptorTest {
    @Autowired
    private JWTInterceptor jWTInterceptor;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Test
    @Disabled("TODO: Complete this test")
    void testPreHandle() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        jWTInterceptor.preHandle(request, new Response(), "Handler");
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testPreHandle2() throws Exception {
        jWTInterceptor.preHandle(null, new Response(), "Handler");
    }
    @Test
    void testPreHandle3() throws Exception {
        when(jwtTokenUtil.validateToken((String) any())).thenReturn(true);
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        assertTrue(jWTInterceptor.preHandle(defaultMultipartHttpServletRequest, new Response(), "Handler"));
        verify(jwtTokenUtil).validateToken((String) any());
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getMethod();
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testPreHandle4() throws Exception {
        when(jwtTokenUtil.validateToken((String) any())).thenReturn(false);
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        jWTInterceptor.preHandle(defaultMultipartHttpServletRequest, new Response(), "Handler");
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testPreHandle5() throws Exception {
        when(jwtTokenUtil.validateToken((String) any())).thenReturn(null);
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        jWTInterceptor.preHandle(defaultMultipartHttpServletRequest, new Response(), "Handler");
    }
    @Test
    void testPreHandle6() throws Exception {
        when(jwtTokenUtil.validateToken((String) any())).thenReturn(true);
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("OPTIONS");
        assertTrue(jWTInterceptor.preHandle(defaultMultipartHttpServletRequest, new Response(), "Handler"));
        verify(defaultMultipartHttpServletRequest).getMethod();
    }
    @Test
    void testPreHandle7() throws Exception {
        when(jwtTokenUtil.validateToken((String) any())).thenReturn(false);
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        assertFalse(jWTInterceptor.preHandle(defaultMultipartHttpServletRequest, mockHttpServletResponse, "Handler"));
        verify(jwtTokenUtil).validateToken((String) any());
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getMethod();
        assertEquals(401, mockHttpServletResponse.getStatus());
    }
    @Test
    void testPreHandle8() throws Exception {
        when(jwtTokenUtil.validateToken((String) any())).thenReturn(false);
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        mockHttpServletResponse.setCommitted(true);
        assertFalse(jWTInterceptor.preHandle(defaultMultipartHttpServletRequest, mockHttpServletResponse, "Handler"));
        verify(jwtTokenUtil).validateToken((String) any());
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getMethod();
    }
}

