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
		
		<c:choose>
			<c:when test="${sessionScope.user != null}"> <!-- Logged -->
				<c:choose>
					<c:when test="${requestScope.registerUserMsg != null}"> <!-- When exists message about register and then automatically log in -->
						<h1 class="content-happytitle">
							Rejestracja
						</h1>
						<%@ include file="WEB-INF/site-parts/registermessage.jsp" %>
					</c:when>
					<c:when test="${requestScope.registerUserMsg == null }"> <!--  When doesn't exist message about register -->
						<%@ include file="WEB-INF/site-parts/badway.jsp" %>
					</c:when>
				</c:choose>
			</c:when>
			
			<c:when test="${sessionScope.user == null}"> <!-- When exists message about register, but registration has mistakes or this is first entry on the page -->
			
				<h1 class="content-happytitle">
					Rejestracja
				</h1>
					
				<%@ include file="WEB-INF/site-parts/registermessage.jsp" %>
					
				<div class="content-registerlogin">
					<form method="POST" action="Register">
						<table align="center">
							<tbody>
								<tr>
									<td>Nazwa użytkownika:</td>
									<td><input type="text" name="login"></td>
								</tr>
								<tr>
									<td class="content-registerlogin-atom">Hasło:</td>
									<td><input type="password" name="password"></td>
								</tr>
								<tr>
									<td class="content-registerlogin-atom">Imię:</td>
									<td><input type="text" name="name"></td>
								</tr>
								<tr>
									<td class="content-registerlogin-atom">Nazwisko:</td>
									<td><input type="text" name="surname"></td>
								</tr>
								<tr>
									<td class="content-registerlogin-atom">Email:</td>
									<td><input type="text" name="email"></td>
								</tr>
							</tbdody>
						</table>
						
						<input type="submit" name="registerUserSubmit" value="Zarejestruj" class="styled-button-8 content-registerlogin-button"/>
					</form>
				</div>
			</c:when>
		</c:choose>
	</div>
	
	<%@ include file="WEB-INF/site-parts/footer.jsp" %> <!-- Footer -->

</body>
</html>