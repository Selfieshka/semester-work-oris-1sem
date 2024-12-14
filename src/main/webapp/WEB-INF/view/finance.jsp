<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Аналитика по вашему бизнесу</title>
    <link rel="stylesheet" href="<c:url value="/style/finance.css"/>">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@24,400,0,0"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer src="<c:url value="/js/finance.js"/>"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<main>
    <%@include file="/WEB-INF/view/parts/_sidebar.jsp" %>
    <div class="container">
        <header>
            <h1>Аналитика по вашему бизнесу</h1>
        </header>
        <div class="stats">
            <div class="stat-box" id="profit-box">
                <h2>Прибыль</h2>
                <p></p>
            </div>
            <div class="stat-box" id="revenue-box">
                <h2>Выручка</h2>
                <p></p>
            </div>
            <div class="stat-box" id="expense-box">
                <h2>Расходы</h2>
                <p></p>
            </div>
            <div class="stat-box" id="money-box">
                <h2>Деньги бизнеса</h2>
                <p></p>
            </div>
        </div>
        <div class="modal" id="modal-revenue-container">
            <div class="modal-content" id="modal-revenue-content">
                <span class="nav-icon material-symbols-rounded close" id="close-revenue">Close</span>
                <form method=POST action="<c:url value="/finance/revenue"/>">
                    <h3>Добавление выручки</h3>
                    <label for="revenue-amount">Сумма</label>
                    <input type="number" step="any" id="revenue-amount" name="amount" required/>
                    <label for="revenue-date">Дата, за которую вносится выручка:</label>
                    <input type="date" id="revenue-date" name="date" required/>

                    <button type="submit">Добавить</button>
                </form>
            </div>
        </div>
        <div class="modal" id="modal-expense-container">
            <div class="modal-content" id="modal-expense-content">
                <span class="nav-icon material-symbols-rounded close" id="close-expense">Close</span>
                <form method=POST action="<c:url value="/finance/expense"/>">
                    <h3>Добавление расхода</h3>
                    <label for="expense-amount">Сумма</label>
                    <input type="number" step="any" id="expense-amount" name="amount" required/>
                    <label for="expense-category">Категория</label>
                    <input type="text" id="expense-category" name="category" required/>
                    <label for="expense-date">Дата, за которую вносится расход:</label>
                    <input type="date" id="expense-date" name="date" required/>

                    <button type="submit">Добавить</button>
                </form>
            </div>
        </div>
        <div class="modal" id="modal-money-container">
            <div class="modal-content" id="modal-money-content">
                <span class="nav-icon material-symbols-rounded close" id="close-money">Close</span>
                <form method=POST action="<c:url value="/finance/money"/>">
                    <h3>Добавление денежного счёта</h3>
                    <label for="money-bank">Банк</label>
                    <input type="text" id="money-bank" name="bankName" required/>
                    <label for="money-amount">Сумма</label>
                    <input type="number" step="any" id="money-amount" name="amount" required/>

                    <button type="submit">Добавить</button>
                </form>
            </div>
        </div>
        <div class="charts-row">
            <div class="chart-container">
                <canvas id="lineChart"></canvas>
            </div>
            <div class="chart-container">
                <canvas id="barChart"></canvas>
            </div>
        </div>
    </div>
</main>
</body>
</html>
