package model.interfaces.exporter.json;

import com.coti.tools.Rutas;

import model.interfaces.exporter.*;
import model.task.Task;
import com.google.gson.*;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class JsonExporter implements IExporter {

    @Override
    public void exportar(List<Task> tareas, String rutaArchivo) throws ExporterException {

        // Uso de Rutas para asegurarse de que la ruta sea válida.

        Path ruta = Rutas.pathToFileInDocuments(rutaArchivo);

        Gson gson = new Gson();
        try (Writer escritor = new FileWriter(ruta.toFile())) {
            gson.toJson(tareas, escritor);
        } catch (IOException e) {
            throw new ExporterException("Error al exportar las tareas a JSON", e);
        }
    }

    @Override
    public List<Task> importar(String rutaArchivo) throws ExporterException {

        // Uso de Rutas para localizar el archivo.

        Path ruta = Rutas.pathToFileInDocuments(rutaArchivo);

        Gson gson = new Gson();
        try (Reader lector = new FileReader(ruta.toFile())) {

            Task[] arrayTareas = gson.fromJson(lector, Task[].class);
            return Arrays.asList(arrayTareas);

        } catch (IOException | JsonSyntaxException e) {

            throw new ExporterException("Error al importar las tareas desde JSON", e);

        }
  
    }

}
