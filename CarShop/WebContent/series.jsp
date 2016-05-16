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
			BMW Seria ${requestScope.bmwSeries}
		</h1>
		
		<div class="content-message">
			<c:choose>
				<c:when test="${requestScope.deleteCarMsg == 'CAN_NOT_DELETE'}">
					<p style="color: red;">Nie można usunąć samochodu.</p><br/>
				</c:when>
				<c:when test="${requestScope.deleteCarMsg == 'DELETED'}">
					<p style="color: green;">Samochód został usunięty.</p><br/>
				</c:when>
			</c:choose>
		</div>
		
		<c:forEach items="${requestScope.bmwCars}" var="current">
			<div class="content-car">
				<div class="content-car-image">
					<img class="content-car-image-source" src="images/bmw_series/${requestScope.bmwSeries}/${current.sourceImage}"/>
				</div>
				<div class="content-car-info">
					<p>Model: <c:out value="${current.model}"/></p>
					<p>Kolor: <c:out value="${current.color}"/></p>
					<p>Cena: <c:out value="${current.price}"/> PLN</p>
				</div>
				<div class="content-car-navi">
					<form method="POST" action="Series${requestScope.bmwSeries}">
						<input type="hidden" name="carID" value="${current.carID}"/>
						<input type="submit" name="buyCarSubmit" value="Kup" class="styled-button-8"/><br/><br/>
						<input type="submit" name="deleteCarSubmit" value="Usuń" class="styled-button-8"/>
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<%@ include file="WEB-INF/site-parts/addcar.jsp" %> <!-- Add car manager -->
	
	<%@ include file="WEB-INF/site-parts/footer.jsp" %> <!-- Footer -->
</body>
</html>