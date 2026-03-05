<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Préstamos | Biblioteca Digital</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Estilos propios -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/dashboard.css">

<!-- Tipografías elegantes -->
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=DM+Sans:wght@400;500;600&display=swap"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />

	<div class="container py-5">

		<!-- HEADER -->
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h2 class="section-title">
				<i class="bi bi-journal-bookmark-fill me-2"></i> Gestión de
				Préstamos
			</h2>
		</div>

		<!-- CARD FORMULARIO -->
		<div class="card shadow-sm border-0 mb-5 elegant-card">
			<div class="card-body p-4">

				<h5 class="mb-4 fw-semibold">
					<i class="bi bi-plus-circle me-2"></i> Registrar nuevo préstamo
				</h5>

				<form action="${pageContext.request.contextPath}/prestamos"
					method="post" class="row g-4">

					<input type="hidden" name="accion" value="registrar">

					<!-- CLIENTE -->
					<div class="col-md-4">
						<label class="form-label">Cliente</label> <select name="clienteId"
							class="form-select form-elegant" required>
							<option disabled selected>Seleccione un cliente</option>
							<c:forEach var="c" items="${clientes}">
								<option value="${c.id}">${c.nick}</option>
							</c:forEach>
						</select>
						<div class="mt-2">
							<a href="${pageContext.request.contextPath}/clientes"
								class="text-decoration-none small"> ¿Nuevo cliente?
								Registrarlo aquí </a>
						</div>
					</div>

					<!-- LIBRO -->
					<div class="col-md-4">
						<label class="form-label">Libro</label> <select name="libroId"
							class="form-select form-elegant" required>
							<option disabled selected>Seleccione un libro</option>
							<c:forEach var="l" items="${libros}">
								<option value="${l.id}">${l.titulo}</option>
							</c:forEach>
						</select>
					</div>

					<!-- FECHA VENCIMIENTO -->
					<div class="col-md-4">
						<label class="form-label">Fecha de vencimiento</label> <input
							type="datetime-local" name="fechaVencimiento"
							class="form-control form-elegant" required>
					</div>

					<div class="col-12 text-end">
						<button type="submit" class="btn btn-elegant px-4">
							<i class="bi bi-check-circle me-2"></i> Registrar
						</button>
					</div>

				</form>
			</div>
		</div>

		<!-- TABLA DE PRÉSTAMOS -->
		<div class="card shadow-sm border-0 elegant-card">
			<div class="card-body p-4">

				<h5 class="mb-4 fw-semibold">
					<i class="bi bi-list-ul me-2"></i> Historial de préstamos
				</h5>

				<div class="table-responsive">
					<table class="table table-hover align-middle">
						<thead class="table-light">
							<tr>
								<th>Cliente</th>
								<th>Libro</th>
								<th>Fecha solicitud</th>
								<th>Vencimiento</th>
								<th>Estado</th>
								<th class="text-center">Acción</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="p" items="${prestamos}">
								<tr>
									<td>${p.clienteNick}</td>
									<td>${p.libroTitulo}</td>
									<td>${p.fechaSolicitud}</td>
									<td>${p.fechaVencimiento}</td>
									<td><c:choose>
											<c:when test="${p.estado == 'ACTIVO'}">
												<span class="badge bg-warning text-dark">Prestado</span>
											</c:when>

											<c:when test="${p.estado == 'DEVUELTO'}">
												<span class="badge bg-success">Devuelto</span>
											</c:when>

											<c:when test="${p.estado == 'VENCIDO'}">
												<span class="badge bg-danger">Vencido</span>
											</c:when>
										</c:choose>
									</td>
									<td>
										<c:if test="${p.estado == 'ACTIVO'}">
											<form action="${pageContext.request.contextPath}/prestamos"
												method="post">
												<input type="hidden" name="accion" value="devolver">
												<input type="hidden" name="id" value="${p.id}"> <input
													type="hidden" name="libroId" value="${p.libroId}">
												<button type="submit" class="btn btn-sm btn-elegant">
													<i class="bi bi-check-circle"></i> Devolver
												</button>
											</form>
										</c:if></td>
								</tr>
							</c:forEach>

							<c:if test="${empty prestamos}">
								<tr>
									<td colspan="6" class="text-center text-muted py-4">No
										existen préstamos registrados.</td>
								</tr>
							</c:if>

						</tbody>
					</table>
				</div>

			</div>
		</div>

	</div>

</body>
</html>