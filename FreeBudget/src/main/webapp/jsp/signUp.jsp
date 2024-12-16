<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signUp</title>
</head>
<body>
<h1>FreeBudget</h1>
<form action="/signUp" method="post">

    <label for="email">Email:</label>
    <input type="text" id="email" name="email" placeholder="murat@mail.ru" required>
    <br>

    <label for="nickname">Имя:</label>
    <input type="text" id="nickname" name="nickname" placeholder="Murat" required>
    <br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" placeholder="Password123" required>
    <br>
    <label for="password"><em>Пароль должен быть не короче 5 символов, содержать строчные, прописные буквы и цифры</em></label>
    <br>

    <button type="submit">Зарегистрироваться</button>
</form>
</body>
</html>
