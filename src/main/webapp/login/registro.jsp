<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registro - Biblioteca Digital</title>
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=DM+Sans:wght@400;500;600&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/registro.css">
</head>
<body>
	<div class="register-container">
		<h1>Biblioteca Digital</h1>
		<h2>Registro de Usuario</h2>

		<c:if test="${not empty error}">
			<div class="error-message">
				<c:out value="${error}" />
			</div>
		</c:if>

		<form method="post"
			action="${pageContext.request.contextPath}/registrar">

			<div class="form-group">
				<label for="nick">Nick</label> <input type="text" name="nick"
					id="nick" required autocomplete="username">
			</div>

			<div class="form-group">
				<label for="email">Email</label> <input type="email" name="email"
					id="email" required autocomplete="email">
			</div>

			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					name="password" id="password" required autocomplete="new-password">
			</div>

			<div class="form-group">
				<label for="fechaNacimiento">Fecha de Nacimiento</label> <input
					type="date" name="fechaNacimiento" id="fechaNacimiento" required>
			</div>

			<button type="submit">Registrarse</button>
		</form>

		<div class="login-link">
			¿Ya tienes cuenta? <a href="${pageContext.request.contextPath}/login">Inicia
				sesión</a>
		</div>
	</div>
</body>
</html>
