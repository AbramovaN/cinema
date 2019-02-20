<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message" var="ms"/>
<head>
    <title><fmt:message key="FILMS_SCHEDULE" bundle="${ms}"/></title>
    <div align="centre">
        <jsp:include page="header.jsp"/>
    </div>
    <br/>
</head>
<body>
<form action="/films" name="films" method="post">
    <input type="hidden" name="command" value="films"/>
    <input type="date" name="date" value="dateOfFilm">
    <button type="submit"><fmt:message key="OK" bundle="${ms}"/>
    </button>
    <p style="color: red;">${error}</p>
</form>
<hr>
<h3 align="center"><fmt:message key="FILMS_SCHEDULE" bundle="${ms}"/></h3>
<table align="center" border="1" cellpadding="6" cellspacing="1">
    <tr>
        <th><fmt:message key="FILM_ID" bundle="${ms}"/></th>
        <th><fmt:message key="NAME_OF_FILM" bundle="${ms}"/></th>
        <th><fmt:message key="GENRE" bundle="${ms}"/></th>
        <th><fmt:message key="DESCRIPTION" bundle="${ms}"/></th>
        <th><fmt:message key="DATE" bundle="${ms}"/></th>
        <th><fmt:message key="TIME" bundle="${ms}"/></th>
        <th><fmt:message key="PRICE" bundle="${ms}"/></th>
        <th><fmt:message key="BUY_TICKETS" bundle="${ms}"/></th>
    </tr>
    <c:forEach items="${filmsList}" var="film">
        <tr>
            <td>${film.idFilm}</td>
            <td>${film.filmName}</td>
            <td>${film.genre}</td>
            <td>${film.description}</td>
            <td>${film.date}</td>
            <td>${film.time}</td>
            <td>${film.price}</td>
            <td>
                <form action="hallLayout" method="post">
                    <input type="hidden" name="command" value="hallLayout"/>
                    <input type="hidden" name="price" value="${film.price}">
                    <input type="hidden" name="idFilm" value="${film.idFilm}">
                    <input type="submit" value="<fmt:message key="HALL_LAYOUT" bundle="${ms}"/>">
                </form>

            </td>
        </tr>
    </c:forEach>
</table>
<hr>
</body>
</html>