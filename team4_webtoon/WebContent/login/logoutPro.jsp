<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String url = (String)session.getAttribute("se_url");
	session.invalidate();
	response.sendRedirect(url);
	//response.sendRedirect("../main_page/MainPage.jsp");
%>
</body>
</html>