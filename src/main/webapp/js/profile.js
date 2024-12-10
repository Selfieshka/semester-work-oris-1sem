const editButton = document.getElementById('editButton');
const cancelButton = document.getElementById('cancelButton');
const saveButton = document.getElementById('saveButton');

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

// $(document).ready(function () {
//     $('#accountForm').on('submit', function (event) {
//         event.preventDefault(); // Предотвращаем стандартное поведение отправки формы
//
//         var formData = $(this).serialize(); // Сериализуем данные формы
//
//         $.ajax({
//             type: 'POST',
//             url: '/BusinessEfficiency/profile',
//             data: formData,
//             success: function (response) {
//                 // Обработка успешного ответа от сервера
//                 if (response.redirectUrl) {
//                     window.location.href = response.redirectUrl; // Выполняем редирект
//                 }
//                 console.log('Успешно отправлено:', response);
//             },
//             error: function (xhr, status, error) {
//                 // Обработка ошибки
//                 console.error('Ошибка при отправке:', error);
//             }
//         });
//     });
// });


$(document).ready(function () {
    // Обработчик для клика на изображении профиля
    $("#profile-image").click(function () {
        $("#file-input").click(); // Открываем диалог выбора файла
    });

    // Обработчик для выбора файла
    $("#file-input").change(function (event) {
        var file = event.target.files[0]; // Получаем выбранный файл
        if (file) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $("#profile-image").attr("src", e.target.result); // Отображаем загруженное изображение
                sendFileToServer(file);
            };
            reader.readAsDataURL(file); // Читаем файл как Data URL (для предварительного просмотра)
        }
    });

    function sendFileToServer(file) {
        var formData = new FormData();
        formData.append('profilePhoto', file); // Добавляем файл в объект FormData

        $.ajax({
            url: '/BusinessEfficiency/profile/upload',
            type: 'POST',
            data: formData,
            contentType: false, // jQuery сам установит нужные заголовки
            processData: false, // Не обрабатываем данные
            success: function (response) {
                console.log('Изображение успешно загружено:', response);
            },
            error: function (xhr, status, error) {
                console.error('Ошибка загрузки:', error);

            }
        });
    }
});


