<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message" var="ms"/>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="CREATE" bundle="${ms}"/></title>
    <div align="centre">
        <jsp:include page="header.jsp"/>
    </div>
</head>
<body>
<h3><fmt:message key="CREATE" bundle="${ms}"/></h3>
<hr/>
<form action="/createFilm" name="createFilm" method="POST">
    <input type="hidden" name="command" value="createFilm"/>
    <fmt:message key="NAME" bundle="${ms}"/><br/>
    <input type="text" name="name" placeholder="LOL" pattern="[A-Za-zА-Яа-яЁё]{1,45}" required> <br/>
    <fmt:message key="GENRE" bundle="${ms}"/><br/>
    <input type="text" name="genre" placeholder="horror" pattern="[A-Za-zА-Яа-яЁё]+$"
           required><br/>
    <fmt:message key="DESCRIPTION" bundle="${ms}"/><br/>
    <input type="text" name="description" placeholder="bla-bla-bla" maxlength="800"
           required><br/>
    <fmt:message key="PRICE" bundle="${ms}"/><br/>
    <input type="text" name="price" placeholder="50" pattern="[0-9]{1,3}"
           required><br/>
    <fmt:message key="TIME" bundle="${ms}"/><br/>
    <input type="time" name="time" required><br/>
    <fmt:message key="DATE" bundle="${ms}"/><br/>
    <input type="date" name="date" placeholder="2018-12-30" required><br/>
    <button type="submit"><fmt:message key="CREATE" bundle="${ms}"/></button>
    <span class="error">${error}</span>
</form>
<hr/>
</body>
</html>







