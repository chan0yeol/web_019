package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DetailBoardServlet extends HttpServlet {
	private static final long serialVersionUID = -3818823526637674461L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info(" DetailBoardServlet GET 글상세");
		IAnswerboardDao dao = new AnswerboardDaoImpl();
		String seq = req.getParameter("seq");
		AnswerboardDto dto = dao.selectDetailBoard(seq);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/WEB-INF/views/detailBoard.jsp").forward(req, resp);
	}
}
