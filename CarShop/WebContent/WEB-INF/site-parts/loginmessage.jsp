<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-message">
	<c:choose>
		<c:when test="${requestScope.loginUserMsg == 'INCORRECT_LOGIN_OR_PASSWORD'}">
			<p style="color: red;">Podałeś nieprawidłowy login lub hasło.</p><br/>
		</c:when>
		<c:when test="${requestScope.loginUserMsg == 'LOGGED'}">
			<p style="color: green;">Zostałeś pomyślnie zalogowany.</p><br/>
		</c:when>
	</c:choose>
</div> 