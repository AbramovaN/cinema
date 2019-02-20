<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message" var="ms"/>
<head>
    <title><fmt:message key="DELETE_FILM" bundle="${ms}"/></title>
    <div align="centre">
        <jsp:include page="header.jsp"/>
    </div>
    <div align="right">
        <form action="logout" method="post">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" value=<fmt:message key="LOGOUT" bundle="${ms}"/>>
        </form>
    </div>
</head>
<body>
<form action="deleteFilm" method="post">
    <input type="hidden" name="command" value="deleteFilm"/>
    <fmt:message key="DELETE_FILM_WITH_ID" bundle="${ms}"/><br/>
    <input type="number" name="idFilm" required value="" pattern="[0-9]">
    <br/>
    <p style="color: red;">${error}</p>
    <input type="submit" name="command" value="<fmt:message key="DELETE_FILM" bundle="${ms}"/>">
</form>
</body>
</html>
