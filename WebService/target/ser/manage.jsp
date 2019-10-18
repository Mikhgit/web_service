<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage</title>
</head>
<body>
Admin Page
<ul>
    <li>Login: ${user.login}</li>
    <li>Role: ${user.role}</li>

    <select name="role" form="choice-box-menu-item">
        <option>Admin</option>
        <option>User</option>
        <option>Unknown</option>
    </select>
    <form method="post" id="choice-box-menu-item" action="/users/${user.id}">
        <input type="hidden" name="id" value="${user.id}">
        <input type="submit" value="select">
    </form>
    <li>ID: ${user.id}</li>
</ul>
<br>
<a href="/">Menu</a>
</body>
</html>
