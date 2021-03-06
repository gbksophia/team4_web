<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "team4_webtoon.*"%>
<%request.setCharacterEncoding("UTF-8"); %>
	<jsp:useBean id="member" class="team4_webtoon.registerBean"/>
	<jsp:setProperty property="*" name="member"/>
<%
	String id1 = (String)session.getAttribute("sessionID");
	member.setId(id1);
	String pw = request.getParameter("pw");
	
	registerDAO manager1 = registerDAO.getInstance();
	manager1.changeAge(member);	
	
	/*
	전체적인 로직
	
	나이 변경 메서드
	*/
%>

<!DOCTYPE html>
<html>
<head>

<title>회원 정보 수정</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
</script>
<%@include file="../bar/menu.jsp" %>
<%@include file="../bar/navigationBar.jsp" %>
<script type="text/javascript">
</script>
</head>

<body>
<div class="container" style = "margin-top : 100px;">
  	<br>
  	<h1 class="my-4" style="text-align: center">나이가 변경되었습니다.</h1>
  	<h5 style = "text-align : center">메인페이지로 돌아갑니다.</h5>
  	<input type = "hidden" name = "id1" value = "<%=id1 %>">
  	<input type = "hidden" name = "pw" value = "<%=pw %>">
  	<meta http-equiv="Refresh" content="1;url=adjustForm.jsp" >
  	
</div>
<!-- /.container -->
</body>
</html>	
