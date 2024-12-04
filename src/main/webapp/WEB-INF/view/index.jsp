<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Business Efficiency</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Main</title>
    <link rel="stylesheet" href="<c:url value="/style/index.css"/>">
    <script defer src="https://unpkg.com/@popperjs/core@2"></script>
    <script defer src="<c:url value="/js/graph.js"/>"></script>
</head>

<body>
<div class="nav-bar">
    <a href="<c:url value="/"/>">Главная</a>
    <a href="<c:url value="/staff"/>">Сотрудники</a>
    <a href="<c:url value="/finance"/>">Финансы</a>
    <c:if test="${not empty sessionScope.get('email')}">
    <a href="<c:url value="/profile"/>">Профиль</a>
    </c:if>
    <c:if test="${empty sessionScope.get('email')}">
        <a href="<c:url value="/login"/>">Вход</a>
    </c:if>

</div>

<div id="chart">
</div>
</body>
</html>