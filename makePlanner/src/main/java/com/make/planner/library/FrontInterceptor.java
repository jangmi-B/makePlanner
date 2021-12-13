package com.make.planner.library;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FrontInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 로그아웃 후 뒤로가기 방지
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0");
				
		String requestUrl = request.getRequestURL().toString();
		String requestUri = request.getRequestURI();
		
		log.info("[requestUrl] >>>>>>> " + requestUrl);
		log.info("[requestUri] >>>>>>> " + requestUri);
		
		return true;
	}

}
