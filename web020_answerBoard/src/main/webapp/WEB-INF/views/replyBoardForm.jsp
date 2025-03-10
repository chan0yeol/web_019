<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.min.edu.dto.AnswerboardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글작성 입력화면</title>
<script type="text/javascript" src="./js/reply.js" defer="defer"></script>
</head>
<%!
	public String nowDate(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		return sdf.format(d);
	}
%>
<%
	AnswerboardDto dto = (AnswerboardDto) request.getAttribute("dto");
//	
// 	UserDto loginDto = (UserDto)session.getAttribute("loginDto");
%>
<%@ include file="./header.jsp" %>
<body>
	
	<div id="container" class="container">
	<%= loginDto %>
		<form action="./replyBoard.do" method="post">
			<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
			<table class="table">
				<tbody>
					<tr>
						<th>아이디</th>
						<td class="form-group">
							<input class="form-control" name="id" value="<%=session.getId() %>" disabled="disabled" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td class="form-group">
							<input class="form-control" name="title" required="required">
						</td>
					</tr>
					<tr>
						<th id="contxt">내용<br />(원본)</th>
						<td class="form-group">
							<input type="hidden" value="y" id="checkContent">
							<input type="hidden" value="<%=dto.getContent() %>" id="hiddenContent">
							<textarea rows="5" class="form-control" name="content" id="txtArea" required="required">원본&gt;<%=dto.getContent()%></textarea>
						</td>
					</tr>
					<tr>
						<th>작성일(declaration)</th>
						<td class="form-group">
							<%= nowDate(new Date()) %>
						</td>
					</tr>
					<tr>
						<th>작성일(javascript)</th>
						<td class="form-group" id="newDate">
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="2">
							<input class="btn btn-primary btn-block" type="submit" value="답글 입력">
							<input class="btn btn-danger btn-block" type="reset" value="작성 초기화">
						</th>
					</tr>
				</tfoot>
			</table>		
		</form>
	</div>
</body>

<script type="text/javascript">
	setInterval(nowDate, 1000);
	
	function nowDate() {
		const date = new Date();
		document.getElementById("newDate").innerText = date.toLocaleString();
	}
	
</script>
</html>















