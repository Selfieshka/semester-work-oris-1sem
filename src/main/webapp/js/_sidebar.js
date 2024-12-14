document.addEventListener('DOMContentLoaded', () => {
    // Получаем текущий путь URL
    const currentPath = window.location.pathname;

    // Получаем все ссылки с классом nav-link
    const navLinks = document.querySelectorAll('.nav-link');

    // Перебираем ссылки и проверяем их href
    navLinks.forEach(link => {
        const href = link.getAttribute('href'); // Получаем значение href ссылки
        if (currentPath === '/' && href === '/') {
            link.classList.add('active'); // Добавляем класс active для главной страницы
        } else if (currentPath.endsWith(href)) {
            link.classList.add('active'); // Добавляем класс active, если текущий путь заканчивается на href
        }
    });
});