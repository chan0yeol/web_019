package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.dto.UserDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModifyBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -5124008129292025705L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ModifyBoardServlet GET 글 수정화면 이동");
		String seq = req.getParameter("seq");
		
		// 수정은 자신이 쓴 글만 수정 할 수 있도록 함.
		HttpSession session = req.getSession();
		UserDto loginDto = (UserDto) session.getAttribute("loginDto");
		IAnswerboardDao dao = new AnswerboardDaoImpl();
		AnswerboardDto dto = dao.selectDetailBoard(seq);
		if(dto.getId().equals(loginDto.getId())) {
			req.setAttribute("dto", dto);
			req.getRequestDispatcher("/WEB-INF/views/modifyBoardForm.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("./loginServlet.do");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ModifyBoardServlet POST 글 수정 입력");
		String seq = req.getParameter("seq");
		String content = req.getParameter("content");
		
		Map<String, Object> map = new HashMap<>();
		map.put("seq", seq);
		map.put("content", content);
		IAnswerboardDao dao = new AnswerboardDaoImpl();
		boolean isc = dao.modifyBoard(map);
		if(isc) {
			resp.sendRedirect("./detailBoard.do?seq="+seq);
		} else {
			resp.sendRedirect("./modifyBoard.do?seq="+seq);
		}
	}
}
