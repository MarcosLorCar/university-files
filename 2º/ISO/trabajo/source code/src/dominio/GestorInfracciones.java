package dominio;

public class GestorInfracciones {

    // Caso de uso: Registrar infracción

    public String apuntarInfraccion(String idinfraccion, String riotID, String tipo, String descripcion)
            throws Exception {
        String msg = null;
        // Buscar jugador
        Jugador jugador = Jugador.read(riotID);

        // Escenario alternativo (jugador no encontrado)
        if (jugador == null) {
            msg = "Jugador no encontrado";
        } else {
            // Añadir infracción al jugador
            int res = jugador.añadirInfraccion(idinfraccion, riotID, tipo, descripcion);

            if (res != 1) {
                msg = "Error al registrar la infraccion";
            } else {
                msg = "Infracción registrada correctamente";
            }
        }
        return msg;
    }
}
