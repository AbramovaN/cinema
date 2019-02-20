<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message" var="ms"/>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="ADMIN_PAGE" bundle="${ms}"/></title>
    <div align="centre">
        <jsp:include page="header.jsp"/>
    </div>
</head>
<body>
<h3 align="center"><fmt:message key="ADMIN_PAGE" bundle="${ms}"/></h3>
<br/>
<form action="deleteFilm" method="post">
    <input type="hidden" name="command" value="deleteFilm"/>
    <input type="submit" value="<fmt:message key="DELETE_FILM" bundle="${ms}"/>">
</form>
<br/>
<form action="createFilm" method="post">
    <input type="hidden" name="command" value="createFilm"/>
    <input type="submit" value="<fmt:message key="CREATE" bundle="${ms}"/>">
</form>
</form>
</body>
</html>
