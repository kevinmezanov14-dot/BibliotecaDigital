<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - Sistema</title>

<!-- CSS -->
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/css/login.css">
</head>
<body>
	<div class="login-container">
		<h1>Biblioteca Digital</h1>
		<h2>Login de Usuario</h2>

		<% if (request.getAttribute("error") != null) { %>
		<div class="error-message">
			<%= request.getAttribute("error") %>
		</div>
		<% } %>

		<% if ("success".equals(request.getParameter("logout"))) { %>
		<div class="success-message">Has cerrado sesión correctamente</div>
		<% } %>

		<form method="post" action="<%=request.getContextPath()%>/login">

			<div class="form-group">
				<label>Email</label> <input type="email" name="email" required>
			</div>

			<div class="form-group">
				<label>Password</label> <input type="password" name="password"
					required>
			</div>

			<button type="submit">Iniciar Sesión</button>
		</form>

		<div class="register-link">
			¿No tienes cuenta? <a href="<%=request.getContextPath()%>/registrar">Regístrate
				aquí</a>
		</div>
	</div>
</body>
</html>
