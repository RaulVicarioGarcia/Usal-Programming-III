package view;

import controller.Controller;
import model.task.Task;

import java.util.List;
import com.coti.tools.Esdia;

public class ConsoleView extends BaseView {

    // Constructor para inicializar con el controlador
    public ConsoleView(Controller controller) {
        super(controller);
    }

    @Override
    public void init() {
        System.out.println("Bienvenido a la aplicacion de gestion de tareas.");
        showMainMenu(); // Muestra el menu principal
    }

    @Override
    public void showMessage(String message) {
        System.out.println("[SYSTEM]: " + message); // Muestra mensajes informativos
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        System.err.println("[SYSTEM]: " + errorMessage); // Muestra mensajes de error
    }

    @Override
    public void end() {
        controller.end(); // Finaliza la aplicacion correctamente
        System.out.println("Saliendo de la aplicacion... ¡Hasta pronto!");
    }

    // Muestra el menu principal y devuelve la opcion seleccionada como String
    @Override
    public String showMenuAndGetOption() {
        System.out.println("\nCentro de operaciones:");
        System.out.println("1. Gestionar tareas");
        System.out.println("2. Exportar/Importar tareas");
        System.out.println("3. Ver tiempo transcurrido");
        System.out.println("4. Salir");

        int choice = Esdia.readInt("Seleccione una opcion:", 1, 4);
        return String.valueOf(choice); // Convierte la opcion a String
    }

    // Metodo principal que controla el flujo del menu principal
    private void showMainMenu() {
        boolean exit = false;
        while (!exit) {
            String choice = showMenuAndGetOption(); // Obtiene la opcion seleccionada

            switch (choice) {
                case "1" -> manageTasksMenu(); // Gestionar tareas
                case "2" -> manageImportExportMenu(); // Exportar/Importar
                case "3" -> controller.showElapsedTime(); // Mostrar tiempo transcurrido
                case "4" -> {
                    exit = true;
                    end(); // Finaliza la aplicacion
                }
                default -> showErrorMessage("Opcion no valida. Intente de nuevo.");
            }
        }
    }

    // Menu para gestionar tareas
    private void manageTasksMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nGestion de tareas:");
            System.out.println("1. Anadir tarea");
            System.out.println("2. Listar todas las tareas");
            System.out.println("3. Listar tareas pendientes (ordenadas por prioridad)");
            System.out.println("4. Modificar tarea");
            System.out.println("5. Cambiar estado de completado de una tarea"); // Nueva opcion
            System.out.println("6. Eliminar tarea por ID");
            System.out.println("7. Volver al centro de operaciones");

            int choice = Esdia.readInt("Seleccione una opcion:", 1, 7);

            switch (choice) {
                case 1 -> controller.addTask(); // Añadir nueva tarea
                case 2 -> controller.listTasks(); // Listar tareas
                case 3 -> controller.listPendingTasksByPriority(); // Listar pendientes por prioridad
                case 4 -> controller.modifyTask(); // Modificar tarea
                case 5 -> controller.toggleTaskCompletion(); // Cambiar estado de completado
                case 6 -> controller.deleteTask(); // Eliminar tarea
                case 7 -> exit = true; // Salir al menu principal
                default -> showErrorMessage("Opcion no valida. Intente de nuevo.");
            }
        }
    }

    // Menu para exportar e importar tareas
    private void manageImportExportMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nExportar/Importar tareas:");
            System.out.println("1. Exportar tareas");
            System.out.println("2. Importar tareas");
            System.out.println("3. Volver al centro de operaciones");

            int choice = Esdia.readInt("Seleccione una opcion:", 1, 3);

            switch (choice) {
                case 1 -> controller.exportTasks(); // Exportar datos
                case 2 -> controller.importTasks(); // Importar datos
                case 3 -> exit = true; // Salir al menu principal
                default -> showErrorMessage("Opcion no valida.");
            }
        }
    }

    // Mostrar una lista de tareas
    @Override
    public void displayTasks(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            showMessage("No hay tareas registradas.");
        } else {
            System.out.println("\nLista de tareas:");
            for (Task task : tasks) {
                System.out.println(task); // Muestra cada tarea
            }
        }
    }
}
