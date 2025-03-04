<%@page import="com.min.edu.dto.AnswerboardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<%
	AnswerboardDto dto = (AnswerboardDto)request.getAttribute("dto");
	
%>
<%@ include file="./header.jsp" %>
<body>
	<div id="container" class="container">
		<div>parameter의 전송범위는 다음 페이지 까지 : </div>
		<form action="./modifyBoard.do" method="post">
			<input type="hidden" name="seq" value="<%=request.getParameter("seq")%>">
			<table class="table">
				<tr>
					<th class="primary">아이디</th>
					<td><%=dto.getId() %></td>
				</tr>
				<tr>
					<th class="primary">제목</th>
					<td><%=dto.getTitle() %></td>
				</tr>
				<tr>
					<th class="primary">내용</th>
					<td>
						<textarea rows="5" class="form-control" name="content" id="content"><%=dto.getContent()%></textarea>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align:center;">
						<input class="btn" type="submit" value="수정">
						<input class="btn" type="button" value="뒤로가기" onclick="javascript:history.back(-1)">
					</th>
				</tr>
			</table>
		</form>
	</div>


<%@ include file="./footer.jsp" %>	
</body>
</html>
 