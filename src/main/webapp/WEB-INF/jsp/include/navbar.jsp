<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%
    String contextPath = request.getContextPath();
    String currentPage = request.getRequestURI();
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">

<nav class="navbar navbar-expand-lg navbar-dark shadow"
     style="background: linear-gradient(135deg, #667eea, #764ba2);">

    <div class="container-fluid">

        <!-- Logo -->
        <a class="navbar-brand d-flex align-items-center gap-2"
           href="${pageContext.request.contextPath}/dashboard">
            <i class="bi bi-book"></i>
            <strong>Biblioteca Digital</strong>
        </a>

        <!-- Botón móvil -->
        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Contenido -->
        <div class="collapse navbar-collapse justify-content-between"
             id="navbarContent">

            <!-- Menú principal -->
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

				<li class="nav-item"><a
					class="nav-link ${fn:contains(pageContext.request.requestURI, '/libros') ? 'active fw-bold' : ''}"
					href="${pageContext.request.contextPath}/libros"> <i
						class="bi bi-journal-text"></i> Libros
				</a></li>

			</ul>

            <!-- Usuario -->
            <div class="d-flex align-items-center gap-3">

                <span class="text-white fw-semibold">
                    <i class="bi bi-person-circle"></i>
                    ${sessionScope.usuario.nick}
                </span>

                <a href="${pageContext.request.contextPath}/logout"
                   class="btn btn-outline-light btn-sm">
                    <i class="bi bi-box-arrow-right"></i>
                    Cerrar Sesión
                </a>

            </div>

        </div>
    </div>
</nav>