const editButton = document.getElementById('editButton');
const cancelButton = document.getElementById('cancelButton');
const saveButton = document.getElementById('saveButton');
const inputs = document.querySelectorAll('.account input');

let originalValues = {};

editButton.addEventListener('click', function () {
    const inputs = document.querySelectorAll('.account-edit input');
    inputs.forEach(input => {
        originalValues[input.name] = input.value;
        input.readOnly = false;
    });
    inputs.forEach(input => {
        input.removeAttribute('readonly');
    });

    editButton.classList.add('hidden');
    cancelButton.classList.remove('hidden');
    saveButton.classList.remove('hidden');
});

cancelButton.addEventListener('click', function () {
    const inputs = document.querySelectorAll('.account-edit input');
    inputs.forEach(input => {
        input.value = originalValues[input.name];
        input.readOnly = true;
    });

    inputs.forEach(input => {
        input.setAttribute('readonly', true);
    });

    editButton.classList.remove('hidden');
    cancelButton.classList.add('hidden');
    saveButton.classList.add('hidden');
});

$(document).ready(function() {
    $('#accountForm').on('submit', function(event) {
        event.preventDefault(); // Предотвращаем стандартное поведение отправки формы

        var formData = $(this).serialize(); // Сериализуем данные формы

        $.ajax({
            type: 'POST',
            url: '/profile',
            data: formData,
            success: function(response) {
                // Обработка успешного ответа от сервера
                console.log('Успешно отправлено:', response);
            },
            error: function(xhr, status, error) {
                // Обработка ошибки
                console.error('Ошибка при отправке:', error);
            }
        });
    });
});


