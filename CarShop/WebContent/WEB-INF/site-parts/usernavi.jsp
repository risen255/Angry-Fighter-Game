<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-usernavi">
	<ul>
		<a href="index.jsp">
			<li>Strona główna</li>
		</a>
		
		<c:choose>
			<c:when test="${sessionScope.user == null}">
				<a href="register.jsp">
					<li>Rejestracja</li>
				</a>
				<a href="login.jsp">
					<li>Login</li>
				</a>
			</c:when>
			<c:when test="${sessionScope.user != null}">
				<a href="Logout">
					<li>Wyloguj [${sessionScope.user.login}]</li>
				</a>
			</c:when>
		</c:choose>
	</ul>
</div>