
package com.digitalgis.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

	public CORSFilter() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String validOrigin1 = "http://localhost"; // Stagin System
		String validOrigin2 = "https://apagri.infinium.management"; // Local System
		String validOrigin3 = "https://apagri.infinium.management"; // Live SERVER  
		String validOrigin4 = "https://apagri.infinium.management";
		
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", validOrigin1);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"x-requested-with, Authorization, x-auth-token, origin, Content-Type, Accept,Referer");

		String requestURL = request.getRequestURL().toString();
		String origin = request.getHeader("Origin");
		String referer = request.getHeader("Referer");

		System.out.println("Origin from Request:" + origin);
		System.out.println("Origin from Referer:" + referer);
		
		if(origin != null){
			if(origin.contains(validOrigin1)){
				response.setHeader("Access-Control-Allow-Origin", validOrigin1);
			}else if(origin.contains(validOrigin2)){
				response.setHeader("Access-Control-Allow-Origin", validOrigin2);
			}else if(origin.contains(validOrigin3)){
				response.setHeader("Access-Control-Allow-Origin", validOrigin3);
			}else if(origin.contains(validOrigin3)){
				response.setHeader("Access-Control-Allow-Origin", validOrigin4);
			}else {
				response.setHeader("Access-Control-Allow-Origin", validOrigin1);
			}
		}
		
		
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
			
			/*
			if(origin==null && referer==null) {
				chain.doFilter(req, res);
			}
			if (origin != null && validOrigin.trim().equalsIgnoreCase(origin.trim())) {
				chain.doFilter(req, res);
			} else if (referer != null && referer.contains(validOrigin)) {
				chain.doFilter(req, res);
			} else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}*/
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

	public boolean checkRequestURL(String requestURL, String validOrigin) {
		return false;
	}

}