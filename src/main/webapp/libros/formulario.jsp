<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrar Libro - Biblioteca Digital</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0;
	padding: 20px;
}

.container {
	background: white;
	padding: 40px;
	border-radius: 12px;
	box-shadow: 0 10px 40px rgba(0,0,0,0.2);
	max-width: 600px;
	width: 100%;
}

h1 {
	color: #333;
	margin-bottom: 10px;
	font-size: 28px;
}

.subtitle {
	color: #666;
	margin-bottom: 30px;
	font-size: 14px;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 8px;
	color: #333;
	font-weight: 600;
	font-size: 14px;
}

input[type="text"],
input[type="number"],
select {
	width: 100%;
	padding: 12px 16px;
	border: 2px solid #e0e0e0;
	border-radius: 6px;
	font-size: 14px;
	transition: all 0.3s ease;
	box-sizing: border-box;
}

input[type="text"]:focus,
input[type="number"]:focus,
select:focus {
	outline: none;
	border-color: #667eea;
	box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

input[type="text"]:hover,
input[type="number"]:hover,
select:hover {
	border-color: #667eea;
}

.btn {
	padding: 12px 24px;
	border: none;
	border-radius: 6px;
	font-size: 16px;
	font-weight: 600;
	cursor: pointer;
	transition: all 0.3s ease;
	text-decoration: none;
	display: inline-block;
}

.btn-primary {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: white;
	width: 100%;
}

.btn-primary:hover {
	transform: translateY(-2px);
	box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
}

.btn-secondary {
	background-color: #6c757d;
	color: white;
	margin-right: 10px;
}

.btn-secondary:hover {
	background-color: #5a6268;
}

.alert {
	padding: 15px 20px;
	margin-bottom: 25px;
	border-radius: 6px;
	font-size: 14px;
	font-weight: 500;
}

.alert-danger {
	background-color: #f8d7da;
	color: #721c24;
	border: 1px solid #f5c6cb;
}

.alert-success {
	background-color: #d4edda;
	color: #155724;
	border: 1px solid #c3e6cb;
}

.form-actions {
	display: flex;
	gap: 10px;
	margin-top: 30px;
}

.required {
	color: #e74c3c;
}

.help-text {
	font-size: 12px;
	color: #666;
	margin-top: 5px;
}

.back-link {
	text-align: center;
	margin-top: 20px;
}

.back-link a {
	color: #667eea;
	text-decoration: none;
	font-weight: 500;
}

.back-link a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="container">
		<h1>📚 Registrar Nuevo Libro</h1>
		<p class="subtitle">Completa los datos del libro para agregarlo al catálogo</p>

		<!-- Mensajes de Error -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger">
				❌ <c:out value="${error}" />
			</div>
		</c:if>

		<!-- Formulario -->
		<form action="${pageContext.request.contextPath}/libros/registrar" method="post">
			
			<!-- Título -->
			<div class="form-group">
				<label for="titulo">
					Título <span class="required">*</span>
				</label>
				<input type="text" 
				       id="titulo" 
				       name="titulo" 
				       placeholder="Ej: Cien años de soledad"
				       required 
				       maxlength="255"
				       autofocus>
			</div>

			<!-- ISBN -->
			<div class="form-group">
				<label for="isbn">
					ISBN <span class="required">*</span>
				</label>
				<input type="text" 
				       id="isbn" 
				       name="isbn" 
				       placeholder="Ej: 978-3-16-148410-0"
				       required 
				       maxlength="50">
				<div class="help-text">Código internacional del libro</div>
			</div>

			<!-- Año -->
			<div class="form-group">
				<label for="anio">
					Año de Publicación <span class="required">*</span>
				</label>
				<input type="number" 
				       id="anio" 
				       name="anio" 
				       placeholder="Ej: 2024"
				       required 
				       min="1000" 
				       max="2100"
				       value="2024">
			</div>

			<!-- Autor -->
			<div class="form-group">
				<label for="autor">
					Autor <span class="required">*</span>
				</label>
				<select id="autor" name="autor" required>
					<option value="">-- Seleccione un autor --</option>
					<c:forEach var="autor" items="${autores}">
						<option value="${autor.id}">
							<c:out value="${autor.nombre}" />
						</option>
					</c:forEach>
				</select>
				<div class="help-text">
					¿No encuentras el autor? 
					<a href="${pageContext.request.contextPath}/autores/registrar" target="_blank">
						Regístralo aquí
					</a>
				</div>
			</div>

			<!-- Stock -->
			<div class="form-group">
				<label for="stock">
					Stock Inicial
				</label>
				<input type="number" 
				       id="stock" 
				       name="stock" 
				       placeholder="Ej: 10"
				       min="0" 
				       max="9999"
				       value="0">
				<div class="help-text">Cantidad de ejemplares disponibles (por defecto: 0)</div>
			</div>

			<!-- Botones -->
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">
					💾 Guardar Libro
				</button>
			</div>

		</form>

		<!-- Link de regreso -->
		<div class="back-link">
			<a href="${pageContext.request.contextPath}/libros">
				← Volver al listado de libros
			</a>
		</div>
	</div>
</body>
</html>