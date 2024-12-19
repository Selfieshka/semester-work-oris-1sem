<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Сотрудники</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/staff.css"/>">
    <script defer src="<c:url value="/js/staff.js"/>"></script>
</head>

<%@include file="/WEB-INF/view/parts/_sidebar.jsp" %>

<header class="header">
    <h1>Сотрудники</h1>
</header>
<%@include file="/WEB-INF/view/parts/_errors.jsp" %>
<div class="employee-list">
    <button class="add-btn" id="addEmployeeBtn">Добавить сотрудника</button>
    <c:forEach items="${staff}" var="employee">
        <div class="employee-row">
            <div class="employee-info">
                <span><c:out value="${employee.firstName}"/></span>
                <span><c:out value="${employee.lastName}"/></span>
                <span><c:out value="${employee.patronymic}"/></span>
                <span><c:out value="${employee.effectiveDate}"/></span>
                <span><c:out value="${employee.position}"/></span>
                <span>₽ <c:out value="${employee.salary}"/></span>
            </div>
            <span class="delete-btn" onclick="deleteEmployee(${employee.id})">&times;</span>
        </div>
    </c:forEach>
</div>

<div id="modalContainer" class="modal-container">
    <div class="modal-content">
        <span class="close" id="closeModal">&times;</span>
        <form method="POST">
            <label for="firstName">Имя:</label>
            <input type="text" id="firstName" name="firstName" required><br>

            <label for="lastName">Фамилия:</label>
            <input type="text" id="lastName" name="lastName" required><br>

            <label for="patronymic">Отчество:</label>
            <input type="text" id="patronymic" name="patronymic"><br>

            <label for="effectiveDate">Дата вступления в должность:</label>
            <input type="date" id="effectiveDate" min="2000-01-01" max="9999-12-31" name="effectiveDate" required><br>

            <label for="jobSearch">Поиск должности:</label>
            <input type="text" id="jobSearch" placeholder="Поиск должности..." onkeyup="filterJobs()"
                   onfocus="showJobOptions()"><br>

            <div id="jobOptions" class="job-options" style="display:none;"></div>

            <div id="selectedJobs" class="job-tags"></div>
            <input type="hidden" id="positions" name="positions" value="">

            <label for="salary">Зарплата:</label>
            <input type="number" id="salary" min="1" max="9999999999" name="salary" required><br>

            <button type="submit">Добавить сотрудника</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/view/parts/_footer.jsp" %>
