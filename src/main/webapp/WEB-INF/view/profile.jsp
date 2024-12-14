<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Профиль</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/profile.css"/>">
    <script defer src="<c:url value="/js/profile.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<%@include file="/WEB-INF/view/parts/_sidebar.jsp" %>

<form method="POST" class="account" id="accountForm">
    <div class="account-header">
        <h1 class="account-title">Настройки аккаунта</h1>
        <div class="btn-container">
            <button type="button" id="editButton" class="btn-edit">Редактировать</button>
            <button type="button" id="cancelButton" class="btn-cancel hidden">Отмена</button>
            <button type="submit" id="saveButton" class="btn-save hidden">Сохранить</button>
        </div>
    </div>
    <div class="account-edit">
        <div class="input-container">
            <label>Фамилия</label>
            <input type="text" name="lastName" placeholder="Фамилия" value="${sessionScope.get("owner").lastName()}"
                   readonly/>
        </div>
        <div class="input-container">
            <label>Имя</label>
            <input type="text" name="firstName" placeholder="Имя" value="${sessionScope.get("owner").firstName()}"
                   readonly/>
        </div>
        <div class="input-container">
            <label>Отчество</label>
            <input type="text" name="patronymic" placeholder="Отчество"
                   value="${sessionScope.get("owner").patronymic()}" readonly/>
        </div>
    </div>
    <div class="account-edit">
        <div class="input-container">
            <label>Возраст</label>
            <input type="number" name="age" placeholder="Возраст" value="${sessionScope.get("owner").age()}"
                   readonly/>
        </div>
        <div class="input-container">
            <label>Номер телефона</label>
            <input type="text" name="phoneNumber" placeholder="Номер телефона"
                   value="${sessionScope.get("owner").phoneNumber()}" readonly/>
        </div>
    </div>
</form>

<%@include file="/WEB-INF/view/parts/_footer.jsp" %>