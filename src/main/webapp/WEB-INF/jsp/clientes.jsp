<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Clientes - Biblioteca Digital</title>
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
	href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/listar.css">
</head>
<body>

	<jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />

	<div class="container mt-5">

		<div class="d-flex justify-content-between align-items-center mb-4">
			<h3 class="elegant-title">Gestión de Clientes</h3>
			<button class="btn btn-elegant" data-bs-toggle="collapse"
				data-bs-target="#nuevoCliente">
				<i class="bi bi-person-plus"></i> Nuevo Cliente
			</button>
		</div>

		<!-- Mensaje de error -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger">
				<c:out value="${error}" />
			</div>
		</c:if>

		<!-- Mensaje de éxito -->
		<c:if test="${param.registrado == 'success'}">
			<div class="alert alert-success">✅ Cliente registrado
				exitosamente</div>
		</c:if>

		<!-- FORMULARIO NUEVO CLIENTE -->
		<div id="nuevoCliente" class="collapse mb-4">
			<div class="card card-elegant p-4">
				<form action="${pageContext.request.contextPath}/clientes"
					method="post">
					<div class="row g-3">
						<div class="col-md-6">
							<label class="form-label">Nick</label> <input type="text"
								name="nick" class="form-control" required>
						</div>
						<div class="col-md-6">
							<label class="form-label">Email</label> <input type="email"
								name="email" class="form-control" required>
						</div>
						<div class="col-md-6">
							<label class="form-label">Fecha de Nacimiento</label> <input
								type="date" name="fechaNacimiento" class="form-control">
						</div>
						<div class="col-12">
							<button type="submit" class="btn btn-elegant">
								<i class="bi bi-save"></i> Registrar Cliente
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<!-- TABLA -->
		<div class="card card-elegant p-4">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nick</th>
						<th>Email</th>
						<th>Fecha Nacimiento</th>
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cliente" items="${clientes}">
						<tr>
							<td>${cliente.id}</td>
							<td><c:out value="${cliente.nick}" /></td>
							<td><c:out value="${cliente.email}" /></td>
							<td><c:choose>
									<c:when test="${not empty cliente.fechaNacimiento}">
                                    ${cliente.fechaNacimiento}
                                </c:when>
									<c:otherwise>
										<span class="text-muted">—</span>
									</c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${cliente.activo}">
										<span class="badge bg-success">Activo</span>
									</c:when>
									<c:otherwise>
										<span class="badge bg-secondary">Inactivo</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
					<c:if test="${empty clientes}">
						<tr>
							<td colspan="5" class="text-center text-muted py-4">No hay
								clientes registrados.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>