<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>웹툰 뷰어 navbar</title>
 <head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Bootstrap core CSS -->
  <link href="/team4_webtoon/resources/Main_page/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="/team4_webtoon/resources/Main_page/css/shop-homepage.css" rel="stylesheet">
</head>

<script language="JavaScript">
    // 별정 등록 여부를 판단
    function openConfirmstar(starForm) {
    	//if(session.getAttribute("sessionID") != null){
            if (starForm.starSelect.value != "") {
                alert("별점이 등록되었습니다.");
                document.starForm.submit();
                document.getElementbyId("yesS").style.display="block";
                document.getElementbyId("starSelect").style.display="none";
                return;
             }
        /* }else{
                response.sendRedirect("/team4_webtoon/login/login.jsp"); 
         }  */ 
    }

</script>

<% 
	int sMN=100;//Integer.parseInt(request.getParameter("1"));
	int sCN=6;//Integer.parseInt(request.getParameter("2"));


	
%>

<body>

	<form name="starForm"  method="post" action="starPro.jsp">
	<input type="hidden" name="mw_num" value="<%= sMN%>" />
	<input type="hidden" name="cl_num" value="<%=sCN %>" />
		<select id="starSelect"  name="starSelect">
			<option value=5 selected>
				<%for(int j=5;j >0;j--){%>
					<small class="text-muted">&#9733;</small>
				<%}%>
			</option>
			<option value=4 >			
				<%for(int j=4;j >0;j--){%>
					<small class="text-muted">&#9733;</small>
				<%}%>
				<%for(int i=0;i<1;i++){ %>
					<small class="text-muted">&#9734;</small>
				<%} %>
			</option>
			<option value=3 >
				<%for(int j=3;j >0;j--){%>
					<small class="text-muted">&#9733;</small>
				<%}%>
				<%for(int i=0;i<2;i++){ %>
					<small class="text-muted">&#9734;</small>
				<%} %>
			</option>	
			<option value=2 >
				<%for(int j=2;j >0;j--){%>
					<small class="text-muted">&#9733;</small>
				<%}%>
				<%for(int i=0;i<3;i++){ %>
					<small class="text-muted">&#9734;</small>
				<%} %>
			</option>	
			<option value=1 >
				<%for(int j=1;j >0;j--){%>
					<small class="text-muted">&#9733;</small>
				<%}%>
				<%for(int i=0;i<4;i++){ %>
					<small class="text-muted">&#9734;</small>
				<%} %>
			</option>		
		</select>
		<span name="yesS" id="yesS" style="display:none">참여하셨습니다</span>
		<%-- <input type="submit" name="confirm_star" value="확인" >--%> 
		<input type="button" name="confirm_star" value="확인" 
        							OnClick="openConfirmstar(this.form)">
	</form>
	  <!-- Bootstrap core JavaScript -->
  <script src="/team4_webtoon/resources/Main_page/vendor/jquery/jquery.min.js"></script>
  <script src="/team4_webtoon/resources/Main_page/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>