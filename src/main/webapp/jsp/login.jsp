<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message" var="ms"/>
    <title><fmt:message key="LOGIN" bundle="${ms}"/></title>
    <div align="centre">
        <jsp:include page="header.jsp"/>
    </div>
</head>
<body>
<div align="center">
    <h3><fmt:message key="LOGIN" bundle="${ms}"/></h3>
    <hr/>
    <form action="/login" name="loginForm" method="POST">
        <input type="hidden" name="command" value="login"/>
        <fmt:message key="EMAIL" bundle="${ms}"/><br/>
        <input type="email" name="login" placeholder="anastasiabramo@yandex.ru" required value="" {7-45}> <br/>
        <fmt:message key="PASSWORD" bundle="${ms}"/><br/>
        <input type="password" name="password" maxlength="45" required placeholder="1234EE))" value="" {6-45}>
        <br/>
        <input type="submit" value=<fmt:message key="LOGIN" bundle="${ms}"/>>
        <p style="color: red;">${error}</p>
    </form>
</div>
<hr/>
</body>
</html>