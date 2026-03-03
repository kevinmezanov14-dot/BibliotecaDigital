package cl.biblioteca.digital.model;

public class Autor {

	private int id;
	private String nombre;

	// Constructor vacío (OBLIGATORIO para DAO / JDBC / JSP)
	public Autor() {
	}

	// Constructor con parámetros (útil cuando ya tienes los datos)
	public Autor(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
