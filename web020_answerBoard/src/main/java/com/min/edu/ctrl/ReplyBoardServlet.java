package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.dto.UserDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;
//import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplyBoardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ReplyBoardServlet GET 답글달기 화면 이동");
		String seq = req.getParameter("seq");
		
		IAnswerboardDao dao = new AnswerboardDaoImpl();
		AnswerboardDto dto =  dao.selectDetailBoard(seq);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/WEB-INF/views/replyBoardForm.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ReplyBoardServlet POST");
		String seq = req.getParameter("seq");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		HttpSession session = req.getSession();
		UserDto loginDto = (UserDto) session.getAttribute("loginDto");
		String id = loginDto.getId();
		
		AnswerboardDto inDto = AnswerboardDto
				.builder()
				.id(id)
				.seq(Integer.parseInt(seq))
				.title(title)
				.content(content)
				.build();
		IAnswerboardDao dao = new AnswerboardDaoImpl();
		boolean isc = dao.reply(inDto);
		if(isc) {
			resp.sendRedirect("./boardList.do");
		}else {
//			Utility.servlet_alert(resp, "답글 작성 실패", "./replyBoard.do?seq="+seq);
		}
	}
}
