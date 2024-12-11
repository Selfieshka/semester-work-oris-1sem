<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Накладные</title>
    <link rel="stylesheet" href="<c:url value="/style/invoices.css"/>">
</head>
<body>
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
    <button type="submit">Send the file</button>
</form>
</body>
</html>
