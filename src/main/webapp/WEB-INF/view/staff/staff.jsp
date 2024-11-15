<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Business Efficiency</title>
</head>
<body>
<table border="1" cellpadding="5">
    <caption><h2>Список сотрудников</h2></caption>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
        <th>Дата вступления в коллектив</th>
        <th>Должность</th>
        <th>Зарплата</th>
    </tr>
    <c:forEach items="${staff}" var="employee">
        <tr>
            <td><c:out value="${employee.getFirstName()}"/></td>
            <td><c:out value="${employee.getLastName()}"/></td>
            <td><c:out value="${employee.getPatronymic()}"/></td>
            <td><c:out value="${employee.getEffectiveDate()}"/></td>
            <td><c:out value="${employee.getPosition()}"/></td>
            <td><c:out value="${employee.getSalary()}"/></td>
        </tr>
    </c:forEach>
</table border="1" cellpadding="5">
<a href="<c:url value="/staff/add"/>" style="text-decoration:none;">
    <button>Добавить сотрудника</button>
</a>
</body>
</html>