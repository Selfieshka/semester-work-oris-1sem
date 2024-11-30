<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Business Efficiency</title>
    <link rel="stylesheet" href="<c:url value="/style/addEmployee.css"/>">
</head>
<body>
<form method="POST">
    <label for="firstName">Имя:</label>
    <input type="text" id="firstName" name="firstName" required>

    <label for="lastName">Фамилия:</label>
    <input type="text" id="lastName" name="lastName" required>

    <label for="patronymic">Отчество:</label>
    <input type="text" id="patronymic" name="patronymic">

    <label for="effectiveDate">Дата вступления в коллектив:</label>
    <input type="date" id="effectiveDate" name="effectiveDate" required>

    <label for="position">Должность:</label>
    <select id="position" name="position">
        <option value="Стажёр">Стажёр</option>
        <option value="Рабочий">Рабочий</option>
        <option value="Менеджер">Менеджер</option>
        <option value="Директор">Директор</option>
    </select>

    <label for="salary">Зарплата:</label>
    <input type="text" id="salary" name="salary" required>

    <button type="submit">Отправить</button>
</form>
</body>
</html>
