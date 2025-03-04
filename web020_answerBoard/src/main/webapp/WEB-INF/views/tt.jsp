<%@page import="java.util.Date"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.min.edu.dto.AnswerboardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 전체 보기</title>
</head>
<%! // com.min.edu.usebean.* useBean으로 처리하기 위해 java로 작성  %>
<jsp:useBean class="com.min.edu.usebean.DateFormatPatternBean"  id="dBean" scope="page" />
<jsp:useBean class="com.min.edu.usebean.ReplyPhotoBean" id="pBean" scope="page"></jsp:useBean>
<%
List<AnswerboardDto> lists = (List<AnswerboardDto>) request.getAttribute("lists");
// 	List<AnswerboardDto> lists = new ArrayList();
%>
<!-- 헤더 incude -->
<%@ include file="./header.jsp"%>
<body>
	<!-- 콘텐츠 영역 -->
	<div class="container" id="container">
		<form action="./multiDelete.do" method="post" onsubmit="return chkSubmit()">
			<table class="table table-hover">
				<thead>
					<tr class="info">
						<th style="text-align: center;"><input type="checkbox"
							id="chkbox" class="allCheckBox" onclick="checkAll(this.checked)"></th>
						<th>연번</th>
						<th>작성자</th>
						<th>제목</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (lists.size() == 0) {
					%>
					<tr>
						<th colspan="5"
							style="color: blue; font-size: 8px; text-align: center;">--
							작성된 글이 없습니다. --</th>
					</tr>
					<%
					} else {
					int idx = 0;
					for (AnswerboardDto dto : lists) {
					%>
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="ch" class="ch" value="<%=dto.getSeq()%>"></td>
						<td><%=lists.size() - (idx++)%></td>
						<td><%=dto.getName()%></td>
						
						<td>
							<jsp:setProperty property="depth" name="pBean" value="<%=dto.getDepth() %>"/>
							<%
							if (dto.getDelflag().equalsIgnoreCase("Y")) {
							%> <span style="font-size: 8px; color: #ccc;">관리자에 의해 삭제된
								글 입니다.</span> <%
							 } else {
							 %> 
							 	<jsp:getProperty property="replyPhoto" name="pBean"/> 
							 	<a href="./detailBoard.do?seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a> 
							 <%
							 }
							 %>

						</td>

						<td>
							<jsp:setProperty property="oldDate" name="dBean" value="<%= dto.getRegdate()%>"/>
							<jsp:getProperty property="dateFormatPattern" name="dBean"/>
						</td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="5" style="text-align:center;">
						<!-- form의 속성인 onsubmit을 통한 제어 -->
							<input class="btn btn-info" type="submit" value="다중삭제01">
						<!-- javascript의 sweetalert를 통한 callback 제어 및 submit 제어 -->
							<input class="btn btn-info" type="button" value="다중삭제02" onclick="chSubmit(event)">
							<input class="btn btn-info" type="button" value="새글작성" onclick="location.href='./writeBoard.do'">
						</th>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>

	<!-- 푸터영역 include -->
	<%@ include file="./footer.jsp"%>
</body>
</html>