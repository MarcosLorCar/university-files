package presentacion;

import dominio.GestorInfracciones;

import javax.swing.*;
import java.util.UUID;

public class IU_Administrador extends JFrame {

    private JTextField textFieldRiotID;
    private JTextField textFieldTipo;
    private JTextArea textAreaDescripcion;
    private JTextArea textAreaResultado;
    private JPanel contentPane;
    private GestorInfracciones gestorInfracciones;

    public IU_Administrador() throws Exception {
        gestorInfracciones = new GestorInfracciones();

        setTitle("Panel de Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 420);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Registrar infracción a un jugador");
        lblTitulo.setBounds(20, 15, 350, 20);
        contentPane.add(lblTitulo);

        JLabel lblRiotID = new JLabel("Riot ID:");
        lblRiotID.setBounds(20, 55, 80, 20);
        contentPane.add(lblRiotID);

        textFieldRiotID = new JTextField();
        textFieldRiotID.setBounds(110, 50, 180, 28);
        contentPane.add(textFieldRiotID);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 95, 80, 20);
        contentPane.add(lblTipo);

        textFieldTipo = new JTextField();
        textFieldTipo.setBounds(110, 90, 180, 28);
        contentPane.add(textFieldTipo);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(20, 135, 100, 20);
        contentPane.add(lblDescripcion);

        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setBounds(110, 135, 300, 80);
        contentPane.add(textAreaDescripcion);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(320, 50, 120, 30);
        contentPane.add(btnRegistrar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(320, 90, 120, 30);
        contentPane.add(btnLimpiar);

        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(20, 235, 100, 20);
        contentPane.add(lblResultado);

        textAreaResultado = new JTextArea();
        textAreaResultado.setEditable(false);
        textAreaResultado.setBounds(20, 260, 420, 90);
        contentPane.add(textAreaResultado);

        // ACCIONES
        btnRegistrar.addActionListener(e -> registrarInfraccion());

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void registrarInfraccion() {
        try {
            String riotID = textFieldRiotID.getText();
            String tipo = textFieldTipo.getText();
            String descripcion = textAreaDescripcion.getText();

            String solicitudID = UUID.randomUUID().toString();
            String resultado = gestorInfracciones.apuntarInfraccion(solicitudID, riotID, tipo, descripcion);
            mostrarMensaje(resultado);
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        textFieldRiotID.setText("");
        textFieldTipo.setText("");
        textAreaDescripcion.setText("");
        textAreaResultado.setText("");
    }

    private void mostrarMensaje(String msg) {
        textAreaResultado.setText(msg);
    }
}
