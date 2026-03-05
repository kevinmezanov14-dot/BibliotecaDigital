<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Dashboard — Biblioteca Digital</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">

<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=DM+Sans:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/include/navbar.jsp"/>

<section class="hero">
    <div class="container">
        <h1>Bienvenido, <span>${sessionScope.usuario.nick}</span></h1>
        <p>Tu espacio personal en la Biblioteca Digital</p>
    </div>
</section>

<div class="container my-5">

    <div class="row g-4">

        <div class="col-md-4">
            <div class="card custom-card">
                <div class="card-body">
                    <i class="bi bi-book stat-icon"></i>
                    <h5>Gestión de Libros</h5>
                    <p>Administra tu colección digital.</p>
                    <a href="${pageContext.request.contextPath}/libros" class="btn btn-elegant">
                        Ir a Libros
                    </a>
                </div>
            </div>
        </div>

			<div class="col-md-4">
				<div class="card custom-card">
					<div class="card-body">
						<i class="bi bi-arrow-left-right stat-icon"></i>
						<h5>Préstamos</h5>
						<p>Gestiona los préstamos de libros.</p>

						<a href="${pageContext.request.contextPath}/prestamos"
							class="btn btn-elegant"> Ir a Préstamos </a>
					</div>
				</div>
			</div>

		</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>