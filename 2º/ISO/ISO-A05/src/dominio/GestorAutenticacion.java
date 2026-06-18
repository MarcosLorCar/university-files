
package dominio;

public class GestorAutenticacion {

    public static Usuario iniciarSesion(String usuario, String contraseña) throws Exception {
        return Usuario.read(usuario, contraseña);
    }

    public static Usuario registrarse(String usuario, String contraseña) throws Exception {
        // Verificar que el usuario no exista ya
        Usuario usuarioExistente = Usuario.read(usuario, contraseña);
        Usuario c = null;
        if (usuarioExistente != null) {
            throw new Exception("El usuario ya existe");
        } else {
            // Crear el nuevo jugador
            int res = Usuario.create(usuario, contraseña);

            if (res == 1) {
                // Devolver el jugador recién creado
                c = Usuario.read(usuario, contraseña);
            }
        }

        return c;
    }

    public static Jugador registrarComoJugador(Usuario usuario, String riotID, String nombre) throws Exception {
        int res = usuario.delete();
        if (res != 1) throw new Exception("Error al eliminar cuenta anterior");
        res = Jugador.create(usuario.getLogin(), usuario.getContraseña(), riotID, nombre);
        if (res != 1) throw new Exception("Error al crear cuenta");

        return Jugador.read(riotID);
    }
}