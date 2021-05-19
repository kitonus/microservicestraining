package com.jatis.training.classroom.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jatis.training.classroom.util.SessionUtil;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class SecurityFilter extends OncePerRequestFilter {

	private final ObjectMapper json = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			if ("true".equalsIgnoreCase(System.getProperty("security.disabled", "false"))) {
				System.out.println("SECURITY DISABLED!!!");
				filterChain.doFilter(request, response);
				return;
			}
			if ("/session".equals(request.getRequestURI())) {
				filterChain.doFilter(request, response);
				return;
			}
			HttpSession httpSession = request.getSession(false);
			if (httpSession == null || "true".equals(request.getHeader("X-Anonymous-Consumer"))) {
				response.setStatus(HttpStatus.FORBIDDEN.value());
				json.writeValue(response.getWriter(), new HashMap<String, Object>() {
					private static final long serialVersionUID = 1L;
	
					{
						put("status", HttpStatus.FORBIDDEN.value());
						put("message", "Invalid session");
						put("timestamp", new Date());
						put("path", request.getRequestURI());
					}
				});
			}
			httpSession.setAttribute("username", request.getHeader("X-Consumer-Username"));
			SessionUtil.set(httpSession);
			filterChain.doFilter(request, response);
		} finally {
			SessionUtil.remove();
		}
	}

}
