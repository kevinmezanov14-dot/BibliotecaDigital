<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<nav class="navbar navbar-expand-lg custom-navbar">
    <div class="container-fluid px-4">

        <a class="navbar-brand d-flex align-items-center gap-2"
           href="${pageContext.request.contextPath}/dashboard">
            <i class="bi bi-book-half"></i>
            <strong>Biblioteca Digital</strong>
        </a>

        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarContent">

            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link ${fn:contains(pageContext.request.requestURI, '/libros') ? 'active' : ''}"
                       href="${pageContext.request.contextPath}/libros">
                        <i class="bi bi-journal-text"></i> Libros
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${fn:contains(pageContext.request.requestURI, '/prestamos') ? 'active' : ''}"
                       href="${pageContext.request.contextPath}/prestamos">
                        <i class="bi bi-arrow-left-right"></i> Préstamos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${fn:contains(pageContext.request.requestURI, '/clientes') ? 'active' : ''}"
                       href="${pageContext.request.contextPath}/clientes">
                        Clientes
                    </a>
                </li>
            </ul>

            <div class="navbar-user-zone">
                <div class="user-avatar">
                    ${fn:substring(sessionScope.usuario.nick, 0, 1)}
                </div>
                <span class="navbar-username">
                    <c:out value="${sessionScope.usuario.nick}" />
                </span>
                <a href="${pageContext.request.contextPath}/logout" class="btn-logout">
                    <i class="bi bi-box-arrow-right"></i>
                </a>
            </div>

        </div>
    </div>
</nav>
