<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Registrar Libro - Biblioteca Digital</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet">

<!-- Bootstrap Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
      rel="stylesheet">

<!-- CSS Personalizado -->
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/navbar.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/formulario.css">

</head>

<body>

    <!-- NAVBAR -->
    <jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />

    <!-- CONTENIDO CENTRADO -->
    <div class="page-wrapper">
        <div class="form-container">

            <h1>📚 Registrar Nuevo Libro</h1>
            <p class="subtitle">
                Completa los datos del libro para agregarlo al catálogo
            </p>

            <!-- Mensaje de Error -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ❌ <c:out value="${error}" />
                </div>
            </c:if>

            <!-- FORMULARIO -->
            <form action="${pageContext.request.contextPath}/libros/registrar"
                  method="post">

                <!-- Título -->
                <div class="form-group">
                    <label for="titulo">
                        Título <span class="required">*</span>
                    </label>
                    <input type="text"
                           id="titulo"
                           name="titulo"
                           placeholder="Ej: Cien años de soledad"
                           required
                           maxlength="255"
                           autofocus>
                </div>

                <!-- ISBN -->
                <div class="form-group">
                    <label for="isbn">
                        ISBN <span class="required">*</span>
                    </label>
                    <input type="text"
                           id="isbn"
                           name="isbn"
                           placeholder="Ej: 978-3-16-148410-0"
                           required
                           maxlength="50">
                    <div class="help-text">
                        Código internacional del libro
                    </div>
                </div>

                <!-- Año -->
                <div class="form-group">
                    <label for="anio">
                        Año de Publicación <span class="required">*</span>
                    </label>
                    <input type="number"
                           id="anio"
                           name="anio"
                           placeholder="Ej: 2024"
                           required
                           min="1000"
                           max="2100"
                           value="2024">
                </div>

                <!-- Autor -->
                <div class="form-group">
                    <label for="autor">
                        Autor <span class="required">*</span>
                    </label>

                    <select id="autor" name="autor" required>
                        <option value="">-- Seleccione un autor --</option>

                        <c:forEach var="autor" items="${autores}">
                            <option value="${autor.id}">
                                <c:out value="${autor.nombre}" />
                            </option>
                        </c:forEach>

                    </select>

                    <div class="help-text">
                        ¿No encuentras el autor?
                        <a href="${pageContext.request.contextPath}/autores/registrar"
                           target="_blank">
                            Regístralo aquí
                        </a>
                    </div>
                </div>

                <!-- Stock -->
                <div class="form-group">
                    <label for="stock">
                        Stock Inicial
                    </label>
                    <input type="number"
                           id="stock"
                           name="stock"
                           placeholder="Ej: 10"
                           min="0"
                           max="9999"
                           value="0">
                    <div class="help-text">
                        Cantidad de ejemplares disponibles (por defecto: 0)
                    </div>
                </div>

                <!-- BOTÓN -->
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">
                        💾 Guardar Libro
                    </button>
                </div>

            </form>

            <!-- VOLVER -->
            <div class="back-link">
                <a href="${pageContext.request.contextPath}/libros">
                    ← Volver al listado de libros
                </a>
            </div>

        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js">
    </script>

</body>
</html>