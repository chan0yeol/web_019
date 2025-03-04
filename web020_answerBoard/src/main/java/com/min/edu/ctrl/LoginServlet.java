package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.UserDto;
import com.min.edu.model.IUserDao;
import com.min.edu.model.UserDaoImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3828164570372890792L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("LoginServlet GET 요청");
		HttpSession session = req.getSession();
		session.invalidate();
//		req.getRequestDispatcher("/WEB-INF/views/loginForm.jsp").forward(req, resp);
		req.getRequestDispatcher("/WEB-INF/views/loginForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pw = req.getParameter("pw");
		log.info("전달 받은 요청 값{} {}",user, pw);
		
		IUserDao dao = new UserDaoImpl();
		Map<String, Object> map = new HashMap<>();
		map.put("id", user);
		map.put("password", pw);
		UserDto loginDto = dao.login(map);
//		log.info("로그인된 정보 {}", loginDto.getName());
		
		if(loginDto == null) {
//			Utility.servlet_alert(resp, "회원정보가 없습니다", "loginServlet.do");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("loginDto", loginDto);
			
			resp.sendRedirect("./boardList.do");
		}
	}
	
}








