import controller.Controlador;
import model.Biblioteca;
import view.Vista;

public class App {
    public static void main(String[] args) {
        // Crear los objetos del modelo y vista
        Biblioteca biblioteca = new Biblioteca();
        Vista vista = new Vista(null); // Temporalmente pasa null

        // Crear el controlador
        Controlador controlador = new Controlador(biblioteca, vista);

        // Establecer el controlador en la vista
        vista.setControlador(controlador);

        // Iniciar la aplicación
        controlador.iniciar();
    }
}
