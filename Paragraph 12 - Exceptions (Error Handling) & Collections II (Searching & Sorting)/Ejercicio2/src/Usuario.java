public class Usuario {

    private String nombreUsuario;
    private int edadUsuario;

    public Usuario (String nombreUsuario, int edadUsuario) {

        this.nombreUsuario=nombreUsuario;
        this.edadUsuario=edadUsuario;

    }

    public int getEdadUsuario() {
        return edadUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
}
