<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Накладные</title>
    <link rel="stylesheet" href="<c:url value="/style/invoices.css"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer src="<c:url value="/js/invoices.js"/>"></script>
</head>

<%@include file="/WEB-INF/view/parts/_sidebar.jsp" %>
<header class="header">
    <h1>Накладные</h1>
</header>
<%@include file="/WEB-INF/view/parts/_errors.jsp" %>

<div id="invoiceModal" class="modal">
    <div class="modal-content">
        <span class="close-modal" onclick="closeModal()">&times;</span>
        <h2>Информация о накладной</h2>
        <div id="invoiceDetails"></div>
    </div>
</div>

<div class="invoice-container">
    <button id="openModal" class="add-btn">Создать накладную</button>
    <div class="invoice-header">
        <span>Номер накладной</span>
        <span>Дата</span>
        <span>Удалить</span>
    </div>
    <c:forEach items="${requestScope.get('invoices')}" var="invoice">
        <div class="invoice-row" onclick="showInvoiceInfo(${invoice.invoiceId()}, '${invoice.number()}', '${invoice.date()}', '${invoice.sum()}', '${invoice.count()}', '${invoice.countTov()}')">
            <div class="invoice-info">
                <span><c:out value="${invoice.number()}"/></span>
                <span><c:out value="${invoice.date()}"/></span>
            </div>
            <span class="delete-btn"
                  onclick="deleteInvoice(${invoice.invoiceId()}, this.closest('.invoice-row'))">&times;</span>
        </div>
    </c:forEach>
</div>


<div id="myModal" class="modal-container">
    <div class="modal-content">
        <span class="close">&times;</span>
        <form method="POST" enctype="multipart/form-data">
            <label for="number">Введите номер накладной:</label>
            <input type="text" id="number" name="number" required/>
            <label for="date">Введите дату накладной:</label>
            <input type="date" id="date" name="date" required/>
            <label for="invoice">Выберите файл накладной:</label>
            <input type="file" id="invoice" name="invoice" required/>

            <h3>Заполните необходимые поля для анализа накладной:</h3>

            <label for="productName">Название колонки наименования товара</label>
            <input type="text" id="productName" name="productName" required/>
            <label for="unitMeasure">Название колонки единиц измерения товара</label>
            <input type="text" id="unitMeasure" name="unitMeasure" required/>
            <label for="quantity">Название колонки количество товара</label>
            <input type="text" id="quantity" name="quantity" required/>
            <label for="costPerUnit">Название колонки стоимости товара за единицу</label>
            <input type="text" id="costPerUnit" name="costPerUnit" required/>
            <button type="submit">Отправить</button>
        </form>
    </div>
</div>


<%@include file="/WEB-INF/view/parts/_footer.jsp" %>
