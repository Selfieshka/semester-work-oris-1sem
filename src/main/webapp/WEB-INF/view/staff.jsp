<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Business Efficiency</title>
</head>
<body>
<a>Имя | Фамилия | Отчество | Дата вступления в коллектив</a><br><br>
<c:forEach items="${staff}" var="employee">
    <a>${employee.getFirstname()} ${employee.getLastname()} ${employee.getPatronymic()} ${employee.getEffectiveDate()}</a><br>
</c:forEach>
</body>
</html>
