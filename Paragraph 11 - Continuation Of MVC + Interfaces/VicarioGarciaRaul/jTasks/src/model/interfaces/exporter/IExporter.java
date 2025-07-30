package model.interfaces.exporter;

import java.util.List;
import model.task.Task;

// Interfaz para exportar/importar tareas.

public interface IExporter {

    void exportar(List<Task> tareas, String rutaArchivo) throws ExporterException, Exception;
    List<Task> importar(String rutaArchivo) throws ExporterException;

}
