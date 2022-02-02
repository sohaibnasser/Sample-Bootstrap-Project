package com.cs.ecom.conf;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cs.ecom.model.helping.RestResponse;
import com.cs.ecom.utils.AppConstants;
import com.google.gson.Gson;

@Component
@Order(1)
public class AppRequestFilter implements Filter {

	Logger log = LoggerFactory.getLogger(AppRequestFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("filter Initialization");
		System.out.println("Filter Init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest) {
			HttpServletRequest servReq = (HttpServletRequest) request;
			this.setHeaders((HttpServletResponse) response, servReq);
			String url = servReq.getRequestURL().toString();
			if (url != null
					&& (url.contains("login") || url.contains("swagger") || url.contains(AppConstants.BYPASS_API_PREFIX) || url.contains("api-docs") || url.contains("Signup") || !url.contains(AppConstants.REST_API_PREFIX))) {
				chain.doFilter(request, response);
			} else {
				HttpSession session = servReq.getSession(false);
				if(session!=null) {
					//System.out.println(session.getId());
					RestResponse rr = (RestResponse) session.getAttribute(AppConstants.SESSION_ATTR_NAME_APPLICANT);
					if (rr != null && rr.getResult() != null) {
						chain.doFilter(request, response);
					} else {
						RestResponse resp = new RestResponse(AppConstants.RESPONSE_TEXT_UNAUTHORIZED, AppConstants.RESPONSE_CODE_UNAUTHORIZED,
								AppConstants.RESPONSE_TEXT_UNAUTHORIZED, null);
						Gson gson = new Gson();
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(gson.toJson(resp));
					}
				}else {
					System.out.println("no session found for: "+url);
					RestResponse resp = new RestResponse(AppConstants.RESPONSE_TEXT_UNAUTHORIZED, AppConstants.RESPONSE_CODE_UNAUTHORIZED,
							AppConstants.RESPONSE_TEXT_UNAUTHORIZED, null);
					Gson gson = new Gson();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(gson.toJson(resp));
				}
			}
		}

	}
	
	
	private void setHeaders(HttpServletResponse response, HttpServletRequest request) {
		
		
//        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
//        boolean firstHeader = true;
//        for (String header : headers) { // there can be multiple Set-Cookie attributes
//            if (firstHeader) {
//                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
//                firstHeader = false;
//                continue;
//            }
//            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
//        }
		String origin = request.getHeader("Origin");
        response.addHeader("Access-Control-Allow-Origin", origin);
		response.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, X-Auth-Token, Content-Type");
		response.setHeader("Access-Control-Allow-Credentials", "true");
    }

	@Override
	public void destroy() {
		log.info("filter Destruction");
	}
}