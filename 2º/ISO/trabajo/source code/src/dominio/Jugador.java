package dominio;

import persistencia.AgenteDB;

import java.util.Vector;
public class Jugador extends Usuario {

    private final String riotID;
    private final String nombre;

    Jugador(String usuario, String contrasena, String riotID, String nombre) throws Exception {
        super(usuario, contrasena, "jugador");
        this.riotID = riotID;
        this.nombre = nombre;
    }

    public static int create(String login, String contraseña, String riotID, String nombre) throws Exception {
        String SQL = "INSERT INTO usuarios (user, password, type, riotid, nombre) VALUES ('" + login + "', '"
                + contraseña + "', 'jugador', '" + riotID + "', '" + nombre + "')";
        return AgenteDB.getAgente().insert(SQL);
    }

    public int añadirInfraccion(String idinfraccion, String riotID, String tipo, String descripcion) throws Exception {
        return Infraccion.create(idinfraccion, riotID, tipo, descripcion);
    }

    public void update(String SQL) {
        AgenteDB a;
        try {
            a = AgenteDB.getAgente();
            a.update(SQL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Vector<Infraccion> getHistorialInfracciones() throws Exception {
        return Infraccion.readInfraccionesJugador(this.riotID);
    }

    public String getRiotID() {
        return riotID;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "riotID='" + riotID + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

