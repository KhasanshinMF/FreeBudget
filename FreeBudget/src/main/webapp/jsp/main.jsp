<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
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
            box-sizing: border-box;
        }

        h1 {
            color: #333;
            margin: 0;
            font-size: 1.8rem;
        }

        a {
            color: #333;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #e66f45;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #e0e0e0;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #FF8C00;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f5f5f5;
        }

        tr:hover {
            background-color: #e9e9e9;
        }

        form {
            margin-bottom: 20px;
            background: #fff;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }

        input[type="text"],
        input[type="date"],
        input[type="checkbox"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1rem;
            box-sizing: border-box;
        }

        input[type="text"]:focus,
        input[type="date"]:focus,
        select:focus {
            border-color: #FF8C00;
            outline: none;
        }

        button {
            background-color: #FF8C00;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 1rem;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #e66f45;
        }

        .error-message {
            color: #DC3545;
            font-weight: bold;
            margin-bottom: 15px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #f9f9f9;
            padding: 15px 20px;
            margin-bottom: 20px;
            width: 100%;
            border-bottom: 2px solid #FF8C00;
        }

        .add-transaction-form,
        .add-category-form {
            display: none;
            margin-top: 20px;
            padding: 15px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        .buttons-row {
            display: flex;
            justify-content: flex-start;
            gap: 10px;
            margin-bottom: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }

        .filter-button {
            background-color: #FF8C00;
        }

        .filter-button:hover {
            background-color: #e66f45;
        }

    </style>
    <script>
        function toggleCategoryForm() {
            const form = document.getElementById('addCategoryForm');
            form.style.display = (form.style.display === 'none' || form.style.display === '') ? 'block' : 'none';
        }

        function toggleTransactionForm() {
            const form = document.getElementById('addTransactionForm');
            form.style.display = (form.style.display === 'none' || form.style.display === '') ? 'block' : 'none';
        }
    </script>
</head>
<body>
<div class="container">
    <header class="header">
        <h1>FreeBudget</h1>
        <a href="logout">Выход</a>
    </header>

    <c:if test="${errorMessage != null}">
        <p class="error-message">${errorMessage}</p>
    </c:if>

    <div class="buttons-row">
        <button onclick="toggleTransactionForm()">Добавить операцию</button>
        <button onclick="toggleCategoryForm()">Добавить категорию</button>
    </div>

    <div id="addTransactionForm" class="add-transaction-form">
        <form method="post" action="${pageContext.request.contextPath}/main">
            <input type="hidden" name="action" value="addTransaction">
            <label for="amount">Сумма:</label>
            <input type="text" id="amount" name="amount" required>
            <label for="date">Дата:</label>
            <input type="date" id="date" name="date" required>
            <label for="description">Описание:</label>
            <input type="text" id="description" name="description">
            <label>Категория:</label>
            <c:forEach var="category" items="${categories}">
                <input type="checkbox" name="categoryIds" value="${category.id}"> ${category.name}<br>
            </c:forEach>
            <br>
            <button type="submit">Добавить</button>
        </form>
    </div>

    <form method="get" action="${pageContext.request.contextPath}/main">
        <label>Фильтр по категориям:</label>
        <select name="filterCategoryId">
            <option value="">Все категории</option>
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <button type="submit" class="filter-button">Фильтровать</button>
    </form>

    <div id="addCategoryForm" class="add-category-form">
        <form method="post" action="${pageContext.request.contextPath}/main">
            <input type="hidden" name="action" value="addCategory">
            <label for="categoryName">Название:</label>
            <input type="text" id="categoryName" name="categoryName" required>
            <button type="submit">Добавить</button>
        </form>
    </div>

    <c:if test="${empty transactions}">
        <p>Транзакции отсутствуют.</p>
    </c:if>
    <c:if test="${not empty transactions}">
        <table>
            <thead>
            <tr>
                <th>Дата</th>
                <th>Описание</th>
                <th>Сумма</th>
                <th>Категория</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="transaction" items="${transactions}">
                <tr>
                    <td>${transaction.date}</td>
                    <td>${transaction.description}</td>
                    <td>${transaction.amount}</td>
                    <td>
                        <c:forEach var="category" items="${transaction.categories}">
                            ${category.name}
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>