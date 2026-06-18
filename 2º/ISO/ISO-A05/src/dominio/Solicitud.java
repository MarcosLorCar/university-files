package dominio;

import persistencia.AgenteDB;

import java.util.List;
import java.util.Vector;

public class Solicitud {
    private String solicitudID;
    private String solicitanteID;
    private String equipoID;
    private String estado;

    public Solicitud(String solicitudID, String solicitanteID, String equipoID, String estado) {
        this.solicitudID = solicitudID;
        this.solicitanteID = solicitanteID;
        this.equipoID = equipoID;
        this.estado = estado;
    }

    public static int create(String solicitudID, String solicitanteID, String equipoID, String estado) throws Exception {
        String SQL = "INSERT INTO solicitudes (idsolicitud, solicitanteID, equipoID, estado) VALUES ('" + solicitudID + "', '" + solicitanteID + "', '" + equipoID + "', '" + estado + "')";
        return AgenteDB.getAgente().insert(SQL);
    }

    public static Solicitud read(String solicitudID) throws Exception {
        String SQL = "SELECT * FROM solicitudes WHERE idsolicitud = '" + solicitudID + "'";
        Vector<Object> result = AgenteDB.getAgente().select(SQL);

        if (result.isEmpty()) {
            return null;
        }

        Vector<Object> fila = (Vector<Object>) result.get(0);
        return new Solicitud(fila.get(0).toString(), fila.get(1).toString(), fila.get(2).toString(), fila.get(3).toString());
    }

    public static List<String> getSolicitudesEquipo(String equipoId) throws Exception {
        String SQL = "SELECT idsolicitud FROM solicitudes WHERE equipoID = '" + equipoId + "'";
        Vector<Object> result = AgenteDB.getAgente().select(SQL);
        List<String> solicitudIds = new Vector<>();

        for (Object fila : result) {
            Vector<Object> row = (Vector<Object>) fila;
            solicitudIds.add(row.get(0).toString());
        }

        return solicitudIds;
    }

    public String getSolicitudID() {
        return solicitudID;
    }

    public String getSolicitanteID() {
        return solicitanteID;
    }

    public String getEquipoID() {
        return equipoID;
    }

    public String getEstado() {
        return estado;
    }

    public String toString() {
        return "Solicitud [ID=" + solicitudID + ", EquipoID=" + equipoID + ", Estado=" + estado + "]";
    }
}
