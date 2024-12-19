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
            <input type="text" id="business-name" name="business-name" required>
        </div>
        <div class="form-group">
            <label for="business-email">Электронная почта</label>
            <input type="email" id="business-email" name="business-email" required>
        </div>
        <div class="form-group">
            <label for="business-phone">Телефон</label>
            <input type="text" id="business-phone" name="business-phone" required>
        </div>
        <div class="form-group">
            <label for="business-address">Адрес бизнеса</label>
            <input type="text" id="business-address" name="business-address" required>
        </div>
        <div class="form-group">
            <label for="business-category">Категория бизнеса</label>
            <select id="business-category" name="business-category" required>
                <option value="">Выберите категорию</option>
                <option value="Ресторан">Ресторан</option>
                <option value="Розничная торговля">Розничная торговля</option>
                <option value="Услуги">Услуги</option>
                <option value="Торговля онлайн">Торговля онлайн</option>
                <option value="Другие">Другие</option>
            </select>
        </div>
        <div class="form-group">
            <label for="business-website">Веб-сайт</label>
            <input type="text" id="business-website" name="business-website">
        </div>
        <div class="form-group">
            <label for="business-description">Описание бизнеса</label>
            <textarea id="business-description" name="business-description" rows="5" required></textarea>
        </div>
        <div class="form-group">
            <label for="business-comments">Дополнительные комментарии</label>
            <textarea id="business-comments" name="business-comments" rows="3"></textarea>
        </div>
        <button type="button" id="edit-button">Редактировать</button>
    </form>
</div>

<div class="map-container">
    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d560.7838067643488!2d49.121194169674766!3d55.790889752307734!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sru!2sru!4v1734639070953!5m2!1sru!2sru"
            allowfullscreen="" loading="lazy"></iframe>
</div>
<%@include file="/WEB-INF/view/parts/_footer.jsp" %>
