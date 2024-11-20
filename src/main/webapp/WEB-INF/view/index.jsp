<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Business Efficiency</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="<c:url value="/style/index.css"/>">
    <script src="https://unpkg.com/@popperjs/core@2"></script>
    <script defer src="<c:url value="/js/index.js"/>"></script>
</head>

<body>
<%--<div class="nav-bar">--%>
<%--    <a href="<c:url value="/"/>">Главная</a>--%>
<%--    <a href="<c:url value="/staff"/>">Сотрудники</a>--%>
<%--    <a href="">Финансы</a>--%>
<%--</div>--%>

<div id="chart">
</div>
</body>
</html>