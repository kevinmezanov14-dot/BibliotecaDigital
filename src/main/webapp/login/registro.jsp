<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro - Biblioteca Digital</title>
    <!-- CSS específico para registro -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/registro.css">
</head>
<body>
    <div class="register-container">
        <h1>Biblioteca Digital</h1>
        <h2>Registro de Usuario</h2>

        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <form method="post" action="<%= request.getContextPath() %>/registrar">
            <div class="form-group">
                <label for="nick">Nick</label>
                <input type="text" name="nick" id="nick" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" required>
            </div>

            <div class="form-group">
                <label for="fechaNacimiento">Fecha Nacimiento</label>
                <input type="date" name="fechaNacimiento" id="fechaNacimiento" required>
            </div>

            <button type="submit">Registrarse</button>
        </form>

        <div class="login-link">
            ¿Ya tienes cuenta? <a href="<%= request.getContextPath() %>/login">Inicia sesión</a>
        </div>
    </div>
</body>
</html>
