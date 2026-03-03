package cl.biblioteca.digital.dtos;

public class LibroDTO {

    private int id;
    private String titulo;
    private String isbn;
    private int anio;
    private int autorId;
    private int stock;
    private String autorNombre;

    public LibroDTO() {
    }

    public LibroDTO(String titulo, String isbn, int anio, int autorId, int stock) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anio = anio;
        this.autorId = autorId;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public int getAutorId() { return autorId; }
    public void setAutorId(int autorId) { this.autorId = autorId; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getAutorNombre() { return autorNombre; }
    public void setAutorNombre(String autorNombre) { this.autorNombre = autorNombre; }
}