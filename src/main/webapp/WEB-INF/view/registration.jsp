<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/registration.css"/>">
</head>

<body>
<div class="wrapper">
    <form method="POST">
        <h2>Присоединяйтесь к нам,<br>создайте свой аккаунт</h2>
        <h1>Регистрация</h1>
        <div class="input-box">
            <input name="username" type="text" autocomplete="off" placeholder="Введите имя" required/>
        </div>
        <div class="input-box">
            <input name="businessName" type="text" autocomplete="off" placeholder="Введите название бизнеса" required/>
        </div>
        <div class="input-box">
            <input name="email" type="email" autocomplete="off" placeholder="Введите email"/>
        </div>
        <div class="input-box">
            <input name="password" type="password" autocomplete="off" placeholder="Придумайте пароль" required/>
        </div>
        <button type="submit" class="btn">Зарегистрироваться</button>
        <div class="register-link">
            <p>Уже зарегистрировались? <a class="login" href="${pageContext.request.contextPath}/login">Войти</a></p>
        </div>
    </form>
    <%@include file="/WEB-INF/view/parts/_errors.jsp" %>
<%@include file="/WEB-INF/view/parts/_footer.jsp" %>