<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Профиль</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/profile.css"/>">
    <script defer src="<c:url value="/js/profile.js"/>"></script>
</head>
<body>
<%--<div class="profile-container">--%>
<%--    <h2>Профиль пользователя</h2>--%>
<%--    <form id="profileForm">--%>
<%--        <div class="profile-field">--%>
<%--            <label for="lastName">Фамилия</label>--%>
<%--            <input type="text" id="lastName" name="lastName" value="Иванов" readonly>--%>
<%--        </div>--%>
<%--        <div class="profile-field">--%>
<%--            <label for="firstName">Имя</label>--%>
<%--            <input type="text" id="firstName" name="firstName" value="Иван" readonly>--%>
<%--        </div>--%>
<%--        <div class="profile-field">--%>
<%--            <label for="middleName">Отчество</label>--%>
<%--            <input type="text" id="middleName" name="middleName" value="Иванович" readonly>--%>
<%--        </div>--%>
<%--        <div class="profile-field">--%>
<%--            <label for="email">Email</label>--%>
<%--            <input type="email" id="email" name="email" value="ivanov@example.com" readonly>--%>
<%--        </div>--%>
<%--        <div class="profile-field">--%>
<%--            <label for="age">Возраст</label>--%>
<%--            <input type="number" id="age" name="age" value="30" min="1" max="120" readonly>--%>
<%--        </div>--%>
<%--        <button type="button" id="editButton">Редактировать</button>--%>
<%--        <button type="submit" id="saveButton" style="display: none;">Сохранить изменения</button>--%>
<%--    </form>--%>
<%--</div>--%>
<div class="container">
    <div class="profile">
        <div class="profile-header">
            <img src="<c:url value="img/avatar_default.png"/>" alt="profile" class="profile-img"/>
            <div class="profile-text-container">
                <h1 class="profile-title">Профиль</h1>
                <p class="profile-email">${sessionScope.get("owner").email()}</p>
            </div>
        </div>
        <div class="menu">
            <a href="#" class="menu-link">
                <i class="fa-solid fa-circle-user"></i>
                Аккаунт
            </a>
            <form method="POST" action="<c:url value="/logout"/>" class="menu-link">
                <button type="submit">
                    <i class="fa-solid fa-circle-user menu-icon"></i>
                    Выйти
                </button>
            </form>
        </div>
    </div>

    <form class="account">
        <div class="account-header">
            <h1 class="account-title">Настройки аккаунта</h1>
            <div class="btn-container">
                <button class="btn-cancel">Отмена</button>
                <button class="btn-save">Сохранить</button>
            </div>
        </div>
        <div class="account-edit">
            <div class="input-container">
                <label>Фамилия</label>
                <input type="text" placeholder="Фамилия" value="${sessionScope.get("owner").lastName()}"/>
            </div>
            <div class="input-container">
                <label>Имя</label>
                <input type="text" placeholder="Имя" value="${sessionScope.get("owner").firstName()}"/>
            </div>
            <div class="input-container">
                <label>Отчество</label>
                <input type="text" placeholder="Отчество" value="${sessionScope.get("owner").patronymic()}"/>
            </div>
        </div>
        <div class="account-edit">
            <div class="input-container">
                <label>Возраст</label>
                <input type="number" placeholder="Возраст" value="${sessionScope.get("owner").age()}"/>
            </div>
            <div class="input-container">
                <label>Номер телефона</label>
                <input type="text" placeholder="Номер телефона"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>
