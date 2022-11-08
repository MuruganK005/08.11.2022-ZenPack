/*
package com.ZenPack.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@Component
public class JWTInterceptor implements AsyncHandlerInterceptor {

	@Autowired
	private JwtTokenUtil tokenUtil;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
		    return true;
		}
    	String token = request.getHeader(JwtTokenUtil.HEADER_STRING);
        if (token != null && tokenUtil.validateToken(token)) {
            return true;
        }
        response.setStatus(401);
        return false;
    }

}
*/
