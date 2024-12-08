document.getElementById('editButton').addEventListener('click', function () {
    // Переключить поля на редактирование
    let inputs = document.querySelectorAll('input[type="text"], input[type="email"], input[type="number"]');
    inputs.forEach(input => {
        input.removeAttribute('readonly');
        input.style.backgroundColor = '#fff'; // Изменить фон на белый
        input.style.pointerEvents = 'auto'; // Разрешить взаимодействие с полем
    });

    document.getElementById('editButton').style.display = 'none'; // Скрыть кнопку "Редактировать"
    document.getElementById('saveButton').style.display = 'inline-block'; // Показать кнопку "Сохранить изменения"
});

document.getElementById('profileForm').addEventListener('submit', function (event) {
    event.preventDefault();
    alert('Изменения сохранены!');
    // Здесь можно добавить код для отправки данных на сервер
});