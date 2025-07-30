package controller;

import java.text.ParseException;
import com.coti.tools.DiaUtil;
import com.coti.tools.Esdia;
import com.coti.tools.Rutas;
import model.Model;
import model.task.IdentificadorSingleton;
import model.task.Task;
import view.BaseView;
import java.util.Date;
import java.util.HashSet;
import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

public class Controller {
    private final Model model;
    private BaseView view;

    // Constructor para inyectar modelo y vista
    public Controller(Model model, BaseView view) {
        this.model = model;
        this.view = view;
    }

    public void exportImportTasks() {
        boolean running = true;
        while (running) {
            try {
                String option = Esdia.readString_ne("Exportar/Importar tareas:\n" +
                        "1. Exportar tareas\n" +
                        "2. Importar tareas\n" +
                        "3. Volver al centro de operaciones\n" +
                        "Seleccione una opcion:");
                switch (option) {
                    case "1":
                        exportTasks();
                        break;
                    case "2":
                        importTasks();
                        break;
                    case "3":
                        running = false;
                        break;
                    default:
                        view.showErrorMessage("Opcion incorrecta. Intente nuevamente.");
                }
            } catch (Exception e) {
                view.showErrorMessage("Error: " + e.getMessage());
            }
        }
    }

    public void start() {
        DiaUtil.startTimerMS();

        try {
            // Cargar datos iniciales y actualizar identificadores
            List<Task> tasks = model.loadData();
            view.showMessage("Datos cargados correctamente. Total de tareas: " + tasks.size());
        } catch (Exception e) {
            view.showErrorMessage("Error al cargar los datos: " + e.getMessage());
        }

        view.init();
    }

    public void end() {
        try {
            List<Task> tasks = model.loadData();

            // Verifica duplicados antes de guardar
            Set<Integer> uniqueIds = new HashSet<>();
            for (Task task : tasks) {
                if (!uniqueIds.add(task.getIdentificador())) {
                    throw new RuntimeException("Error: Identificador duplicado encontrado: " + task.getIdentificador());
                }
            }

            // Guarda las tareas si no hay duplicados
            model.saveData(tasks);
            view.showMessage("Estado guardado correctamente.");
        } catch (Exception e) {
            view.showErrorMessage("Error al guardar el estado: " + e.getMessage());
        } finally {
            DiaUtil.showFinalTime();
            view.showMessage("Saliendo de la aplicación... ¡Hasta pronto!");
            System.exit(0); // Finaliza la aplicación
        }
    }

    // Método para añadir una tarea
    public void addTask() {
        try {
            // Solicitar al usuario los datos necesarios
            String titulo = Esdia.readString_ne("Introduce el titulo de la tarea:");
            String descripcion = Esdia.readString("Introduce la descripcion de la tarea:");
            int prioridad = Esdia.readInt("Introduce la prioridad (0 a 5):", 0, 5);
            int duracionEstimada = Esdia.readInt("Introduce la duracion estimada en minutos:", 0, 1440); // Máx. 24
                                                                                                         // horas
            boolean completada = Esdia.yesOrNo("¿La tarea se ha completado? (s/n):");

            // Crear la tarea utilizando el constructor actual
            Task task = new Task(
                    IdentificadorSingleton.getInstancia().generarNuevoIdentificador(), // Generar identificador único
                    titulo, // Título
                    new Date(), // Fecha actual
                    descripcion, // Descripción o contenido
                    prioridad, // Prioridad
                    duracionEstimada, // Duración estimada
                    completada // Estado de completada
            );

            // Guardar la tarea en el modelo
            model.saveData(List.of(task));
            view.showMessage("Tarea añadida: " + task.getTitulo());
        } catch (Exception e) {
            view.showErrorMessage("Error al añadir tarea: " + e.getMessage());
        }
    }

    // Método para listar todas las tareas
    public void listTasks() {
        try {
            // Obtener todas las tareas desde el modelo
            List<Task> tasks = model.loadData();
            if (tasks.isEmpty()) {
                view.showMessage("No hay tareas registradas.");
            } else {
                view.displayTasks(tasks);
            }
        } catch (Exception e) {
            view.showErrorMessage("Error al listar tareas: " + e.getMessage());
        }
    }

