<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message" var="ms"/>
    <meta charset="UTF-8">
    <div align="centre">
        <jsp:include page="header.jsp"/>
    </div>
    <title><fmt:message key="REGISTRATION" bundle="${ms}"/></title>
</head>
<body>
<h3><fmt:message key="REGISTRATION_FORM" bundle="${ms}"/></h3>
<hr/>
<form action="/add" name="add" method="POST">
    <input type="hidden" name="command" value="add"/>
    <fmt:message key="SURNAME" bundle="${ms}"/><br/>
    <input type="text" name="surname" placeholder="Abramova" pattern="[A-Za-zА-Яа-яЁё]{2,45}" required> <br/>
    <fmt:message key="NAME" bundle="${ms}"/><br/>
    <input type="text" name="name" placeholder="Anastasia" maxlength="45" pattern="[A-Za-zА-Яа-яЁё]{2,45}"
           required><br/>
    <fmt:message key="PHONE_NUMBER" bundle="${ms}"/><br/>
    <input type="text" name="phone_number" placeholder="8063292695" pattern="[0-9]{11}" required>
    <br/>
    <fmt:message key="EMAIL" bundle="${ms}"/><br/>
    <input type="email" name="email" placeholder="anastasiabramo@yandex.ru" value="" pattern="{7,45}" required>
    <br/>
    <fmt:message key="PASSWORD" bundle="${ms}"/><br/>
    <input type="password" name="password" value="" minlength="6" maxlength="45" required>
    <br/>
    <button type="submit"><fmt:message key="SUBMIT" bundle="${ms}"/></button>
    <span class="error">${error}</span>
</form>
<hr/>
</body>
</html>