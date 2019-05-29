    <%@ page import = "search.SearchDAO"%>
    <%@ page import = "search.SearchVO" %>
    <%@ page import = "java.util.ArrayList" %>
    <%@ page import = "webtoon.list.*" %>
    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
	String search = request.getParameter("addr") == null ? "null" : request.getParameter("addr");
	String select = request.getParameter("select") == null ? "" : request.getParameter("select"); 
	SearchDAO dao = new SearchDAO();
	ArrayList<SearchVO> list = dao.getAddrs(search,select);
	
	//이미지 가져오기
	String title = request.getParameter("title");
	//select box 가져오기


	
%>
<%@include file="/menu.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>SEARCH</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</head>
<body>

	<!-- Page Content -->
<div class="container">

  <!-- Page Heading -->
  <%if (search == "null") {%>
  	<br>
  	<h1 class="my-4" style="text-align: center">검색어를 입력해주세요</h1>
  	<br><br><br>
	<form name = "out" method = "get" action="search.jsp">
	<ASIDE style="text-align: center">
	<select name = "select">
		<option value = "0">제목 </option>
		<option value = "1">작가 </option>
	</select>
	<input type='text' name="addr" placeholder="내용을 입력하세요"/><input type='submit' value = "검색">
       </ASIDE>
       <br><br><br>
	</form>
	<h3>추천 키워드</h3><br>
	<div class = "row">
		<div class = "col-md-6">
			<table class = "table table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>first</th>
						<th>second</th>
						<th>second</th>
						<th>second</th>
						</tr>
						</thead>
				<tbody>
					<tr>
						<td>mark</td>
						<td>asd</td>
						<td>a</td>
						<td>a</td>
						<td>a</td>
				</tbody>
								<tbody>
					<tr>
						<td>mark</td>
						<td>asd</td>
						<td>a</td>
						<td>a</td>
						<td>a</td>
				</tbody>
						
			</table>
		</div>
	</div>
	<br>
	
	<h3>추천 장르</h3><br>
	<div class = "row">
		<div class = "col-md-6">
			<table class = "table table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>first</th>
						<th>second</th>
						<th>second</th>
						<th>second</th>
						<th>second</th>
						<th>second</th>
						<th>second</th>
						
						</tr>
						</thead>
				<tbody>
					<tr>
						<td>mark</td>
						<td>asd</td>
						<td>a</td>
				</tbody>
										<tbody>
					<tr>
						<td>mark</td>
						<td>asd</td>
						<td>a</td>
						<td>a</td>
						<td>a</td>
				</tbody>
			</table>
		</div>
	</div>

  <%} else if (search != "null"){%>
  <h1 class="my-4"><%=request.getParameter("addr") %>
    <small>의 검색 결과입니다.</small>
  </h1>
  
   <FORM name='frm' method='GET' action="search.jsp">
    <ASIDE style='float: right;'>
	<select name = "select">
		<option value = "0">제목 </option>
		<option value = "1">작가 </option>
	</select>
       <input type='text' name="addr" placeholder="내용을 입력하세요"/><input type='submit' value = "검색">
       </ASIDE>
       <br>
       <br>
           	<div class = "row">
    	<%for(int i = 0; i < list.size(); i++){%>


    <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
      <div class="card h-100">
        <a href="#"><img class="card-img-top" src="/team4_webtoon/resources/image/webtoon/<%=list.get(i).getTitle() %>/<%=list.get(i).getTitle() %>_som.jpg" alt=""></a>
        <div class="card-body">
          <h4 class="card-title">
			 <a href="#"><%=list.get(i).getTitle() %></a>
          </h4>
          <p class="card-text"><%=list.get(i).getWriter() %></p>
          <p class="card-text"><%=list.get(i).getGen() %></p>
          <p class="card-text"><%=list.get(i).getTag() %></p>
        </div>

      </div>
    </div>

    <%}%>   

    </div>


  </FORM>
  
<%} %>
 

  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>

  <!-- /.row -->

  <!-- Pagination -->
  <!-- 
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#">1</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#">2</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#">3</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
    </li>
  </ul>
 -->

<%@ include file="footer.jsp"%>
<!-- /.container -->
</body>
</html>