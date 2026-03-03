<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Editar Libro</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<div class="container">
		<h1>Editar Libro</h1>

		<c:if test="${not empty error}">
			<div style="color: red">${error}</div>
		</c:if>

		<form method="post"
			action="<%= request.getContextPath() %>/actualizar/libros">
			<input type="hidden" name="id" value="${libro.id}">

			<div class="form-group">
				<label>Título</label> <input type="text" name="titulo"
					value="${libro.titulo}" required>
			</div>

			<div class="form-group">
				<label>ISBN</label> <input type="text" name="isbn"
					value="${libro.isbn}" required>
			</div>

			<div class="form-group">
				<label>Año</label> <input type="number" name="anio"
					value="${libro.anio}" required>
			</div>

			<div class="form-group">
				<label>Autor</label> <select name="autor_id" class="form-control"
					required>
					<c:forEach var="autor" items="${autores}">
						<option value="${autor.id}"
							<c:if test="${autor.id == libro.autorId}">
                selected
            </c:if>>
							${autor.nombre}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label>Stock</label> <input type="number" name="stock"
					value="${libro.stock}" required>
			</div>

			<button type="submit">Actualizar</button>
		</form>

		<div style="margin-top: 20px;">
			<a href="<%= request.getContextPath() %>/libros">Volver a la
				Lista</a>
		</div>
	</div>
</body>
</html>