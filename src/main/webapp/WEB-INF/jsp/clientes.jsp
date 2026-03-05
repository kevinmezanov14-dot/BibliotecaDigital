<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Clientes</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">

</head>
<body>

<jsp:include page="/WEB-INF/jsp/include/navbar.jsp"/>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="elegant-title">Gestión de Clientes</h3>
        <button class="btn btn-elegant" data-bs-toggle="collapse" data-bs-target="#nuevoCliente">
            + Nuevo Cliente
        </button>
    </div>

    <!-- FORMULARIO NUEVO CLIENTE -->
    <div id="nuevoCliente" class="collapse mb-4">
        <div class="card card-elegant p-4">
            <form action="${pageContext.request.contextPath}/clientes" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Nick</label>
                        <input type="text" name="nick" class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-elegant">
                            Registrar Cliente
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
                </tr>
            </thead>
            <tbody>
                <c:forEach var="c" items="${clientes}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.nick}</td>
                        <td>${c.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>