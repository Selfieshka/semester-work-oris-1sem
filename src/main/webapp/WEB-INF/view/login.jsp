<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Вход в аккаунт</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/login.css"/>">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>
<div class="wrapper">
    <form method="POST" action="${pageContext.request.contextPath}/login">
        <h2>Добро пожаловать!</h2>
        <h1>Вход</h1>
        <div class="input-box">
            <i class='bx bxs-user'></i>
            <input type="text" name="username" id="username" autocomplete="off" placeholder="Имя пользователя"
                   required/>
        </div>
        <div class="input-box">
            <i class='bx bxs-lock'></i>
            <input type="password" name="password" id="password" autocomplete="off" placeholder="Пароль" required/>
        </div>
        <div class="remember">
            <label class="checkbox"><input type="checkbox"/>Запомнить меня</label>
            <a href="#">Забыли пароль?</a>
        </div>
        <button type="submit" class="btn">Войти</button>
        <div class="register-link">
            <p>Ещё не зарегистрировались? <a class="registration"
                                             href="${pageContext.request.contextPath}/registration">Регистрация</a></p>
        </div>

        <%--        <div th:if="${param.error}" class="message-box-error">--%>
        <%--            <span>--%>
        <%--                Неверный логин или пароль--%>
        <%--            </span>--%>
        <%--        </div>--%>

        <c:if test="${not empty param.registered}">
            <div class="message-box-success">
                <span>
                    Вы успешно зарегистрировались, повторите введённые данные
                </span>
            </div>
        </c:if>

        <%--        <div th:if="${param.logout}" class="message-box-logout">--%>
        <%--            <span>--%>
        <%--                Вы вышли из аккаунта--%>
        <%--            </span>--%>
        <%--        </div>--%>
    </form>
</div>
</body>

</html>
