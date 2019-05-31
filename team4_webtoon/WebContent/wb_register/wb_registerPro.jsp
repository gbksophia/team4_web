<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.File" %>
<%@ page import = "java.util.*" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
    
    <%@ page import = "webtoon.list.*" %>
<%
	request.setCharacterEncoding("euc-kr");

	String path = request.getServletContext().getRealPath("save");
	
	System.out.println("path : " + path);
	int size = 10*1024*1024;
	String title = "";
	String sub_title = "";
	String gen = "";
	String tag = "";
	String week = "";
	String writer = "";
	
	
	try{
		
		MultipartRequest multi=new MultipartRequest(request,path,size,"UTF-8",new DefaultFileRenamePolicy());
		
		String filename = multi.getFilesystemName("file");
		String m_fileFullPath = path + "/" + filename;
		
		title = multi.getParameter("title");
		sub_title = multi.getParameter("sub_title");
		gen = multi.getParameter("gen");
		tag = multi.getParameter("tag");
		week = multi.getParameter("week");
		writer = multi.getParameter("writer");
		
	}catch (Exception e){
		e.printStackTrace();
	}

%>
	
	<form action = "fileCheck.jsp" method = "post">
		<input type = "hidden" name = "title" value="<%=title %>">
		<input type = "hidden" name = "sub_title" value="<%=sub_title %>">
		<input type = "hidden" name = "gen" value="<%=gen %>">
		<input type = "hidden" name = "tag" value="<%=tag %>">
		<input type = "hidden" name = "week" value="<%=week %>">
		<input type = "hidden" name = "writer" value="<%=writer %>">
		<input type = "submit">
	</form>