package cl.biblioteca.digital.model;

public class Cliente {

    private int id;
    private String nick;
    private String email;

    // Constructor vacío
    public Cliente() {}

    // Constructor con parámetros
    public Cliente(int id, String nick, String email) {
        this.id = id;
        this.nick = nick;
        this.email = email;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}