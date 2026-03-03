package cl.biblioteca.digital.model;

import java.sql.Date;

public class Usuario {
	private int id;
	private String nick;
	private String email;
	private String password;
	private Date fechaNacimiento;
	private boolean activo;

	// Constructores
	public Usuario() {
	}

	public Usuario(String nick, String email, String password, Date fechaNacimiento) {
		this.nick = nick;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.activo = true;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}