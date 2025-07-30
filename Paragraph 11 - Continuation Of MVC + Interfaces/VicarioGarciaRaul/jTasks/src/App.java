import controller.Controller;
import model.Model;
import model.interfaces.exporter.csv.CsvExporter;
import model.interfaces.repository.IRepository;
import model.interfaces.repository.InMemoryRepository;
import model.interfaces.repository.bin.BinaryRepository;
import model.interfaces.repository.notion.NotionRepository;
import view.BaseView;
import view.ConsoleView;
import com.coti.tools.DiaUtil;

public class App {
    public static void main(String[] args) {

        // Inicia el temporizador
        DiaUtil.startTimerMS();

        IRepository repositorio = null;

        try {
            // Verificar los argumentos de la línea de comandos
            if (args.length < 2 || !args[0].equalsIgnoreCase("--repository")) {
                throw new IllegalArgumentException("Uso esperado: --repository [bin|notion|memory] [API_KEY DATABASE_ID]");
            }

            String repositoryType = args[1];

            // Selección del repositorio según el argumento
            switch (repositoryType.toLowerCase()) {
                case "bin":
                    repositorio = new BinaryRepository();
                    break;

                case "notion":
                    if (args.length < 4) {
                        throw new IllegalArgumentException("Uso esperado para Notion: --repository notion API_KEY DATABASE_ID");
                    }
                    String apiKey = args[2];
                    String databaseId = args[3];
                    repositorio = new NotionRepository(apiKey, databaseId);
                    break;

                case "memory":
                    repositorio = new InMemoryRepository(); // Repositorio por defecto en memoria
                    break;

                default:
                    throw new IllegalArgumentException("Tipo de repositorio no soportado: " + repositoryType);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Uso esperado:");
            System.err.println("  java -jar app.jar --repository bin");
            System.err.println("  java -jar app.jar --repository notion API_KEY DATABASE_ID");
            System.err.println("  java -jar app.jar --repository memory");
            return;
        } catch (Exception e) {
            System.err.println("Error al inicializar el repositorio: " + e.getMessage());
            return;
        }

        // Configurar modelo, vista y controlador
        Model model = new Model(repositorio); // Crear instancia del modelo
        Controller controller = new Controller(model, null); // Crear controlador
        BaseView view = new ConsoleView(controller); // Crear instancia de la vista
        model.setExporter(new CsvExporter());

        // Asignar la vista al controlador
        controller.setView(view);

        // Inicia la aplicación
        try {
            controller.start(); // Cargar datos iniciales y mostrar vista principal

            // Ciclo de ejecución principal
            boolean running = true;
            while (running) {
                String option = view.showMenuAndGetOption();
                switch (option) {
                    case "1":
                        controller.addTask();
                        break;
                    case "2":
                        controller.listTasks();
                        break;

                        case "3":
                        controller.listPendingTasksByPriority();
                        break;
                    case "4":
                        controller.toggleTaskCompletion();
                        break;
                    case "5":
                        controller.modifyTask();
                    case "6":
                        controller.deleteTask();
                        break;
                    case "7":
                        controller.exportTasks();
                        break;
                    case "8":
                        controller.importTasks();
                        break;
                    case "9":
                        controller.clearScreen();
                        break;
                    case "10":
                        controller.showElapsedTime();
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        view.showErrorMessage("Opcion incorecta, intenta de nuevo.");
                }
            }

            // Finalizar aplicación
            controller.end();
        } catch (Exception e) {
            view.showErrorMessage("Error fatal: " + e.getMessage());
        } finally {
            // Mostrar tiempo total y finalizar
            DiaUtil.showFinalTime();
        }
    }
}
