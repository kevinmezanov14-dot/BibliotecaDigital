package cl.biblioteca.digital.dtos;

public class LibroAutorDTO {

	private int idLibro;
	private String isbn;
	private String titulo;
	private int idAutor;
	private String nombreAutor;
	private int stock;

	// 🔹 Constructor vacío (obligatorio para JDBC / frameworks)
	public LibroAutorDTO() {
	}

	// 🔹 Constructor completo (útil para consultas JOIN)
	public LibroAutorDTO(int idLibro, String isbn, String titulo, int idAutor, String nombreAutor, int stock) {
	    this.idLibro = idLibro;
	    this.isbn = isbn;
	    this.titulo = titulo;
	    this.idAutor = idAutor;
	    this.nombreAutor = nombreAutor;
	    this.stock = stock;
	}

	// Getters y Setters
	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
