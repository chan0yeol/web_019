<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글 작성 화면</title>
<script type="text/javascript" src="./js/writeForm.js"></script>
</head>
<%@ include file="./header.jsp" %>
<body>
	<div id="container" class="container">
		<div><a class="btn btn-block btn-primary" href="javascript:history.back(-1)">뒤로가기</a></div>
		<form action="./writeBoard.do" class="form-horizontal" method="POST">
			<table class="table">
				<tbody>
					<tr class="form-group">
						<td>
							<label class="control-label" for="id">아이디 : </label>
						</td>
						<td>
							<input class="form-control" id="id" name="id"  readonly="readonly" value="<%=loginDto.getId()%>">
						</td>
					</tr>
					<tr class="form-group">
						<td>
							<label class="control-label" for="title ">제목 : </label>
						</td>
						<td>
							<input class="form-control" id="title" name="title" >
						</td>
					</tr>
					<tr class="form-group">
						<td>
							<label class="control-label" for="content ">내용 : </label>
						</td>
						<td>
							<textarea class="form-control" id="content" name="content" ></textarea>
						</td>
					</tr>
					<tr class="form-group">
						<td>
							<label class="control-label" for="content ">내용 : </label>
						</td>
						<td>
							<div class="" id="contentView">
							
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr class="form-group">
						<td colspan="2" style="text-align:center;">
							<input class="btn btn-info active" type="button" value="새글입력" onclick="validateForm()">
							<input class="btn btn-danger active" type="reset" value="다시입력">
						 </td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
<%@ include file="./footer.jsp" %>
</body>
</html>