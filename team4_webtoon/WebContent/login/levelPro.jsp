<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ page import = "team4_webtoon.*" %>
	<jsp:useBean id="member" class="team4_webtoon.registerBean">
	<jsp:setProperty property="*" name="member"/>
	</jsp:useBean>
	<%
		String id1 = (String)session.getAttribute("sessionID");
		member.setId(id1);
		member.setState(2);

		registerDAO dao = registerDAO.getInstance();

		dao.updateMember(member);
		
    	/*
    	전체적인 로직
    	
    	휴면 계정 변경 페이지
    	
    	*/
	%>
<!DOCTYPE html>
<html>
<head>
<title>휴면 회원 변경</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
</script>
<%@include file="../bar/menu.jsp" %>
<%@include file="../bar/navigationBar.jsp" %>
</head>
<body>

<div class="container">
  	<br>
  	<h1 class="my-4" style="text-align: center">휴면 계정이 해제되었습니다.</h1>
  	<h5 style = "text-align : center">메인페이지로 돌아갑니다.</h5>
  	<meta http-equiv="Refresh" content="1;url=../main_page/MainPage.jsp" >
</div>
<!-- /.container -->
</body>
</html>	