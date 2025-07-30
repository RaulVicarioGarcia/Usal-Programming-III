import model.Suma;
import view.Vista;
import controller.Controller;

public class App {
    public static void main(String[] args) {
        Suma suma = new Suma();
        Vista vista = new Vista();
        Controller controller = new Controller(suma, vista);

        controller.ejecutar();
    }
}
