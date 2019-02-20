<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message" var="ms"/>
    <title><fmt:message key="CINEMA" bundle="${ms}"/></title>
</head>
<body>
<div align="right">
    <form action="changeLocale" method="post">
        <input type="hidden" name="command" value="changeLocale"/>
        <input type="submit" name="local" value="en">
    </form>
    <form action="changeLocale" method="post">
        <input type="hidden" name="command" value="changeLocale"/>
        <input type="submit" name="local" value="ru">
    </form>
    <form action="changeLocale" method="post">
        <input type="hidden" name="command" value="changeLocale"/>
        <input type="submit" name="local" value="ua">
    </form>
    <div/>
    <div align="left">
        <form action="login" method="post">
            <input type="hidden" name="command" value="login"/>
            <input type="submit" value=<fmt:message key="LOGIN" bundle="${ms}"/>>
        </form>
        <br/>
        <form action="add" method="post">
            <input type="hidden" name="command" value="add"/>
            <input type="submit" value=<fmt:message key="REGISTRATION" bundle="${ms}"/>>
        </form>
        <br/>
        <form action="films" method="post">
            <input type="hidden" name="command" value="films"/>
            <input type="submit" value=<fmt:message key="SESSIONS" bundle="${ms}"/>>
        </form>
    </div>
</div>
</body>
</html>


