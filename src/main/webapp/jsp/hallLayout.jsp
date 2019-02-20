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
    <title><fmt:message key="HALL_LAYOUT" bundle="${ms}"/></title>
</head>
<body>
<br/>
<h1 align="center"><fmt:message key="HALL_LAYOUT" bundle="${ms}"/></h1>
<hr/>
<form action="/hallLayout" name="hallLayout" method="POST">
    <input type="hidden" name="command" value="hallLayout"/>
</form>
<div align="right">
    <button type="hidden" style="background-color:#ffff0f;" disabled="disabled">Booked</button>
    <button type="hidden" style="background-color:#52ff0f;">Free</button>
</div>
<form action="/listOfOrders" name="listOfOrders" method="POST">
    <input type="hidden" name="command" value="listOfOrders"/>
    <div align="center">
        <table class="table">
            <thead>
            <tr>
                <th colspan="21"><h1><fmt:message key="SCREEN" bundle="${ms}"/></h1></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listsTikets}" var="tickets">
            <tr>
                <c:forEach items="${tickets}" var="ticket">
                    <c:if test="${ticket.booked}">
                        <td style="background-color:#ffff0f;">
                            <input type="checkbox" name="ticket" value="${ticket.idTicket}"
                                   disabled>${ticket.cinemaHall.place}
                        </td>
                    </c:if>
                    <c:if test="${!ticket.booked}">
                        <td style="background-color:#52ff0f;">
                            <input type="checkbox" name="ticket"
                                   value="${ticket.idTicket}"/>${ticket.cinemaHall.place}
                        </td>
                    </c:if>
                </c:forEach>
                </c:forEach>
            </tr>
            <tr>
                <td colspan="10" align="center">
                    <input type="submit" value="<fmt:message key = "SUBMIT" bundle="${ms}"/>"></td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
<hr/>
</body>
</html>