    // Método para listar tareas sin completar, ordenadas por prioridad de mayor a
    // menor
    public void listPendingTasksByPriority() {
        try {
            // Cargar todas las tareas desde el modelo
            List<Task> tasks = model.loadData();

            // Filtrar tareas no completadas y ordenarlas por prioridad (mayor a menor)
            List<Task> uncompletedTasks = tasks.stream()
                    .filter(task -> !task.isCompletada()) // Filtrar solo tareas no completadas
                    .sorted((task1, task2) -> Integer.compare(task2.getPrioridad(), task1.getPrioridad())) // Ordenar
                                                                                                           // por
                                                                                                           // prioridad
                                                                                                           // descendente
                    .toList();

            if (uncompletedTasks.isEmpty()) {
                view.showMessage("No hay tareas sin completar.");
            } else {
                view.displayTasks(uncompletedTasks);
            }
        } catch (Exception e) {
            view.showErrorMessage("Error al listar tareas sin completar: " + e.getMessage());
        }
    }

    // Método para eliminar una tarea por ID
    public void deleteTask() {
        try {
            int id = Esdia.readInt("Introduce el ID de la tarea a eliminar:");
            try {
                // Cargar todas las tareas
                List<Task> tasks = model.loadData();
                // Buscar la tarea con el ID
                Task tareaAEliminar = null;
                for (Task t : tasks) {
                    if (t.getIdentificador() == id) {
                        tareaAEliminar = t;
                        break;
                    }
                }

                if (tareaAEliminar != null) {
                    // Llamar directamente a la capa de repositorio para archivar
                    model.removeTask(tareaAEliminar);
                    view.showMessage("Tarea eliminada con ID: " + id);
                } else {
                    view.showMessage("No se ha encontado ninguna tarea con el ID especificado.");
                }
            } catch (Exception e) {
                view.showErrorMessage("Error al eliminar tarea: " + e.getMessage());
            }

        } catch (Exception e) {
            view.showErrorMessage("Error al eliminar tarea: " + e.getMessage());
        }
    }

    // Método para modificar una tarea
    public void modifyTask() {
        try {
            // Solicitar el ID de la tarea a modificar
            int id = Esdia.readInt("Introduce el ID de la tarea a modificar:");

            // Cargar todas las tareas desde el modelo
            List<Task> tasks = model.loadData();

            // Buscar el índice de la tarea con el ID especificado
            int index = -1;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getIdentificador() == id) {
                    index = i;
                    break;
                }
            }

            // Verificar si la tarea fue encontrada
            if (index == -1) {
                view.showMessage("No se encontro ninguna tarea con el ID especificado.");
                return;
            }

            // Obtener la tarea a modificar
            Task tarea = tasks.get(index);

            // Mostrar los datos actuales de la tarea y permitir modificaciones
            view.showMessage("Datos actuales de la tarea:\n" + tarea);

            // Modificar título
            String nuevoTitulo = leerCampoOpcional("Introduce el nuevo titulo", tarea.getTitulo());
            tarea.setTitulo(nuevoTitulo);

            // Modificar descripción
            String nuevaDescripcion = leerCampoOpcional("Introduce la nueva descripcion", tarea.getContenido());
            tarea.setDescripcion(nuevaDescripcion);

            // Modificar prioridad
            int nuevaPrioridad = Esdia.readInt(
                    "Introduce la nueva prioridad (actual: " + tarea.getPrioridad() + ", 0 a 5):",
                    0, 5);
            tarea.setPrioridad(nuevaPrioridad);

            // Modificar duración estimada
            int nuevaDuracion = Esdia.readInt(
                    "Introduce la nueva duracion estimada en minutos (actual: " + tarea.getDuracionEstimada() + "):",
                    0, 1440);
            tarea.setDuracionEstimada(nuevaDuracion);

