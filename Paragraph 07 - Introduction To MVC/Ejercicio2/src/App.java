import controller.Controller;
import model.VentasModel;
import view.View;

public class App {
    public static void main(String[] args) {
        VentasModel model = new VentasModel();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.run();
    }
}