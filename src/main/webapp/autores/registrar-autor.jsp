<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrar Autor - Biblioteca Digital</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=DM+Sans:wght@400;500;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/formulario.css">
</head>
<body>

    <jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />

    <div class="page-wrapper">
        <div class="form-container">

            <h1>✍️ Registrar Nuevo Autor</h1>
            <p class="subtitle">Completa los datos del autor para agregarlo al sistema</p>

            <!-- Mensaje de éxito -->
            <c:if test="${param.exito == 'true'}">
                <div class="alert alert-success">
                    ✅ Autor registrado exitosamente. Puedes cerrar esta ventana.
                </div>
            </c:if>

            <!-- Mensaje de error -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ❌ <c:out value="${error}" />
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/autores/registrar" method="post">

                <div class="form-group">
                    <label for="nombre">Nombre <span class="required">*</span></label>
                    <input type="text" id="nombre" name="nombre"
                           placeholder="Ej: Gabriel García Márquez"
                           required maxlength="150" autofocus>
                </div>

                <div class="form-group">
                    <label for="nacionalidad">Nacionalidad</label>
                    <input type="text" id="nacionalidad" name="nacionalidad"
                           placeholder="Ej: Colombiano"
                           maxlength="100">
                    <div class="help-text">Campo opcional</div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">
                        💾 Guardar Autor
                    </button>
                </div>

            </form>

            <div class="back-link">
                <a href="${pageContext.request.contextPath}/libros/registrar">
                    ← Volver al registro de libros
                </a>
            </div>

        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>