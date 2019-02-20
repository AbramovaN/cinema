<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message" var="ms"/>
<head>
    <div align="centre">
        <jsp:include page="header.jsp"/>
    </div>
    <title><fmt:message key="ORDERS_PAGE" bundle="${ms}"/></title>
</head>
<body>
<br/>
<h1 align="center"><fmt:message key="ORDERS_PAGE" bundle="${ms}"/></h1>
<hr/>
<form action="/listOfOrders" name="listOfOrders" method="POST">
    <input type="hidden" name="command" value="listOfOrders"/>
    <div align="center">
        <fmt:message key="CUSTUMAR_INFORMATION" bundle="${ms}"/>
        <br>
        <hr/>
        <fmt:message key="NAME" bundle="${ms}"/> ${userInfo.name}
        <br>
        <fmt:message key="SURNAME" bundle="${ms}"/> ${userInfo.surname}
        <br>
        <fmt:message key="PHONE_NUMBER" bundle="${ms}"/> ${userInfo.operatorCode}${userInfo.phoneNumber}
        <br>
        <fmt:message key="EMAIL" bundle="${ms}"/> ${userInfo.email}
        <br>
        <hr/>
        <fmt:message key="TICKETS_INFORMATION" bundle="${ms}"/>
        <br>
        <hr/>
        <c:forEach items="${tickets}" var="ticket">
            <fmt:message key="NAME_OF_FILM" bundle="${ms}"/> ${film.filmName}
        <br>
            <fmt:message key="DATE" bundle="${ms}"/> ${film.date}
        <br>
            <fmt:message key="TIME" bundle="${ms}"/> ${film.time}
        <br>
            <fmt:message key="PRICE" bundle="${ms}"/> ${film.price}
        <br>
            <fmt:message key="ROW" bundle="${ms}"/> ${ticket.cinemaHall.row}
        <br>
            <fmt:message key="PLACE" bundle="${ms}"/> ${ticket.cinemaHall.place}
        <hr/>
        </c:forEach>
</form>
</div>
</body>
</html>
