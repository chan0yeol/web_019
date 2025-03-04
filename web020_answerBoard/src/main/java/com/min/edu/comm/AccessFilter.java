package com.min.edu.comm;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.UserDto;

public class AccessFilter implements Filter {

	private List<String> excludeUrl;
	
	@Override
	public void destroy() {
		System.out.println("AccessFilter 삭제");
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("AccessFilter 생성");
		String excludePattern = filterConfig.getInitParameter("excludeURL");
		excludeUrl = Arrays.asList(excludePattern.split(","));
		System.out.println("제외 요청 주소 : " + excludeUrl);
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		UserDto loginDto = (UserDto) session.getAttribute("loginDto");
		
		// 제외되는 URL을 확인 후 세션 확인
		
		if (!excludeUrl.contains(req.getServletPath())) {
			if (loginDto == null) {
				req.getRequestDispatcher("/WEB-INF/views/loginForm.jsp").forward(request, response);
			} else {
				chain.doFilter(request, response);
			} 
		} else {
			chain.doFilter(request, response);
		}
		
	}

}
