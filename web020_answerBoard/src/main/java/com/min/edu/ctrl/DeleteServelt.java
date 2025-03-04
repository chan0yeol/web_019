package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.UserDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;

public class DeleteServelt extends HttpServlet {

	private static final long serialVersionUID = -8875655887633570689L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");
		HttpSession session = req.getSession();
		UserDto loginDto = (UserDto)session.getAttribute("loginDto");
		
		if(!loginDto.getAuth().equalsIgnoreCase("A")) {
//			Utility.servlet_alert(resp, "삭제권한 없음", "loginServlet.do");
		} else {
			IAnswerboardDao dao = new AnswerboardDaoImpl();
			
			if(dao.deleteBoard(seq)) {
				resp.sendRedirect("./boardList.do");
			} else {
				resp.sendRedirect("./detailBoard.do?seq="+seq);
			}// if end
		} // else end
	}
	
}
