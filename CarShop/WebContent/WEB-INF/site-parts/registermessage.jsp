<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-message">
	<c:choose>
		<c:when test="${requestScope.registerUserMsg == 'FIELD_LOGIN_EMPTY'}">
			<p style="color: red;">Pole nazwy użytkownika nie może być puste.</p><br/>
		</c:when>
		<c:when test="${requestScope.registerUserMsg == 'PASSWORD_TOO_SHORT'}">
			<p style="color: red;">Hasło musi składać się z 8 znaków.</p><br/>
		</c:when>
		<c:when test="${requestScope.registerUserMsg == 'FIELD_NAME_EMPTY'}">
			<p style="color: red;">Pole imienia nie może byc puste.</p><br/>
		</c:when>
		<c:when test="${requestScope.registerUserMsg == 'FIELD_NAME_EMPTY'}">
			<p style="color: red;">Pole imienia nie może byc puste.</p><br/>
		</c:when>
		<c:when test="${requestScope.registerUserMsg == 'FIELD_SURNAME_EMPTY'}">
			<p style="color: red;">Pole nazwiska nie może byc puste.</p><br/>
		</c:when>
		<c:when test="${requestScope.registerUserMsg == 'INCORRECT_EMAIL'}">
			<p style="color: red;">Podany email jest nieprawidłowy.</p><br/>
		</c:when>
		<c:when test="${requestScope.registerUserMsg == 'LOGIN_BUSY'}">
			<p style="color: red;">Login jest już zajęty.</p><br/>
		</c:when>
		<c:when test="${requestScope.registerUserMsg == 'EMAIL_BUSY'}">
			<p style="color: red;">Email jest już zajęty.</p><br/>
		</c:when>
		<c:when test="${requestScope.registerUserMsg == 'WRONG_ADD_RECORD_TO_DATABASE'}">
			<p style="color: red;">Wystąpił problem z dodaniem informacji do bazy danych.</p><br/>
		</c:when>
		<c:when test="${requestScope.registerUserMsg == 'REGISTERED'}">
			<p style="color: green;">Zostałeś pomyślnie zarejestrowany i automatycznie zalogowany na swoje konto.</p><br/>
		</c:when>
	</c:choose>
</div>