<%@page import="com.min.edu.dto.AnswerboardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
</head>
	<%
		AnswerboardDto dto = (AnswerboardDto) request.getAttribute("dto"); 
	%> 
<%@ include file="./header.jsp" %>
<body>
	<div id="container" class="container">
		<table class="table table-contensed">
			<col width="20%">
			<col width="70%">
			<tbody>
				<tr>
					<th class="info">아이디</th>
					<td><%= dto.getId() %></td>
				</tr>
				
				<tr>
					<th class="info">제목</th>
					<td><%= dto.getTitle()%></td>
				</tr>
				
				<tr>
					<th class="info">내용</th>
					<td><%= dto.getContent() %></td>
				</tr>
				
				<tr>
					<th class="info">내용2</th>
					<td>
						<textarea rows="5" cols="70" readonly="readonly"><%=dto.getContent()%></textarea>
					</td>
				</tr>
				
				<tr>
					<th class="info">등록일</th>
					<td><%=dto.getRegdate() %></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="2">
						<form class="btn-group btn-group-justified">
							<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
							<% // 관리자 권한이라면 DB 삭제가 될 수 있도록 삭제 버튼
								if(loginDto.getAuth().equalsIgnoreCase("A")){
									%>
									<div class="btn-group">
										<button class="btn btn-danger" onclick="del(event)">찐 삭제</button>
									</div>
									<%
								}
							%>
							
							<% // 작성된 글과 현재 로그인된 사용자가 같다면 수정 버튼
								if(loginDto.getId().equals(dto.getId())){
									%>
									<div class="btn-group">
										<button class="btn btn-info" onclick="modify(event)">내글수정</button>
									</div>
									<%
								}
							%>
							<!--  모든 사용자의 답글 버튼 -->
							<div class="btn-group">
								<button class="btn btn-primary" onclick="reply(event)">답글 작성</button>
							</div>
						</form>
					</th>
				</tr>
			</tfoot>
		</table>
	</div>
<%@ include file="./footer.jsp" %>
</body>
</html>