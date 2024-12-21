<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <style>
        /* Основные стили */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9; /* Светло-серый фон */
            color: #333; /* Темно-серый текст */
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1 {
            color: #333; /* Темно-серый текст для заголовка */
            margin-bottom: 20px;
            font-size: 2rem;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555; /* Серый цвет для меток */
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1rem;
            box-sizing: border-box;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #FF8C00; /* Оранжевый цвет при фокусе */
            outline: none;
        }

        button {
            background-color: #FF8C00; /* Оранжевый цвет для кнопки */
            color: #fff; /* Белый текст на кнопке */
            border: none;
            padding: 12px 24px;
            font-size: 1rem;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        button:hover {
            background-color: #e66f45; /* Темно-оранжевый цвет при наведении */
        }

    </style>
</head>
<body>
<div class="container">
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
</div>
</body>
</html>