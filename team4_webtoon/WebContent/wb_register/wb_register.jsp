    <%@ page import = "search.SearchDAO"%>
    <%@ page import = "search.SearchVO" %>
    <%@ page import = "java.util.ArrayList" %>
    <%@ page import = "webtoon.list.*" %>
<%request.setCharacterEncoding("UTF-8"); %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>작품 등록</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<style>
	table, th, td{
		border : 1px solid #bcbcbc;
	}
	table{
		width : 100%;
		height : 150px;
	}
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

	<!-- Page Content -->
<div class="container" style = "margin-top : 150px; margin-bottom : 100px;">

  <!-- Page Heading -->

  	<h1 class="my-4" style="text-align: center">작품을 등록하세요</h1>

  	<form action = "wb_registerPro.jsp" method="post" enctype = "multipart/form-data">
		<div style = "margin-top : 100px;">
				<label class = "label1">작품 제목 </label>
				<input type="text" size="100" maxlength="30" name="title" placeholder="  제목을 입력해주세요" class = "1" required> 
				
				<label class = "label1">소제목 </label>
				<input type="text" size="100" maxlength="30" name="sub_title" placeholder="  소제목을 입력해주세요" class = "1" required>
				 
				<label style = "width : 150px;">장르 </label>
				<input type = "radio" name = "gen" value = "0"checked class = "input1" required>일상
				<input type = "radio" name = "gen" value = "1" class = "input2" required>개그
				<input type = "radio" name = "gen" value = "2" class = "input2" required>판타지
				<input type = "radio" name = "gen" value = "3" class = "input2" required>액션
				<input type = "radio" name = "gen" value = "4" class = "input2" required>드라마<br><br>
				<label style = "width: 150px;"> </label>
				<input type = "radio" name = "gen" value = "5" class = "input1" required>순정
				<input type = "radio" name = "gen" value = "6" class = "input2" required>감성
				<input type = "radio" name = "gen" value = "7" class = "input2" required>스릴러
				<input type = "radio" name = "gen" value = "8" class = "input2" required>시대극
				<input type = "radio" name = "gen" value = "9" class = "input2" required>스포츠<br><br><br>
				
				<label class = "label1">태그</label>			
				<input type="text" size="100" maxlength="30" name="tag" placeholder="  태그를 입력해주세요" style = "margin-bottom: 60px;" required> 
				
				<label class = "label1">연재요일 </label>
				<input type = "radio" name = "week" value = "1" checked class = "input1" required>월요일
				<input type = "radio" name = "week" value = "2" class = "input2" required>화요일
				<input type = "radio" name = "week" value = "3" class = "input2" required>수요일
				<input type = "radio" name = "week" value = "4" class = "input2" required>목요일
				<input type = "radio" name = "week" value = "5" class = "input2" required>금요일
				<input type = "radio" name = "week" value = "6" class = "input2" required>토요일
				<input type = "radio" name = "week" value = "0" class = "input2" required>일요일
				<input type = "radio" name = "week" value = "7" class = "input2" required>도전만화

				<label class = "label1">줄거리 </label>
				<input type="text" size="100" maxlength="30" name="sum" placeholder="줄거리를 입력하세요" style = "margin-bottom: 60px" required>
				
				<label class = "label1">썸네일 이미지 </label>크기 (500 x 325)에 맞게 올려주세요 (jpg 파일) 또한 작품 제목_som.jpg 형식을 지켜주세요<br>
				<input type = "file" name = "thum_file" style = "margin-bottom: 60px;"> <br>
				
				<label class = "label1">추천 페이지용 썸네일 이미지 </label>크기 (750 x 300)에 맞게 올려주세요 (jpg 파일) 또한 작품 제목_recom.jpg 형식을 지켜주세요<br>
				<input type = "file" name = "recom_file" style = "margin-bottom: 60px;"> <br>
				
				<b>내용을 확인하셨습니까?</b>
				<input type = "radio" name = "writer" value = "<%=session.getAttribute("sessionID") %>" class = "input2" style = "margin-bottom:50px;" required>확인
	
</div>

<button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">작품 등록</button><br>

<button class="btn btn-lg btn-primary btn-block text-uppercase" type="reset">수정하기</button>
</form>
</div>



<%@ include file="../bar/footer.jsp"%>
<!-- /.container -->
</body>
</html>