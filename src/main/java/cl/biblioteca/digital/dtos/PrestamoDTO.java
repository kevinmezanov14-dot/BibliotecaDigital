package cl.biblioteca.digital.dtos;

import java.time.LocalDateTime;

public class PrestamoDTO {

	private int id;
	private int clienteId;
	private int libroId;
	private LocalDateTime fechaSolicitud;
	private LocalDateTime fechaDevolucion;
	private LocalDateTime fechaVencimiento;
	private String estado;
	private String clienteNick;
	private String libroTitulo;

	public PrestamoDTO() {
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public int getLibroId() {
		return libroId;
	}

	public void setLibroId(int libroId) {
		this.libroId = libroId;
	}

	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public LocalDateTime getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getClienteNick() {
		return clienteNick;
	}

	public void setClienteNick(String clienteNick) {
		this.clienteNick = clienteNick;
	}

	public String getLibroTitulo() {
		return libroTitulo;
	}

	public void setLibroTitulo(String libroTitulo) {
		this.libroTitulo = libroTitulo;
	}
}