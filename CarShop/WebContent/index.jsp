<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/effects.js"></script>     
	<title>BMW</title>
</head>
<body>
	<%@ include file="WEB-INF/site-parts/logo.jsp" %> <!-- Logo -->
	
	<div class="content">
		<%@ include file="WEB-INF/site-parts/usernavi.jsp" %> <!-- User navigation -->
		
		<h1 class="content-happytitle">
			Niedościgniona radość z jazdy.
		</h1>
	
		<div class="content-message">
			<c:choose>
				<c:when test="${requestScope.userLogoutMsg == 'LOGGED_OUT'}">
					<p style="color: red;">Zostałeś wylogowany.</p><br/>
				</c:when>
			</c:choose>
		</div>
	
		<div class="content-series">
			<c:forEach var="i" begin="1" end="7">
				<a href="Series${i}">
					<div class="content-series-atom">
						<img src="images/bmw_series/${i}/bmw_${i}_logo.jpg"><br/>
						<p class="content-series-atom-title">BMW Seria ${i}</p>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>
	
	<%@ include file="WEB-INF/site-parts/footer.jsp" %> <!-- Footer -->

</body>
</html>