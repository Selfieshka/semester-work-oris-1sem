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
<div class="container">
    <div class="profile">
        <div class="profile-header">
            <c:if test="${empty sessionScope.get('owner').profilePhotoUrl()}">
                <img src="<c:url value='img/avatar_default.png'/>" alt="profile" class="profile-img"
                     id="profile-image"/>
            </c:if>
            <c:if test="${not empty sessionScope.get('owner').profilePhotoUrl()}">
                <img src="${sessionScope.get('owner').profilePhotoUrl()}" alt="profile" class="profile-img"
                     id="profile-image"/>
            </c:if>
            <input name="profilePhoto" type="file" id="file-input" style="display: none;"
                   accept="image/x-png, image/jpeg"/>
            <div class="profile-text-container">
                <h1 class="profile-title">Профиль</h1>
                <p class="profile-email">${sessionScope.get("owner").email()}</p>
            </div>
        </div>
        <div class="menu">
            <a href="<c:url value="/profile"/>" class="menu-link">
                <i class="fa-solid fa-circle-user"></i>
                Аккаунт
            </a>
            <form method="POST" action="<c:url value="/profile/logout"/>" class="menu-link logout">
                <button type="submit">
                    <i class="fa-solid fa-circle-user menu-icon"></i>
                    Выйти
                </button>
            </form>
            <form method="POST" id="deleteOwner" action="<c:url value="/profile/delete"/>" class="menu-link delete">
                <button type="submit">
                    <i class="fa-solid fa-circle-user menu-icon"></i>
                    Удалить аккаунт
                </button>
            </form>
        </div>
    </div>

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
</div>
</body>
</html>
