<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style/main.css"/>">
    <script defer src="<c:url value="/js/main.js"/>"></script>
    <title>Главная</title>
</head>

<%@include file="/WEB-INF/view/parts/_sidebar.jsp" %>
<div class="my-container">
    <h1>Информация о бизнесе</h1>
    <form id="business-info-form">
        <div class="form-group">
            <label for="business-name">Название бизнеса</label>
            <input type="text" id="business-name" name="business-name" value="Tomato juice" required>
        </div>
        <div class="form-group">
            <label for="business-email">Электронная почта</label>
            <input type="email" id="business-email" name="business-email" value="kirill@gmail.com" required>
        </div>
        <div class="form-group">
            <label for="business-phone">Телефон</label>
            <input type="text" id="business-phone" name="business-phone" value="+79872797711" required>
        </div>
        <div class="form-group">
            <label for="business-address">Адрес бизнеса</label>
            <input type="text" id="business-address" name="business-address" value="г. Казань, ул. Кремлевская, 18к1" required>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/view/parts/_footer.jsp" %>
