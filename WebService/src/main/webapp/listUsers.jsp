<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<c:forEach var="user" items="${list}">
    <ul>
        <li>Login: <c:out value="${user.login}"/></li>

        <li>Role: <c:out value="${user.role}"/></li>

        <form method="post" action="/users/${user.id}">
            <input type="hidden" name="id" value="${user.id}">
            <input class="button" type="submit" value="select">
        </form>
    </ul>
    <hr />
</c:forEach>

<br>
<a href="/">Menu</a>

</body>
</html>
