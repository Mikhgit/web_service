<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div class="form">

    <h2>Login page</h2><br>
    <form method="post" action="/">
        <input type="text" required placeholder="login" name="login"><br>
        <input type="password" required placeholder="password" name="password"><br><br>
        <input class="button" type="submit" value="Sign in">
    </form>

    <form method="post" action="/signup">
        <input type="text" required placeholder="login" name="login"><br>
        <input type="password" required placeholder="password" name="password"><br><br>
        <input class="button" type="submit" value="Sign Up">
    </form>
    <br>
    <a href="/users">All users</a>

</div>
</body>
</html>
