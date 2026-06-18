package presentacion;

import dominio.GestorAutenticacion;
import dominio.Jugador;
import dominio.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IU_Autenticacion extends JFrame {

    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                IU_Autenticacion frame = new IU_Autenticacion();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public IU_Autenticacion() throws Exception {

        setTitle("Autenticación - Sistema Valorant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 200);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Bienvenido al Sistema");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(120, 15, 250, 30);
        contentPane.add(lblTitulo);

        // Panel de inicio de sesión
        JLabel lblLogin = new JLabel("Iniciar Sesión");
        lblLogin.setFont(new Font("Arial", Font.BOLD, 14));
        lblLogin.setBounds(20, 60, 150, 20);
        contentPane.add(lblLogin);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(20, 90, 80, 20);
        contentPane.add(lblUsuario);

        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(110, 90, 150, 25);
        contentPane.add(textFieldUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(20, 125, 80, 20);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 125, 150, 25);
        contentPane.add(passwordField);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBounds(280, 90, 140, 25);
        btnIniciarSesion.addActionListener(this::iniciarSesion);
        contentPane.add(btnIniciarSesion);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(280, 90 + (125 - 90), 140, 25);
        btnRegistrarse.addActionListener(this::registrarse);
        contentPane.add(btnRegistrarse);
    }

    private void iniciarSesion(ActionEvent event) {
        try {
            String login = textFieldUsuario.getText();
            String contraseña = new String(passwordField.getPassword());

            if (login.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario usuario = GestorAutenticacion.iniciarSesion(login, contraseña);

            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();

                switch (usuario.getTipo()) {
                    case "usuario":
                        new IU_Inicio(usuario).setVisible(true);
                        break;
                    case "jugador":
                        new IU_Jugador((Jugador) usuario).setVisible(true);
                        break;
                    case "admin":
                        new IU_Administrador().setVisible(true);
                        break;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarse(ActionEvent event) {
        try {
            String usuario = textFieldUsuario.getText();
            String contraseña = new String(passwordField.getPassword());

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario cuenta = GestorAutenticacion.registrarse(usuario, contraseña);

            if (cuenta != null) {
                JOptionPane.showMessageDialog(this, "Registro exitoso. Ya puedes iniciar sesión",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el usuario",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        textFieldUsuario.setText("");
        passwordField.setText("");
    }
}
