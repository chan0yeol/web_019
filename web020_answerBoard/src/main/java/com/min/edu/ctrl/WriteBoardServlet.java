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

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteBoardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("WriteBoardServlet GET 새글 작성 화면이동");
		req.getRequestDispatcher("/WEB-INF/views/writerForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("WriteBoardServlet POST 새글 작성 입력");
		
		HttpSession session = req.getSession();
		UserDto loginDto = (UserDto)session.getAttribute("loginDto");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id = loginDto.getId();
		
		AnswerboardDto dto = AnswerboardDto.builder()
				.title(title)
				.content(content)
				.id(id)
				.build();
		IAnswerboardDao dao = new AnswerboardDaoImpl();
		boolean isc = dao.insertBoard(dto);
		
		if(isc) {
			resp.sendRedirect("./detailBoard.do?seq="+dto.getSeq());
		} else {
			resp.sendRedirect("./writeBoard.do");
			
		}
	}
}
