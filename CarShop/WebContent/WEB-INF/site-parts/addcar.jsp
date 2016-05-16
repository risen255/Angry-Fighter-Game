<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-addcar">
	<c:choose>
		<c:when test="${requestScope.loadCarMsg == 'BAD_FORMAT'}">
			<p style="color: red;">Podany plik ma nieprawidłowe rozszerzenie. Akceptowane jest tylko JPG i PNG.</p><br/>
		</c:when>
			<c:when test="${requestScope.loadCarMsg == 'MODEL_SHORT_LENGTH'}">
			<p style="color: red;">Nazwa modelu zbyt krótka.</p><br/>
		</c:when>
		<c:when test="${requestScope.loadCarMsg == 'COLOR_SHORT_LENGTH'}">
			<p style="color: red;">Nazwa koloru zbyt krótka.</p><br/>
		</c:when>
		<c:when test="${requestScope.loadCarMsg == 'PRICE_SHORT_LENGTH'}">
			<p style="color: red;">Musisz podać cenę.</p><br/>
		</c:when>
		<c:when test="${requestScope.loadCarMsg == 'PRICE_MUST_BE_NUMERIC'}">
			<p style="color: red;">Cena musi być liczbą.</p><br/>
		</c:when>
		<c:when test="${requestScope.loadCarMsg == 'PRICE_CAN_NOT_BE_ZERO'}">
			<p style="color: red;">Cena musi być większa od 0.</p><br/>
		</c:when>
		<c:when test="${requestScope.loadCarMsg == 'CAR_ALREADY_EXISTS'}">
			<p style="color: red;">Taki samochód już istnieje w bazie.</p><br/>
		</c:when>
		<c:when test="${requestScope.loadCarMsg == 'WRONG_ADD_RECORD_TO_DATABASE'}">
			<p style="color: red;">Podane informacje nie zmieszczą się w bazie danych.</p><br/>
		</c:when>
		<c:when test="${requestScope.loadCarMsg == 'SAVED'}">
			<p style="color: green;">OKI Samochod zostal zapisany do bazy xDDD</p><br/>
		</c:when>
	</c:choose>
	<c:remove var="loadCarMsg"/> <!-- Remove session atrribute about load car message -->

	<form method="POST" action="Series${requestScope.bmwSeries}" enctype="multipart/form-data">
		Model <input type="text" name="model"/><br/>
		Kolor <input type="text" name="color"/><br/>
		Cena <input type="text" name="price"/><br/><br/>
        File: <input type="file" name="file" accept="image/*"/><br/><br/>
        <input type="submit" value="Dodaj" name="uploadSubmit" class="styled-button-10"/>
    </form>
</div>