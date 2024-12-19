<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Business Efficiency</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/welcome.css"/>">
</head>
<body>

<div class="container">
    <h1>Добро пожаловать в нашу систему!</h1>
    <p>Оптимизируйте бизнес-процессы вашего малого бизнеса с нашей помощью:</p>
    <ul>
        <li><strong>Контроль и управление</strong> штабом сотрудников</li>
        <li><strong>Управление товарными запасами:</strong> контроль хранимых и принимаемых товаров</li>
        <li><strong>Анализ расходов и доходов</strong> для повышения эффективности</li>
    </ul>
    <p>Присоединяйтесь и начните оптимизацию вашего бизнеса уже сегодня!</p>
    <a href="<c:url value="/login"/>" class="button">Начать</a>
</div>
</body>
</html>