            // Modificar fecha
            String nuevaFechaStr = leerCampoOpcional("Introduce la nueva fecha (Formato: YYYY-MM-DD)",
                    new SimpleDateFormat("yyyy-MM-dd").format(tarea.getFecha()));
            try {
                Date nuevaFecha = new SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaStr);
                tarea.setFecha(nuevaFecha);
            } catch (ParseException e) {
                view.showErrorMessage("Fecha no valida. Se mantiene la fecha actual.");
            }

            // Modificar estado de completitud
            boolean nuevaCompletitud = leerBooleano("¿Esta completada? (S,N)", tarea.isCompletada());
            tarea.setCompletada(nuevaCompletitud);

            // Reemplazar la tarea modificada en la lista
            tasks.set(index, tarea);

            // Guardar los cambios en el modelo
            model.saveData(tasks);

            // Confirmar la modificación
            view.showMessage("Tarea modificada:\n" + tarea);

        } catch (Exception e) {
            view.showErrorMessage("Error al modificar la tarea: " + e.getMessage());
        }
    }

    // Método auxiliar para leer campos opcionales
    private String leerCampoOpcional(String mensaje, String valorActual) {
        String entrada = Esdia.readString(mensaje + " (actual: " + valorActual + "):");
        return entrada.trim().isEmpty() ? valorActual : entrada;
    }

    // Método auxiliar para leer un booleano con un valor predeterminado
    private boolean leerBooleano(String mensaje, boolean valorActual) {
        String entrada = Esdia.readString(mensaje + " (actual: " + (valorActual ? "Si" : "No") + "):");
        if (entrada.equalsIgnoreCase("S")) {
            return true;
        } else if (entrada.equalsIgnoreCase("N")) {
            return false;
        }
        return valorActual; // Si no hay cambio, se mantiene el estado actual
    }

    // Método para cambiar el estado de completitud de una tarea
    public void toggleTaskCompletion() {
        try {
            // Solicitar el ID de la tarea a modificar
            int id = Esdia.readInt("Introduce el ID de la tarea para cambiar su estado de completitud:");

            // Cargar todas las tareas desde el modelo
            List<Task> tasks = model.loadData();

            // Buscar la tarea con el ID especificado
            Task tarea = tasks.stream()
                    .filter(t -> t.getIdentificador() == id)
                    .findFirst()
                    .orElse(null);

            if (tarea != null) {
                // Cambiar el estado de completitud
                boolean nuevoEstado = !tarea.isCompletada();
                tarea.setCompletada(nuevoEstado);

                // Guardar los cambios (se guarda la lista completa)
                model.saveData(tasks);

                // Confirmar al usuario
                view.showMessage("El estado de completitud de la tarea con ID " + id + " ha sido cambiado a: "
                        + (nuevoEstado ? "Completada" : "No completada"));
            } else {
                view.showMessage("No se encontro ninguna tarea con el ID especificado.");
            }
        } catch (Exception e) {
            view.showErrorMessage("Error al cambiar el estado de completitud de la tarea: " + e.getMessage());
        }
    }

    // Método para exportar tareas a un archivo
    public void exportTasks() {
        try {
            // Solicita al usuario el nombre del archivo
            String entradaUsuario = Esdia
                    .readString_ne("Introduce el nombre del archivo para exportar (ejemplo: tareas.csv):");

            Path filePath;
            File archivo = new File(entradaUsuario);

            // Si el usuario proporciona una ruta absoluta, úsala directamente
            if (archivo.isAbsolute()) {
                filePath = archivo.toPath();
            } else {
                // Si no, crea la ruta en la carpeta "Documents"
                filePath = Rutas.pathToFileInDocuments(entradaUsuario);
            }

            // Exporta las tareas (usa la ruta absoluta generada aquí)
            model.exportData(filePath.toString());
            view.showMessage("Datos exportados correctamente al archivo: " + filePath);
        } catch (Exception e) {
            view.showErrorMessage("Error al exportar tareas: " + e.getMessage());
        }
    }

    public void importTasks() {
        try {
            // Solicita al usuario el nombre del archivo
            String entradaUsuario = Esdia
                    .readString_ne("Introduce el nombre del archivo para importar (ejemplo: tareas.csv):");

            Path filePath;
            File archivo = new File(entradaUsuario);

            // Si el usuario proporciona una ruta absoluta, úsala directamente
            if (archivo.isAbsolute()) {
                filePath = archivo.toPath();
            } else {
                // Si no, crea la ruta en la carpeta "Documents"
                filePath = Rutas.pathToFileInDocuments(entradaUsuario);
            }

            // Importa las tareas desde la ruta generada
            model.importData(filePath.toString());
            view.showMessage("Datos importados correctamente desde el archivo: " + filePath);
        } catch (Exception e) {
            view.showErrorMessage("Error al importar tareas: " + e.getMessage());
        }
    }

    // Método para establecer la vista (opcional si ya está en el constructor)
    public void setView(BaseView view) {
        this.view = view;
    }

    // Método para limpiar la pantalla
    public void clearScreen() {
        try {
            DiaUtil.clear();
        } catch (Exception e) {
            view.showErrorMessage("Error al limpiar la pantalla: " + e.getMessage());
        }
    }

    // Método para mostrar el tiempo transcurrido
    public void showElapsedTime() {
        DiaUtil.stopTimerAndPrintElapsedTimeMillis();
    }
}
