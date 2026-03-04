<style>
.lib-footer {
	background-color: #1b2a3b;
	color: #cfd8dc;
}

.lib-footer .footer-brand {
	font-size: 1.4rem;
	font-weight: 700;
	color: #fff;
	letter-spacing: 0.02em;
}

.lib-footer .footer-tagline {
	font-size: 0.75rem;
	letter-spacing: 0.15em;
	text-transform: uppercase;
	color: #f0b429;
}

.lib-footer .footer-desc {
	font-size: 0.875rem;
	color: #90a4ae;
	line-height: 1.75;
}

.lib-footer h6 {
	color: #fff;
	font-size: 0.85rem;
	text-transform: uppercase;
	letter-spacing: 0.12em;
	padding-bottom: 10px;
	border-bottom: 2px solid #c0392b;
	display: inline-block;
	margin-bottom: 1rem;
}

.lib-footer .footer-links {
	list-style: none;
	padding: 0;
	margin: 0;
}

.lib-footer .footer-links li {
	margin-bottom: 8px;
}

.lib-footer .footer-links a {
	color: #90a4ae;
	text-decoration: none;
	font-size: 0.875rem;
	transition: color 0.2s, padding-left 0.2s;
	display: inline-block;
}

.lib-footer .footer-links a:hover {
	color: #f0b429;
	padding-left: 5px;
}

.lib-footer .hours-table td {
	font-size: 0.82rem;
	padding: 3px 8px 3px 0;
	color: #90a4ae;
}

.lib-footer .hours-table td:last-child {
	color: #cfd8dc;
	font-weight: 500;
}

.lib-footer .hours-table tr.today td {
	color: #f0b429;
	font-weight: 700;
}

.lib-footer .contact-item {
	font-size: 0.875rem;
	color: #90a4ae;
	margin-bottom: 10px;
	display: flex;
	align-items: flex-start;
	gap: 10px;
}

.lib-footer .contact-item i {
	color: #c0392b;
	font-size: 1rem;
	margin-top: 2px;
	flex-shrink: 0;
}

.lib-footer .contact-item a {
	color: #90a4ae;
	text-decoration: none;
	transition: color 0.2s;
}

.lib-footer .contact-item a:hover {
	color: #f0b429;
}

.lib-footer .social-btn {
	width: 36px;
	height: 36px;
	border: 1px solid rgba(255, 255, 255, 0.15);
	border-radius: 50%;
	display: inline-flex;
	align-items: center;
	justify-content: center;
	color: #90a4ae;
	text-decoration: none;
	font-size: 0.9rem;
	transition: all 0.25s;
}

.lib-footer .social-btn:hover {
	border-color: #f0b429;
	color: #f0b429;
	background: rgba(240, 180, 41, 0.08);
}

.lib-footer .footer-bottom {
	background-color: #14212e;
	font-size: 0.78rem;
	color: rgba(255, 255, 255, 0.35);
}

.lib-footer .footer-bottom a {
	color: rgba(255, 255, 255, 0.35);
	text-decoration: none;
	transition: color 0.2s;
}

.lib-footer .footer-bottom a:hover {
	color: #f0b429;
}

.lib-footer .divider-accent {
	height: 4px;
	background: repeating-linear-gradient(90deg, #c0392b 0px, #c0392b 40px, #f0b429 40px
		, #f0b429 50px, #1b2a3b 50px, #1b2a3b 60px);
}
</style>

<footer class="lib-footer" role="contentinfo">

	<div class="divider-accent"></div>

	<div class="container py-5">
		<div class="row g-4">

			<!-- MARCA -->
			<div class="col-12 col-md-6 col-lg-4">
				<div class="footer-brand mb-1">
					<i class="bi bi-book-half me-2 text-warning"></i>Biblioteca Central
				</div>
				<p class="footer-tagline mb-3">Conocimiento sin fronteras</p>
				<p class="footer-desc">Más de 80&nbsp;000 títulos a tu
					disposición. Catálogos digitales, sala de estudio, préstamo en
					línea y talleres culturales todo el año.</p>
				<div class="d-flex gap-2 mt-3">
					<a href="#" class="social-btn" aria-label="Facebook"><i
						class="bi bi-facebook"></i></a> <a href="#" class="social-btn"
						aria-label="Instagram"><i class="bi bi-instagram"></i></a> <a
						href="#" class="social-btn" aria-label="Twitter"><i
						class="bi bi-twitter-x"></i></a> <a href="#" class="social-btn"
						aria-label="YouTube"><i class="bi bi-youtube"></i></a>
				</div>
			</div>

			<!-- HORARIOS -->
			<div class="col-6 col-md-3 col-lg-3">
				<h6>Horarios</h6>
				<table class="hours-table w-100">
					<tbody>
						<tr>
							<td>Lunes</td>
							<td>08:00 – 20:00</td>
						</tr>
						<tr>
							<td>Martes</td>
							<td>08:00 – 20:00</td>
						</tr>
						<tr>
							<td>Miércoles</td>
							<td>08:00 – 20:00</td>
						</tr>
						<tr>
							<td>Jueves</td>
							<td>08:00 – 20:00</td>
						</tr>
						<tr>
							<td>Viernes</td>
							<td>08:00 – 18:00</td>
						</tr>
						<tr>
							<td>Sábado</td>
							<td>09:00 – 14:00</td>
						</tr>
						<tr>
							<td>Domingo</td>
							<td>Cerrado</td>
						</tr>
					</tbody>
				</table>
			</div>

			<!-- CONTACTO -->
			<div class="col-12 col-md-6 col-lg-3">
				<h6>Contacto</h6>
				<div class="contact-item">
					<i class="bi bi-geo-alt-fill"></i> <span>Av. Cultura 1234,
						Piso 2<br>Ciudad, CP 00000
					</span>
				</div>
				<div class="contact-item">
					<i class="bi bi-telephone-fill"></i> <a href="tel:+1234567890">+1
						(234) 567-890</a>
				</div>
				<div class="contact-item">
					<i class="bi bi-envelope-fill"></i> <a
						href="mailto:info@bibliotecacentral.org">info@bibliotecacentral.org</a>
				</div>
				<div class="contact-item">
					<i class="bi bi-globe2"></i> <a
						href="${pageContext.request.contextPath}/">www.bibliotecacentral.org</a>
				</div>
				<div class="mt-3">
					<a href="${pageContext.request.contextPath}/newsletter"
						class="btn btn-outline-warning btn-sm w-100"> <i
						class="bi bi-envelope-paper me-1"></i> Suscribirse al boletín
					</a>
				</div>
			</div>

		</div>
	</div>

</footer>
