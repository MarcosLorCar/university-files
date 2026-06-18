package dominio;

import persistencia.AgenteDB;

import java.util.Vector;

public class Infraccion {
    private String idinfraccion;
    private String riotID_jugador;
    private String tipo;
    private String descripcion;
    private java.sql.Date fecha;

    public Infraccion(String idinfraccion, String riotID_jugador, String tipo, String descripcion, java.sql.Date fecha) {
        this.idinfraccion = idinfraccion;
        this.riotID_jugador = riotID_jugador;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public static Vector<Infraccion> readInfraccionesJugador(String riotID) throws Exception {
        Vector<Infraccion> infracciones = new Vector<>();
        AgenteDB a = AgenteDB.getAgente();
        String sql = "SELECT * FROM infracciones WHERE riotID_jugador = '" + riotID + "'";
        Vector<Object> filas = a.select(sql);

        for (Object obj : filas) {
            Vector<Object> fila = (Vector<Object>) obj;

            java.sql.Date sqlFecha;
            Object fechaObj = fila.get(4);
            if (fechaObj instanceof java.sql.Date) {
                sqlFecha = (java.sql.Date) fechaObj;
            } else if (fechaObj instanceof java.util.Date) {
                sqlFecha = new java.sql.Date(((java.util.Date) fechaObj).getTime());
            } else {
                String dateStr = fechaObj.toString();
                if (dateStr.length() > 10) {
                    dateStr = dateStr.substring(0, 10);
                }
                sqlFecha = java.sql.Date.valueOf(dateStr);
            }

            Infraccion inf = new Infraccion(fila.get(0).toString(), fila.get(1).toString(), fila.get(2).toString(), fila.get(3).toString(), sqlFecha);
            infracciones.add(inf);
        }

        return infracciones;
    }

    public static int create(String idinfraccion, String riotID, String tipo, String descripcion) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String dateString = sqlDate.toString();
        String SQL = "INSERT INTO infracciones (idinfraccion, riotID_jugador, tipo, descripcion, date) VALUES ('"
                + idinfraccion + "', '" + riotID + "', '" + tipo + "', '" + descripcion + "', '" + dateString + "')";
        return AgenteDB.getAgente().insert(SQL);
    }

    @Override
    public String toString() {
        return "[" + fecha.toString() + "] (" + tipo + ") " + descripcion;
    }
}
