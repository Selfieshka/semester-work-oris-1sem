<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Сотрудники</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/staff.css"/>">
</head>

<%@include file="/WEB-INF/view/parts/_sidebar.jsp" %>

<div class="main-content">
    <table>
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
    </table border="1" cellpadding="5"
    <a class="add-employee" href="<c:url value="/staff/add"/>">
        <button>Добавить сотрудника</button>
    </a>
</div>

<%@include file="/WEB-INF/view/parts/_footer.jsp" %>
