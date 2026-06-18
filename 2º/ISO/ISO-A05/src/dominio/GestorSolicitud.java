package dominio;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class GestorSolicitud {
    private static boolean comprobarHueco(Equipo equipo) {
        boolean hueco = true;
        List<String> riotIds = equipo.getPlayerRiotIDs();
        if (riotIds.size() >= Equipo.MAX_PLAYER_COUNT) {
            hueco = false;
        }
        return hueco;
    }

    private static boolean tienePreviaSolicitud(String riotID, String equipoID) throws Exception {
        boolean found = false;

        List<String> solicitudIds = Solicitud.getSolicitudesEquipo(equipoID);
        for (int i = 0; i < solicitudIds.size() && !found; i++) {
            String solicitudId = solicitudIds.get(i);
            Solicitud solicitud = Solicitud.read(solicitudId);
            if (riotID.equals(solicitud.getSolicitanteID())) {
                found = true;
            }
        }

        return found;
    }

    private static boolean yaEstaEnEquipo(String riotID, Equipo equipo) throws Exception {
        boolean found = false;

        List<String> playerRiotIDs = equipo.getPlayerRiotIDs();
        for (int i = 0; i < playerRiotIDs.size() && !found; i++) {
            String playerRiotID = playerRiotIDs.get(i);
            if (riotID.equals(playerRiotID)) {
                found = true;
            }
        }
        return found;
    }

    public static void solicitar(Component parent, String riotID, String idEquipo) {
        try {
            // Check equipo existe
            Equipo equipo = Equipo.read(idEquipo);

            // Check jugador en el equipo
            boolean yaEstaEnEquipo = yaEstaEnEquipo(riotID, equipo);
            if (yaEstaEnEquipo) {
                JOptionPane.showMessageDialog(parent, "Ya esta en este equipo",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check hueco
            boolean hayHueco = comprobarHueco(equipo);
            if(!hayHueco) {
                JOptionPane.showMessageDialog(parent, "No hay hueco en este equipo",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check prev solicitud
            boolean tienePreviaSolicitud = tienePreviaSolicitud(riotID, idEquipo);
            if (tienePreviaSolicitud) {
                JOptionPane.showMessageDialog(parent, "Ya solicitaste unirte a este equipo",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String solicitudID = UUID.randomUUID().toString();
            int res = Solicitud.create(solicitudID, riotID, idEquipo, "pendiente");
            if (res != 1) {
                JOptionPane.showMessageDialog(parent, "Error al crear solicitud",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(parent, "Solicitud enviada.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Equipo no encontrado",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
