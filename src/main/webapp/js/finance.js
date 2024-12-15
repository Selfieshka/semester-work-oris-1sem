const currentPath = window.location.pathname;
const pathParts = currentPath.split('/');
const basePath = '/' + pathParts[1];

const ctxLine = document.getElementById('lineChart').getContext('2d');
const ctxBar = document.getElementById('barChart').getContext('2d');

const lineChart = new Chart(ctxLine, {
    type: 'line',
    data: {
        labels: ['01.01.2013', '01.02.2013', '01.03.2013', '01.04.2013', '01.05.2013', '01.06.2013'],
        datasets: [{
            label: 'Выручка',
            data: [400000, 450000, 470000, 600000, 580000, 700000],
            backgroundColor: 'rgba(78, 115, 223, 0.2)',
            borderColor: 'rgba(78, 115, 223, 1)',
            borderWidth: 2,
            fill: true,
        },
            {
                label: 'Прибыль',
                data: [200000, 250000, 230000, 400000, 370000, 480000],
                backgroundColor: 'rgba(28, 200, 138, 0.2)',
                borderColor: 'rgba(28, 200, 138, 1)',
                borderWidth: 2,
                fill: true,
            }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'Сумма (₽)'
                }
            },
            x: {
                title: {
                    display: true,
                    text: 'Дата'
                }
            }
        }
    }
});

const barChart = new Chart(ctxBar, {
    type: 'bar',
    data: {
        labels: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн'],
        datasets: [{
            label: 'Прибыль',
            data: [1200000, 1500000, 1400000, 1700000, 1600000, 1900000],
            backgroundColor: 'rgba(28, 200, 138, 0.8)',
            borderColor: 'rgba(28, 200, 138, 1)',
            borderWidth: 1,
            borderRadius: 5,
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'Сумма (₽)'
                }
            }
        }
    }
});

const modalRevenue = document.getElementById("modal-revenue-container");
const modalExpense = document.getElementById("modal-expense-container");
const modalMoney = document.getElementById("modal-money-container");

const revenueBox = document.getElementById("revenue-box");
const expenseBox = document.getElementById("expense-box");
const moneyBox = document.getElementById("money-box");

const closeRevenue = document.getElementById("close-revenue");
const closeExpense = document.getElementById("close-expense");
const closeMoney = document.getElementById("close-money");


revenueBox.onclick = function () {
    modalRevenue.style.display = "block";
}

expenseBox.onclick = function () {
    modalExpense.style.display = "block";
}

moneyBox.onclick = function () {
    modalMoney.style.display = "block";
}

closeRevenue.onclick = function () {
    modalRevenue.style.display = "none";
}

closeMoney.onclick = function () {
    modalMoney.style.display = "none";
}

closeExpense.onclick = function () {
    modalExpense.style.display = "none";
}

window.onclick = function (event) {
    if (event.target === modalRevenue) {
        modalRevenue.style.display = "none";
    }
    if (event.target === modalExpense) {
        modalExpense.style.display = "none";
    }
    if (event.target === modalMoney) {
        modalExpense.style.display = "none";
    }
}

$(document).ready(function () {
    const requests = [
        {url: basePath + '/api/v1/stats/expense', selector: '#expense-box p', defaultMessage: 'Загрузка...'},
        {url: basePath + '/api/v1/stats/revenue', selector: '#revenue-box p', defaultMessage: 'Загрузка...'},
        {url: basePath + '/api/v1/stats/money', selector: '#money-box p', defaultMessage: 'Загрузка...'},
        {url: basePath + '/api/v1/stats/profit', selector: '#profit-box p', defaultMessage: 'Загрузка...'},
    ];

    function fetchData(url, selector, defaultMessage) {
        $(selector).text(defaultMessage);

        $.ajax({
            url: url,
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                const formattedValue = (data.value).toLocaleString('ru-RU', {
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 2
                });
                $(selector).text(formattedValue + ' ₽');
            },
            error: function (xhr, status, error) {
                console.error("AJAX Error:", status, error);
                $(selector).text('Ошибка загрузки данных');
            }
        });
    }

    requests.forEach(request => {
        fetchData(request.url, request.selector, request.defaultMessage);
    });
});
