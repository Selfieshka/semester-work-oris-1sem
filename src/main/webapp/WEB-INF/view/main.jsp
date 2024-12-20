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
<div class="map-container" style="position:relative;overflow:hidden;"><a
        href="https://yandex.ru/maps/org/it_park_im_bashira_rameyeva/209623831137/?utm_medium=mapframe&utm_source=maps"
        style="color:#eee;font-size:12px;position:absolute;top:0px;">ИТ-парк им. Башира Рамеева</a><a
        href="https://yandex.ru/maps/43/kazan/category/science_park/126204989789/?utm_medium=mapframe&utm_source=maps"
        style="color:#eee;font-size:12px;position:absolute;top:14px;">Технопарк в Казани</a><a
        href="https://yandex.ru/maps/43/kazan/category/business_incubator_/82858001211/?utm_medium=mapframe&utm_source=maps"
        style="color:#eee;font-size:12px;position:absolute;top:28px;">Бизнес-инкубатор в Казани</a>
    <iframe src="https://yandex.ru/map-widget/v1/?indoorLevel=1&ll=49.128127%2C55.779474&mode=poi&poi%5Bpoint%5D=49.127671%2C55.779446&poi%5Buri%5D=ymapsbm1%3A%2F%2Forg%3Foid%3D209623831137&z=16.75"
            width="560" height="400" frameborder="1" allowfullscreen="true" style="position:relative;"></iframe>
</div>
<%@include file="/WEB-INF/view/parts/_footer.jsp" %>
