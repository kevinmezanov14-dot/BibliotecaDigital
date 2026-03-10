<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Editar Libro — Biblioteca Digital</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=DM+Sans:wght@400;500;600&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/editar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
</head>
<body>

	<!-- NAVBAR -->
	<jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />

	<div class="container py-5">
		<div class="row justify-content-center">
			<div class="col-lg-7">

				<div class="card card-editar">

					<div class="card-header-editar d-flex align-items-center gap-3">
						<i class="bi bi-pencil-square fs-3"></i>
						<div>
							<h4 class="mb-0 fw-bold">Editar Libro</h4>
							<small class="opacity-75">Modifica los datos del libro y
								guarda los cambios</small>
						</div>
					</div>

					<div class="card-body p-4">

						<c:if test="${param.actualizado == 'success'}">
							<div class="alert alert-success d-flex align-items-center gap-2"
								role="alert">
								<i class="bi bi-check-circle-fill"></i> Libro actualizado
								correctamente.
							</div>
						</c:if>

						<c:if test="${not empty error}">
							<div class="alert alert-danger d-flex align-items-center gap-2"
								role="alert">
								<i class="bi bi-exclamation-triangle-fill"></i> <span><c:out
										value="${error}" /></span>
							</div>
						</c:if>

						<form method="post"
							action="${pageContext.request.contextPath}/actualizar/libros"
							id="formEditar" novalidate>

							<input type="hidden" name="id" value="${libro.id}">

							<!-- Título -->
							<div class="mb-3">
								<label class="form-label fw-semibold"> Título <span
									class="badge-requerido">*</span>
								</label> <input type="text" name="titulo" value="${libro.titulo}"
									class="form-control" pattern=".{2,200}"
									placeholder="Ej: Cien Años de Soledad" required>
								<div class="invalid-feedback">El título es obligatorio y
									debe tener al menos 2 caracteres.</div>
							</div>

							<!-- ISBN -->
							<div class="mb-3">
								<label class="form-label fw-semibold"> ISBN <span
									class="badge-requerido">*</span>
								</label> <input type="text" name="isbn" value="${libro.isbn}"
									class="form-control" pattern="[\d\-]{10,17}" maxlength="17"
									placeholder="Ej: 978-3-16-148410-0" required>
								<div class="form-text text-muted">
									<i class="bi bi-info-circle"></i> Entre 10 y 17 caracteres,
									solo números y guiones.
								</div>
								<div class="invalid-feedback">ISBN inválido. Solo números
									y guiones (10–17 caracteres).</div>
							</div>

							<!-- Año y Stock -->
							<div class="row g-3 mb-3">
								<div class="col-md-6">
									<label class="form-label fw-semibold"> Año de
										publicación <span class="badge-requerido">*</span>
									</label> <input type="number" name="anio" value="${libro.anio}"
										class="form-control" min="1000" max="2025"
										placeholder="Ej: 2023" required>
									<div class="invalid-feedback">Ingresa un año válido
										(entre 1000 y 2025).</div>
								</div>
								<div class="col-md-6">
									<label class="form-label fw-semibold"> Stock <span
										class="badge-requerido">*</span>
									</label> <input type="number" name="stock" value="${libro.stock}"
										class="form-control" min="0" max="9999" placeholder="Ej: 10"
										required>
									<div class="invalid-feedback">El stock es obligatorio y
										no puede ser negativo.</div>
								</div>
							</div>

							<!-- Autor -->
							<div class="mb-4">
								<label class="form-label fw-semibold"> Autor <span
									class="badge-requerido">*</span>
								</label> <select name="autor_id" class="form-select" required>
									<option value="">-- Selecciona un autor --</option>
									<c:forEach var="autor" items="${autores}">
										<option value="${autor.id}"
											<c:if test="${autor.id == libro.autorId}">selected</c:if>>
											<c:out value="${autor.nombre}" />
										</option>
									</c:forEach>
								</select>
								<div class="invalid-feedback">Debes seleccionar un autor.</div>
							</div>

							<!-- Botones -->
							<div class="d-flex gap-2">
								<button type="submit"
									class="btn btn-primary btn-actualizar text-white">
									<i class="bi bi-save me-1"></i> Guardar cambios
								</button>
								<a href="${pageContext.request.contextPath}/libros"
									class="btn btn-outline-secondary"> <i
									class="bi bi-arrow-left me-1"></i> Volver a la lista
								</a>
							</div>

							<p class="text-muted mt-3 mb-0" style="font-size: 0.8rem;">
								<span class="badge-requerido">*</span> Campos obligatorios
							</p>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>