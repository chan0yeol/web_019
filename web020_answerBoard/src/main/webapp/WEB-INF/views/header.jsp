<%@page import="com.min.edu.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.15.3/dist/sweetalert2.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.15.3/dist/sweetalert2.all.min.js"></script>
  <script type="text/javascript" src="./js/answerboard.js"></script>
  
  <style type="text/css">
  	#container {
  		width: 800px;
  		margin:50px auto;
  		height: 72vh;
  	}
  	header {
  		background: #015939;
  		height:100px;
  		color:#000000;
  	}
  	footer {
  		background: #015939;
  		text-align:center;
  		line-height:20px;
  		padding: 20px;
  	}
  </style>
</head>
<body>
	<%
		UserDto loginDto = (UserDto)session.getAttribute("loginDto");
	%>
	<header>
		<div style="padding:30px;  ">
			<h3 style="display:inline;">
				<a href="./boardList.do">게시판 구현</a>
			</h3>
			<%
				if(loginDto == null){
					%> 
						<div style="display:inline; float:right;">
							<button class="btn btn-info" onclick="location.href='./loginServlet.do'">Sign In</button>						
						</div>
						<div>
							<button class="btn btn-primary">Sign Up</button>
						</div>
					<% 
				} else{
					%> 
						<div style="display:inline; float:right;">
							<span style="color:white;">
								아이디: <%=loginDto.getId() %>(<%=loginDto.getAuth().equalsIgnoreCase("A")?"관리자":"사용자" %>)
								<button class="btn btn-info">MyPage</button>
								<button class="btn btn-danger" onclick="location.href='./loginServlet.do'">Logout</button>
							</span>						
						</div>
					<%					
				}
			%>
		</div>
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>                        
		      </button>
		      <a class="navbar-brand" href="#">WebSiteName</a>
		    </div>
		    <div class="collapse navbar-collapse" id="myNavbar">
		      <ul class="nav navbar-nav">
		        <li><a href="#">Home</a></li>
		        <li class="dropdown">
		          <a class="dropdown-toggle" data-toggle="dropdown" href="#">회원관리 <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="#">회원정보조회</a></li>
		            <li><a href="#">신고회원</a></li>
		          </ul>
		        </li>
		        <li><a href="#">게시판</a></li>
		        <li><a href="#">글작성</a></li>
		      </ul>
		    </div>
		  </div>
		</nav>
		  
		
	</header>
</body>
</html>