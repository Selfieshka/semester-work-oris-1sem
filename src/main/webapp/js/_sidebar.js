document.addEventListener('DOMContentLoaded', () => {
    const currentPath = window.location.pathname;

    const navLinks = document.querySelectorAll('.nav-link');

    navLinks.forEach(link => {
        const href = link.getAttribute('href'); // Получаем значение href ссылки
        if (currentPath.endsWith(href)
            && !link.classList.contains("logout")
            && !link.classList.contains("delete")) {
            link.classList.add('active');
        }
    });
});