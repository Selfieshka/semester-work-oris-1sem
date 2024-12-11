// Получаем элементы модального окна и кнопок
const modal = document.getElementById("myModal");
const btnOpenModal = document.getElementById("openModal");
const btnCloseModal = document.getElementsByClassName("close")[0];

// Открываем модальное окно при нажатии на кнопку
btnOpenModal.onclick = function () {
    modal.style.display = "block";
}

// Закрываем модальное окно при нажатии на <span> (кнопка закрытия)
btnCloseModal.onclick = function () {
    modal.style.display = "none";
}

// Закрываем модальное окно при нажатии в любом месте вне окна
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Функция для отображения и скрытия деталей накладной
function toggleDetails(row) {
    const details = row.nextElementSibling;
    if (details.style.display === "none" || details.style.display === "") {
        // Отображаем детали с анимацией
        details.style.display = "block";
        details.style.opacity = 0;
        setTimeout(() => {
            details.style.transition = "opacity 0.3s ease";
            details.style.opacity = 1;
        }, 10);
    } else {
        // Скрываем детали с анимацией
        details.style.opacity = 0;
        setTimeout(() => {
            details.style.display = "none";
        }, 300);
    }
}
