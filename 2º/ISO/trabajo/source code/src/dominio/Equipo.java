package dominio;

import persistencia.AgenteDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Equipo {
    public static final long MAX_PLAYER_COUNT = 10;
    private static final String PLAYER_SEPARATOR = ";";

    private String id;
    private String nombre;
    private String capitanRiotID;
    private List<String> playerRiotIDs;

    public Equipo(String id, String nombre, String capitanRiotID) {
        this.id = id;
        this.nombre = nombre;
        this.capitanRiotID = capitanRiotID;
        this.playerRiotIDs = new ArrayList<>();
    }

    public static int create(String id, String nombre, String capitanRiotID) throws Exception {
        String SQL = "INSERT INTO equipos (id, nombre, capitan_riotid, player_riotids) VALUES ('" + id + "', '" +
                nombre + "', '" + capitanRiotID + "', '')";
        return AgenteDB.getAgente().insert(SQL);
    }

    public static Equipo read(String id) throws Exception {
        String SQL = "SELECT * FROM equipos WHERE id = '" + id + "'";
        Vector<Object> result = AgenteDB.getAgente().select(SQL);

        if (result.isEmpty()) return null;

        Vector<Object> fila = (Vector<Object>) result.get(0);
        Equipo equipo = new Equipo(fila.get(0).toString(), fila.get(1).toString(), fila.get(2).toString());
        if (fila.get(3) != null && !fila.get(3).toString().isEmpty()) {
            String[] riotIDs = fila.get(3).toString().split(PLAYER_SEPARATOR);
            for (String riotID : riotIDs)
                equipo.playerRiotIDs.add(riotID);
        }
        return equipo;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCapitanRiotID() {
        return capitanRiotID;
    }

    public List<String> getPlayerRiotIDs() {
        return playerRiotIDs;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", capitanRiotID='" + capitanRiotID + '\'' +
                ", playerRiotIDs=" + playerRiotIDs +
                '}';
    }
}
