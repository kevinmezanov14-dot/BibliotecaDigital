# Biblioteca Digital 

Aplicación web dinámica desarrollada con Java EE para la gestión de libros, usuarios y préstamos de una biblioteca digital.

---

## 🛠️ Tecnologías utilizadas

- **Java EE** — Servlets, JSP, JSTL
- **Patrón MVC** — Model / View / Controller
- **Patrón DAO** — Acceso a datos con JDBC
- **MySQL** — Base de datos relacional
- **Apache Tomcat 11** — Servidor de aplicaciones
- **Bootstrap 5.3** — Estilos y componentes UI
- **Eclipse Enterprise Edition** — IDE de desarrollo

---

## 📁 Estructura del proyecto

```
BibliotecaDigital/
├── src/main/java/
│   └── cl/biblioteca/digital/
│       ├── clientes/           → Servlet de clientes
│       ├── daos/               → Interfaces e implementaciones DAO
│       ├── dtos/               → Objetos de transferencia de datos
│       ├── libros/             → Servlets de libros
│       ├── login/              → Servlets de login y logout
│       ├── model/              → Modelos de entidades
│       ├── prestamos/          → Servlet de préstamos
│       ├── servicios/          → Capa de servicios
│       ├── servlets/           → Servlets generales
│       └── util/               → Conexión, filtros y utilidades
├── src/main/resources/
│   └── db.properties           → Configuración de base de datos
└── src/main/webapp/
    ├── autores/                → JSP registro de autores
    ├── css/                    → Hojas de estilo
    ├── dashboard/              → JSP dashboard
    ├── libros/                 → JSPs de gestión de libros
    ├── login/                  → JSPs de login y registro
    └── WEB-INF/
    │   ├── jsp/
    │   │   ├── include/        → navbar.jsp, footer.jsp
    │   │   ├── clientes.jsp
    │   │   └── prestamos.jsp
    │   └── web.xml
    ├── index.jsp
```

---

## Configuración

### 1. Base de datos

Crear la base de datos en MySQL:

```sql
CREATE DATABASE biblioteca_digital CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Ejecutar el script SQL de creación de tablas:

```sql
CREATE DATABASE biblioteca_digital;
USE biblioteca_digital;

CREATE TABLE autores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(100)
);

CREATE TABLE libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    isbn VARCHAR(50),
    anio INT,
    autor_id INT,
    stock INT DEFAULT 0,
    FOREIGN KEY (autor_id) REFERENCES autores(id)
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nick VARCHAR(50) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nick VARCHAR(50) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    fecha_nacimiento DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    libro_id INT NOT NULL,
    fecha_solicitud TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_devolucion DATETIME NULL,
    fecha_vencimiento DATETIME NOT NULL,
    estado VARCHAR(50) NOT NULL,
    CONSTRAINT fk_prestamo_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_prestamo_libro   FOREIGN KEY (libro_id)   REFERENCES libros(id)
);

-- Datos de ejemplo
INSERT INTO autores (nombre, nacionalidad) VALUES
    ('Gabriela Mistral', 'Chilena'),
    ('Isabel Allende', 'Chilena'),
    ('Julio Cortázar', 'Argentina');
```

### 2. Archivo a crear `db.properties`

Ubicado en `src/main/resources/db.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/biblioteca_digital?useSSL=false&serverTimezone=UTC
db.user=root
db.password=tu_password
```

### 3. Configuración en Eclipse

1. Abrir Eclipse Enterprise Edition
2. Importar el proyecto: **File → Import → Existing Projects into Workspace**
3. Seleccionar la carpeta del proyecto
4. Verificar que Apache Tomcat 11 esté configurado en **Window → Preferences → Server → Runtime Environments**

---

## Despliegue

### Opción A — Desde Eclipse

1. Clic derecho sobre el proyecto → **Run As → Run on Server**
2. Seleccionar Apache Tomcat 11
3. Acceder a: `http://localhost:8080/BibliotecaDigital`

### Opción B — Archivo WAR

1. Clic derecho sobre el proyecto → **Export → WAR file**
2. Seleccionar destino y confirmar
3. Copiar el `.WAR` a la carpeta `webapps/` de Tomcat
4. Iniciar Tomcat y acceder a: `http://localhost:8080/BibliotecaDigital`

---

## Acceso a la aplicación

1. Registrarse en: `http://localhost:8080/BibliotecaDigital/registrar`
2. Iniciar sesión en: `http://localhost:8080/BibliotecaDigital/login`
3. Serás redirigido al dashboard principal

---

## Funcionalidades

| Módulo     | Funcionalidad |
|------------|--------------|
| Usuarios   | Registro, login y logout con gestión de sesión |
| Libros     | Listar, registrar, editar y eliminar libros |
| Autores    | Registrar autores con nombre y nacionalidad |
| Clientes   | Listar y registrar clientes |
| Préstamos  | Registrar préstamos, devolver libros, control de stock y estados |

---

## Arquitectura MVC

```
┌─────────────┐         ┌──────────────┐        ┌──────────────┐
│    Vista    │  ────▶ │ Controlador   │ ────▶ │   Modelo     │
│   (JSP)     │ ◀───── │ (Servlet)     │ ◀──── │ (DAO + DTO)  │
└─────────────┘         └──────────────┘        └──────────────┘
                                                         │
                                                  ┌─────────────┐
                                                  │   MySQL DB  │
                                                  └─────────────┘
```
## Patrones de diseño utilizados

- **MVC (Model View Controller)** — Separación entre la vista, la lógica de negocio y el acceso a datos.
- **DAO (Data Access Object)** — Encapsula las operaciones de acceso a la base de datos.
- **DTO (Data Transfer Object)** — Objetos utilizados para transportar datos entre capas.
- **Service Layer** — Centraliza la lógica de negocio entre los Servlets y los DAOs.
- **Singleton** — Implementado en la clase `Conexion` para gestionar una única instancia de conexión.
  
### Capas del proyecto

- **Vista** — JSPs con JSTL, Bootstrap 5 y CSS personalizado
- **Controlador** — Servlets con anotaciones `@WebServlet`
- **Servicio** — Lógica de negocio desacoplada del controlador
- **DAO** — Acceso a datos con interfaces e implementaciones JDBC
- **Modelo** — Entidades que representan las tablas de la BD
- **DTO** — Objetos de transferencia entre capas

---

## Seguridad

- `FiltroAutenticacion` protege todas las rutas privadas
- Rutas públicas: `/login`, `/registrar`, `/css/`, `/js/`
- Sesiones gestionadas con `HttpSession`
- Validaciones del lado del servidor en todos los formularios
- Uso de `PreparedStatement` para prevenir SQL Injection
- `c:out` en JSPs para prevenir XSS

---

## Notas técnicas

- Las contraseñas se hashean con **BCrypt** antes de almacenarse en la base de datos. La verificación se realiza con `BCrypt.checkpw()` sin necesidad de desencriptar.
- El driver MySQL se incluye en `WEB-INF/lib/mysql-connector-j-9.6.0.jar`.
- La librería JSTL se incluye en `WEB-INF/lib/jakarta.servlet.jsp.jstl-3.0.1.jar`.

---

## Desarrollo

Proyecto desarrollado como evaluación del **Módulo 5: Desarrollo de aplicaciones web dinámicas en Java** — Alkemy.

## Autor

Proyecto desarrollado por **Kevin Meza Catril**  
Como parte del **Módulo 5: Desarrollo de aplicaciones web dinámicas en Java — Alkemy**.
