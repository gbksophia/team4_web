<%@ page import = "java.util.ArrayList" %>
<%@ page import = "team4_webtoon.registerDAO" %>
<%@ page import = "team4_webtoon.registerBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>

<%
/*
전체적인 로직

정보 수정을 위해 비밀번호를 입력받는 메서드
*/
%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
</script>
<style>
	.label1{
		width:150px;
		margin-bottom:60px;
	}
	.input1{
		margin-right:20px;
	}
	
	.input2{
		margin-right:20px;
		margin-left:20px;
	}	
</style>
</head>

<body>
<%@include file="../bar/menu.jsp" %>
<%@include file="../bar/navigationBar.jsp" %>
<div class="container" style="margin-top:100px; margin-bottom : 130px;">
  	<br>
  	<h1 class="my-4" style="text-align: center"><%=session.getAttribute("sessionID") %></h1>
  	<h5 style = "text-align : center">님의 정보 수정</h5>
  	<form action = "adjust.jsp" method="post" name = "userinput" style = "margin-top : 100px;">
		<div>
			<label class = "label1">비밀번호 입력</label>
			<input type = "password" name = "pw" required autofocus style = "margin-bottom : 150px;">
		</div>
		<button class="btn btn-lg btn-warning btn-block text-uppercase" value ="submit">확인</button>
	</form>
	<br>
		<button class="btn btn-lg btn-warning btn-block text-uppercase" onclick="location='../main_page/MainPage.jsp'">돌아가기</button>
</div>
<%@ include file="../bar/footer.jsp"%>
<!-- /.container -->
</body>
</html>