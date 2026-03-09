<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - Biblioteca Digital UNTEC</title>
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=DM+Sans:wght@400;500;600&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<div class="login-container">
		<h1>Biblioteca Digital</h1>
		<h2>Iniciar Sesión</h2>

		<c:if test="${not empty requestScope.error}">
			<div class="error-message">
				<c:out value="${requestScope.error}" />
			</div>
		</c:if>

		<c:if test="${param.logout == 'success'}">
			<div class="success-message">Has cerrado sesión correctamente</div>
		</c:if>

		<form method="post" action="${pageContext.request.contextPath}/login">
			<div class="form-group">
				<label>Email</label> <input type="email" name="email" required
					autocomplete="email">
			</div>
			<div class="form-group">
				<label>Password</label> <input type="password" name="password"
					required autocomplete="current-password">
			</div>
			<button type="submit">Iniciar Sesión</button>
		</form>

		<div class="register-link">
			¿No tienes cuenta? <a
				href="${pageContext.request.contextPath}/registrar">Regístrate
				aquí</a>
		</div>
	</div>
</body>
</html>
