<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signIn</title>
</head>
<body>
<h1>FreeBudget</h1>
<form action="/signIn" method="post">

    <label for="email">Email:</label>
    <input type="text" id="email" name="email" placeholder="murat@mail.ru" required>
    <br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" placeholder="Password123" required>
    <br>

    <button type="submit">Войти</button>

</form>
</body>
</html>
