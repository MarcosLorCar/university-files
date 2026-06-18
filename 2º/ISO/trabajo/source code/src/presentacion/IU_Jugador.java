package presentacion;

import dominio.*;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;
import java.util.Vector;

public class IU_Jugador extends JFrame {

    private JTextArea textAreaInfo;
    private JTextArea textAreaIdEquipo;
    private JPanel contentPane;

    public IU_Jugador(Jugador jugador) throws Exception {

        setTitle("Panel de Jugador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 500);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Bienvenido, " + jugador.getNombre());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(20, 15, 400, 25);
        contentPane.add(lblTitulo);

        // Información del jugador
        JLabel lblInfo = new JLabel("Información personal:");
        lblInfo.setBounds(20, 50, 200, 20);
        contentPane.add(lblInfo);

        textAreaInfo = new JTextArea();
        textAreaInfo.setEditable(false);
        textAreaInfo.setBounds(20, 75, 500, 60);
        textAreaInfo.setText("Riot ID: " + jugador.getRiotID() + "\n" +
                "Nombre: " + jugador.getNombre());
        contentPane.add(textAreaInfo);

        // Historial de infracciones
        JLabel lblHistorial = new JLabel("Historial de Infracciones:");
        JTextPane historialPane = new JTextPane();
        historialPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historialPane);
        scrollPane.setBounds(20, 175, 500, 150);
        Vector<Infraccion> infracciones = jugador.getHistorialInfracciones();
        if (infracciones != null && !infracciones.isEmpty()) {
            String infraccionesString = "";
            for (Infraccion inf : infracciones) {
                infraccionesString += inf.toString() + "\n";
            }
            historialPane.setText(infraccionesString);
        }
        lblHistorial.setBounds(20, 150, 300, 20);
        contentPane.add(lblHistorial);
        contentPane.add(scrollPane);

        // Botones para futuras funcionalidades de equipos
        JLabel lblEquipos = new JLabel("Gestión de Equipos:");
        lblEquipos.setBounds(20, 340, 300, 20);
        contentPane.add(lblEquipos);

        JButton btnCrearEquipo = new JButton("Crear Equipo");
        btnCrearEquipo.setBounds(20, 410, 150, 30);
        btnCrearEquipo.setEnabled(false);
        contentPane.add(btnCrearEquipo);

        JButton btnSolicitarUnirse = new JButton("Unirse a Equipo");
        btnSolicitarUnirse.setBounds(185, 410, 150, 30);
        btnSolicitarUnirse.addActionListener(e -> {
            GestorSolicitud.solicitar(this, jugador.getRiotID(), textAreaIdEquipo.getText());
        });
        contentPane.add(btnSolicitarUnirse);

        textAreaIdEquipo = new JTextArea();
        textAreaIdEquipo.setBounds(185, 370, 150, 30);

        contentPane.add(textAreaIdEquipo);

        JButton btnAbandonarEquipo = new JButton("Abandonar Equipo");
        btnAbandonarEquipo.setBounds(350, 410, 170, 30);
        btnAbandonarEquipo.setEnabled(false);
        contentPane.add(btnAbandonarEquipo);
    }
}
