<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Libros - Biblioteca Digital</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=DM+Sans:wght@400;500;600&display=swap" rel="stylesheet">

<!-- CSS propios — orden importante -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/listar.css">
</head>
<body>

    <!-- NAVBAR -->
    <jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />

    <div class="container">
        <h1>📚 Libros en Biblioteca Digital</h1>

        <!-- Mensajes de Error -->
        <c:if test="${param.error == 'id_requerido'}">
            <div class="alert alert-danger">❌ Error: No se proporcionó el ID del libro</div>
        </c:if>

        <c:if test="${param.error == 'id_invalido'}">
            <div class="alert alert-danger">❌ Error: El ID del libro es inválido</div>
        </c:if>

        <c:if test="${param.error == 'eliminar'}">
            <div class="alert alert-danger">❌ Error al eliminar el libro. Intenta nuevamente.</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger"><c:out value="${error}" /></div>
        </c:if>

        <!-- Mensaje de Éxito -->
        <c:if test="${param.eliminado == 'success'}">
            <div class="alert alert-success">✅ Libro eliminado exitosamente</div>
        </c:if>

        <!-- Botón Agregar -->
        <div style="margin-bottom: 15px;">
            <a href="${pageContext.request.contextPath}/libros/registrar">
                ➕ Agregar Nuevo Libro
            </a>
        </div>

        <!-- Tabla de Libros -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>ISBN</th>
                    <th>Año</th>
                    <th>Autor</th>
                    <th>Stock</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="libro" items="${libros}">
                    <tr>
                        <td>${libro.id}</td>
                        <td><c:out value="${libro.titulo}" /></td>
                        <td><c:out value="${libro.isbn}" /></td>
                        <td>${libro.anio}</td>
                        <td><c:out value="${libro.autorNombre}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${libro.stock > 0}">
                                    <span class="stock-disponible">${libro.stock}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="stock-agotado">0</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/actualizar/libros?id=${libro.id}">
                                ✏️ Editar
                            </a>
                            <form action="${pageContext.request.contextPath}/eliminar/libros"
                                  method="post" style="display: inline;">
                                <input type="hidden" name="id" value="${libro.id}">
                                <button type="submit" class="btn-eliminar"
                                        onclick="return confirm('¿Estás seguro de eliminar el libro: ${libro.titulo}?');">
                                    🗑️ Eliminar
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty libros}">
            <p>📭 No hay libros registrados.</p>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>