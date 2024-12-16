<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

<h1>Ошибка!</h1>

<%=request.getParameter("err")%>

<a href="/">На главную</a>

</body>
</html>
