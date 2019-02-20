<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message" var="ms"/>
<body>
<fmt:setBundle basename="message" var="ms"/>
<style content="text/css">
    #header {
        height: 25px;
        background: #ffff0f;
        margin-right: 30px;
    }

    .a, .b, .c, .d {
        float: left;
        margin: 0 5px;
    }

    .button {
        background: ivory;
    }
</style>
<div id="header">
    <div class="a">
        <form action="login" method="post">
            <input type="hidden" name="command" value="login"/>
            <input type="submit" class="button" value=<fmt:message key="LOGIN" bundle="${ms}"/>>
        </form>
    </div>
    <div class="b">
        <form action="add" method="post">
            <input type="hidden" name="command" value="add"/>
            <input type="submit" class="button" value=<fmt:message key="REGISTRATION" bundle="${ms}"/>>
        </form>
    </div>
    <div class="c">
        <form action="films" method="post">
            <input type="hidden" name="command" value="films"/>
            <input type="submit" class="button" value=<fmt:message key="SESSIONS" bundle="${ms}"/>>
        </form>
    </div>
    <div align="center">
        <div class="d">
            <form action="logout" method="post">
                <input type="hidden" name="command" value="logout"/>
                <input type="submit" class="button" value=<fmt:message key="LOGOUT" bundle="${ms}"/>>
            </form>
        </div>
    </div>
</div>
</body>
</html>