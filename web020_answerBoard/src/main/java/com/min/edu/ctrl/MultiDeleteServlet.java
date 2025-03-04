package com.min.edu.ctrl;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5459523190363799762L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MultiDeleteServlet POST 단일 및 다중 삭제 Delflag 변경");

		String[] chs = req.getParameterValues("ch");
		IAnswerboardDao dao = new AnswerboardDaoImpl();
		boolean isc = dao.multiDeleteBoard(Arrays.asList(chs));
		if(isc) {
			resp.sendRedirect("./boardList.do");
		} else {
			req.getRequestDispatcher("/WEB-INF/views/error.html").forward(req, resp);
		}
	}
}
