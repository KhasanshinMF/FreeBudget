<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ошибка</title>
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
            text-align: center;
        }

        h1 {
            color: #DC3545;
            margin-bottom: 20px;
            font-size: 2rem;
        }

        p {
            font-size: 1.2rem;
            margin-bottom: 20px;
        }

        a {
            color: #FF8C00;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #e66f45;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Ошибка!</h1>
    <p>
        <c:if test="${not empty statusCode}">
            ${statusCode}<br>
        </c:if>
    </p>
    <p>
        <c:if test="${not empty errorMessage}">
            ${errorMessage}<br>
        </c:if>
    </p>
    <a href="/main">На главную</a>
</div>
</body>
</html>