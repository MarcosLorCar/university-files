package presentacion;

import dominio.GestorAutenticacion;
import dominio.Jugador;
import dominio.Usuario;

import javax.swing.*;
import java.awt.*;

public class IU_Inicio extends JFrame {
    private JTextField textFieldRiotID;
    private JTextField textFieldNombre;
    private JPanel contentPane;
    private Usuario usuario;

    public IU_Inicio(Usuario usuario) throws Exception {
        this.usuario = usuario;

        setName("Registrar Jugador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 280);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Registrarse como Jugador");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setBounds(20, 15, 300, 25);
        contentPane.add(lblTitulo);

        // Riot ID
        JLabel lblRiotID = new JLabel("Riot ID:");
        lblRiotID.setBounds(20, 60, 80, 16);
        contentPane.add(lblRiotID);

        textFieldRiotID = new JTextField();
        textFieldRiotID.setBounds(120, 54, 250, 28);
        contentPane.add(textFieldRiotID);
        textFieldRiotID.setColumns(10);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 110, 80, 16);
        contentPane.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(120, 104, 250, 28);
        contentPane.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        // Botón Registrar
        JButton btnRegistrar = new JButton("Registrarse");
        btnRegistrar.addActionListener(e -> registrarComoJugador(
                textFieldRiotID.getText(),
                textFieldNombre.getText()));
        btnRegistrar.setBounds(120, 160, 110, 30);
        contentPane.add(btnRegistrar);

        // Botón Limpiar
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(event -> limpiarCampos());
        btnLimpiar.setBounds(260, 160, 110, 30);
        contentPane.add(btnLimpiar);
    }

    public void registrarComoJugador(String riotID, String nombre) {
        try {
            if (riotID.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Jugador jugador = GestorAutenticacion.registrarComoJugador(usuario, riotID, nombre);

            JOptionPane.showMessageDialog(this, "Registro exitoso como jugador",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

            new IU_Jugador(jugador).setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        textFieldRiotID.setText("");
        textFieldNombre.setText("");
    }
}
