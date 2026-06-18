package dominio;

import persistencia.AgenteDB;

import java.util.Vector;

public class Usuario {
    private final String login;
    private final String contraseña;
    private final String tipo;

    Usuario(String login, String contraseña, String tipo) {
        this.login = login;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    @SuppressWarnings("unchecked")
    public static Usuario read(String usuario, String contrasena) throws Exception {
        Usuario cuenta = null;
        String SQL = "SELECT * FROM usuarios WHERE user = '" + usuario + "' AND password = '" + contrasena + "'";
        Vector<Object> aux = null;
        Vector<Object> vectorDevolver = AgenteDB.getAgente().select(SQL);
        if (!vectorDevolver.isEmpty()) {
            aux = (Vector<Object>) vectorDevolver.get(0);
            String login = aux.get(0).toString();
            String pass = aux.get(1).toString();
            String tipo = aux.get(2).toString();
            if (tipo.equals("jugador")) {
                String riotID = aux.get(3).toString();
                String nombre = aux.get(4).toString();
                cuenta = new Jugador(login, pass, riotID, nombre);
            } else {
                cuenta = new Usuario(login, pass, tipo);
            }
        }
        return cuenta;
    }

    @SuppressWarnings("unchecked")
    public static Jugador read(String riotid) throws Exception {
        Jugador cuenta = null;
        String SQL = "SELECT * FROM usuarios WHERE riotid = '" + riotid + "'";
        Vector<Object> aux = null;
        Vector<Object> vectorDevolver = AgenteDB.getAgente().select(SQL);
        if (!vectorDevolver.isEmpty()) {
            aux = (Vector<Object>) vectorDevolver.get(0);
            String login = aux.get(0).toString();
            String pass = aux.get(1).toString();
            String tipo = aux.get(2).toString();
            String riotID = aux.get(3).toString();
            String nombre = aux.get(4).toString();
            cuenta = new Jugador(login, pass, riotID, nombre);
        }
        return cuenta;
    }

    public static int create(String usuario, String contrasena) throws Exception {
        String SQL = "INSERT INTO usuarios (user, password, type) VALUES ('" + usuario + "', '"
                + contrasena + "', 'usuario')";
        return AgenteDB.getAgente().insert(SQL);
    }

    public int delete() throws Exception {
        String SQL = "DELETE FROM usuarios WHERE user = '" + login + "'";
        return AgenteDB.getAgente().delete(SQL);
    }

    @Override
    public String toString() {
        return "Usuario: " + login + " Contrasena: " + contraseña + " Tipo: " + tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLogin() {
        return login;
    }

    public String getContraseña() {
        return contraseña;
    }
}