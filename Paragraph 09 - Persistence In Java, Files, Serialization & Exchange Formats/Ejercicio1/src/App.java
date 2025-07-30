import model.Quiniela;
import view.Vista;
import controller.Controlador;

public class App {
    public static void main(String[] args) {
        try {
            Quiniela quiniela = new Quiniela();
            Vista vista = new Vista();
            Controlador controlador = new Controlador(quiniela, vista);
            
            controlador.iniciar();
        } catch (Exception e) {
            System.err.println("Error fatal en la aplicación: " + e.getMessage());
            System.exit(1);
        }
    }
}