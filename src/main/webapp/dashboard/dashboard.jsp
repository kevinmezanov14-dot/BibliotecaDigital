<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Dashboard - Biblioteca Digital</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/dashboard.css">
</head>

<body>

	<!-- NAVBAR -->
	<nav class="navbar navbar-expand-lg navbar-dark"
		style="background: linear-gradient(135deg, #667eea, #764ba2);">
		<div class="container-fluid">

			<span class="navbar-brand d-flex align-items-center gap-2"> <i
				class="bi bi-book"></i> Biblioteca Digital
			</span>

			<div class="d-flex align-items-center gap-3">
				<span class="text-white fw-semibold">
					${sessionScope.usuario.nick} </span> <a
					href="${pageContext.request.contextPath}/logout"
					class="btn btn-outline-light btn-sm"> <i
					class="bi bi-box-arrow-right"></i> Cerrar Sesión
				</a>
			</div>

		</div>
	</nav>

	<!-- CONTENIDO -->
	<div class="container my-5">

		<div class="card p-4 mb-4 shadow-sm">
			<h1>Bienvenido, ${sessionScope.usuario.nick}</h1>
			<p class="text-muted">Este es tu dashboard personal.</p>
			<small>Edad registrada: ${sessionScope.edad} años</small>
		</div>

		<div class="row g-4">

			<!-- LIBROS -->
			<div class="col-md-6 col-lg-4">
				<div class="card p-4 h-100 text-center shadow-sm">

					<i class="bi bi-book fs-1 mb-3"></i>

					<h5>Colección de Libros</h5>

					<p class="text-muted">Explora y administra todos los libros
						disponibles</p>

					<a href="${pageContext.request.contextPath}/libros"
						class="btn btn-primary mt-3"> Ir a Libros </a>

				</div>
			</div>

			<!-- CONFIGURACIÓN -->
			<div class="col-md-6 col-lg-4">
				<div class="card p-4 h-100 text-center shadow-sm">

					<i class="bi bi-gear fs-1 mb-3"></i>

					<h5>Configuración</h5>

					<p class="text-muted">Ajusta tus preferencias (próximamente)</p>

					<button class="btn btn-secondary mt-3" disabled>En
						desarrollo</button>

				</div>
			</div>

			<!-- ESTADÍSTICAS -->
			<div class="col-md-6 col-lg-4">
				<div class="card p-4 h-100 text-center shadow-sm">

					<i class="bi bi-bar-chart fs-1 mb-3"></i>

					<h5>Estadísticas</h5>

					<p class="text-muted">Revisa tus datos (próximamente)</p>

					<button class="btn btn-secondary mt-3" disabled>En
						desarrollo</button>

				</div>
			</div>

		</div>

	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
