public class UsuarioFactory {

    public Usuario crearUsuario (String nombre, int edad) throws MyUserException {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new MyUserException("El nombre no puede ser nulo o vacío.");
        }

        if (edad <= 0) {
            throw new MyUserException("La edad debe ser mayor que cero.");
        }

        return new Usuario(nombre, edad);

    }
    
}
