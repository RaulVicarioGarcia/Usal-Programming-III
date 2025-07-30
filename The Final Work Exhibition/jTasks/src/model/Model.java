package model;

import model.interfaces.repository.IRepository;
import model.task.Task;
import model.interfaces.exporter.IExporter;
import model.interfaces.repository.RepositoryException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Model {

    private final IRepository repositorio;
    private IExporter exportador;

    // Constructor: inicializa el repositorio mediante inyección de dependencias.

    public Model(IRepository repositorio) {

        this.repositorio = repositorio;

    }

    // Configura dinámicamente un exportador para manejar diferentes formatos.

    public void setExporter(IExporter exportador) {

        this.exportador = exportador;

    }

    public List<Task> loadData() throws RepositoryException {

        return repositorio.getAllTasks();

    }

    public void saveData(List<Task> tareas) throws Exception {

        validarDuplicados(tareas);
        validarCamposObligatorios(tareas);
        for (Task tarea : tareas) {
            repositorio.addTask(tarea);

        }
    }

    public void exportData(String rutaArchivo) throws Exception {

        if (exportador == null) {

            throw new IllegalStateException("No se ha configurado un exportador.");
        }

        List<Task> tareas = repositorio.getAllTasks();
        validarDatosAntesDeExportar(tareas);
        exportador.exportar(tareas, rutaArchivo);

    }

    public void importData(String rutaArchivo) throws Exception {

        if (exportador == null) {

            throw new IllegalStateException("No se ha configurado un exportador.");

        }

        List<Task> tareasImportadas = exportador.importar(rutaArchivo);
        validarDuplicados(tareasImportadas);
        validarCamposObligatorios(tareasImportadas);

        for (Task tarea : tareasImportadas) {

            repositorio.addTask(tarea);

        }
    }

    /*** MÉTODOS DE VALIDACIÓN ***/

    private void validarDuplicados(List<Task> tareas) throws Exception {

        Set<Integer> ids = new HashSet<>();

        for (Task tarea : tareas) {

            if (!ids.add(tarea.getIdentificador())) {

                throw new Exception("Se encontro un identificador duplicado: " + tarea.getIdentificador());

            }

        }

    }

    private void validarCamposObligatorios(List<Task> tareas) throws Exception {

        for (Task tarea : tareas) {

            if (tarea.getTitulo() == null || tarea.getTitulo().isBlank()) {
                throw new Exception("El titulo de una tarea no puede estar vacío.");
            }
            if (tarea.getFecha() == null) {
                throw new Exception("La fecha de una tarea no puede ser nula.");
            }
            if (tarea.getPrioridad() < 0 || tarea.getPrioridad() > 5) {
                throw new Exception("La prioridad de una tarea debe estar entre 0 y 5.");
            }

        }

    }

    private void validarDatosAntesDeExportar(List<Task> tareas) throws Exception {

        if (tareas.isEmpty()) {

            throw new Exception("No hay tareas disponibles para exportar.");

        }

        validarCamposObligatorios(tareas);

    }
    
    public void removeTask(Task tarea) throws RepositoryException {
        repositorio.removeTask(tarea);
    }
    
}
