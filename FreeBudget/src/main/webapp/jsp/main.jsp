<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>FreeBudget</title>
    <style>
        table
        {
            width: 100%;
            border-collapse: collapse;
        }
        th, td
        {
            border: 1px solid black;
            padding: 8px; text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h1>FreeBudget</h1>
<a href="logout">Выход</a> | <a href="/main">Главная</a> <br/>

<c:if test="${errorMessage != null}">
    <p style="color: red;">${errorMessage}</p>
</c:if>

<h2>Транзакции</h2>
<h2>Добавить новую транзакцию</h2>
<form method="post" action="${pageContext.request.contextPath}/main">
    <input type="hidden" name="action" value="addTransaction">
    <label for="amount">Сумма:</label>
    <input type="text" id="amount" name="amount" required> <br>
    <label for="date">Дата:</label>
    <input type="date" id="date" name="date" required> <br>
    <label for="description">Описание:</label>
    <input type="text" id="description" name="description"> <br>
    <label for="isIncome">Доход:</label>
    <input type="checkbox" id="isIncome" name="isIncome"><br>
    Категория: <br>
    <c:forEach var="category" items="${categories}">
        <input type="checkbox" name="categoryIds" value="${category.id}"> ${category.name}<br>
    </c:forEach>
    <button type="submit">Добавить</button>
</form>

<form method="get" action="${pageContext.request.contextPath}/main">
    Фильтр по категориям:
    <select name="filterCategoryId">
        <option value="">Все категории</option>
        <c:forEach var="category" items="${categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Фильтровать">
</form>

<h2>Добавить новую категорию</h2>
<form method="post" action="${pageContext.request.contextPath}/main">
    <input type="hidden" name="action" value="addCategory">
    <label for="categoryName">Название:</label>
    <input type="text" id="categoryName" name="categoryName" required>
    <button type="submit">Добавить</button>
</form>

<c:if test="${empty transactions}">
    <p>Транзакции отсутствуют.</p>
</c:if>
<c:if test="${not empty transactions}">
    <table>
        <thead>
        <tr>
            <th>Дата</th>
            <th>Описание</th>
<%--            <th>Тип</th>--%>
            <th>Сумма</th>
            <th>Категория</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="transaction" items="${transactions}">
            <tr>
                <td>${transaction.date}</td>
                <td>${transaction.description}</td>
<%--                <td>--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${transaction.isIncome}">--%>
<%--                            <span style="color: green;">Доход</span>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <span style="color: red;">Расход</span>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </td>--%>
                <td>${transaction.amount}</td>
                <td>
                    <c:forEach var="category" items="${transaction.categories}">
                        ${category.name} <br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
