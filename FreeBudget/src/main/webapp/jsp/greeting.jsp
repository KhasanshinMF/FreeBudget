<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FreeBudget</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            font-size: 2rem;
        }

        a {
            text-decoration: none;
        }

        button {
            background-color: #FF8C00;
            color: #fff;
            border: none;
            padding: 12px 24px;
            font-size: 1rem;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            margin: 10px 0;
            width: 200px;
        }

        button:hover {
            background-color: #e66f45;
        }

        .container {
            text-align: center;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>FreeBudget</h1>
    <div>
        <a href="/signIn">
            <button type="button">Войти</button>
        </a>
    </div>
    <div>
        <a href="/signUp">
            <button type="button">Зарегистрироваться</button>
        </a>
    </div>
</div>
</body>
</html>
