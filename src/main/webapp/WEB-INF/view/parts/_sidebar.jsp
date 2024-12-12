<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="<c:url value="/style/_sidebar.css"/>">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@24,400,0,0"/>
<aside class="sidebar">
    <header class="sidebar-header">
        <a class="header-logo">
            <c:if test="${empty sessionScope.get('owner').profilePhotoUrl()}">
                <img src="<c:url value='img/avatar_default.png'/>" alt="profile" class="profile-img"
                     id="profile-image"/>
            </c:if>
            <c:if test="${not empty sessionScope.get('owner').profilePhotoUrl()}">
                <img src="${sessionScope.get('owner').profilePhotoUrl()}" alt="profile" class="profile-img"
                     id="profile-image"/>
            </c:if>
        </a>
        <span class="user-name">${sessionScope.get('owner').firstName()}</span>
    </header>
    <nav class="sidebar-nav">
        <ul class="nav-list primary-nav">
            <li class="nav-item">
                <a href="<c:url value="/"/>" class="nav-link">
                    <span class="nav-icon material-symbols-rounded">Home</span>
                    <span class="nav-label">Главная</span>
                </a>
            </li>
            <li class="nav-item">
                <a href="<c:url value="/staff"/>" class="nav-link">
                    <span class="nav-icon material-symbols-rounded">Groups</span>
                    <span class="nav-label">Сотрудники</span>
                </a>
            </li>
            <li class="nav-item">
                <a href="<c:url value="/finance"/>" class="nav-link">
                    <span class="nav-icon material-symbols-rounded">Finance</span>
                    <span class="nav-label">Финансы</span>
                </a>
            </li>
            <li class="nav-item">
                <a href="<c:url value="/invoices"/>" class="nav-link">
                    <span class="nav-icon material-symbols-rounded">Docs</span>
                    <span class="nav-label">Накладные</span>
                </a>
            </li>
        </ul>
        <ul class="nav-list secondary-nav">
            <li class="nav-item">
                <a href="<c:url value="/profile"/>" class="nav-link">
                    <span class="nav-icon material-symbols-rounded">Account_Circle</span>
                    <span class="nav-label">Профиль</span>
                </a>
            </li>
        </ul>
    </nav>
</aside>