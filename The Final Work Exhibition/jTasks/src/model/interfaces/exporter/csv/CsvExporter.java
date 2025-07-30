package model.interfaces.exporter.csv;

import com.coti.tools.OpMat;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import model.task.Task;
import model.interfaces.exporter.ExporterException;
import model.interfaces.exporter.IExporter;

public class CsvExporter implements IExporter {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void exportar(List<Task> tareas, String rutaArchivo) throws Exception {
        // Validar datos antes de exportar
        if (tareas == null || tareas.isEmpty()) {
            throw new ExporterException("No hay tareas disponibles para exportar.");
        }

        // Usa la ruta directamente (el controlador garantiza que es válida)
        Path ruta = Path.of(rutaArchivo);

        String[][] datosTareas = tareas.stream()
                .map(task -> new String[]{
                        String.valueOf(task.getIdentificador()),
                        task.getTitulo(),
                        task.getFecha() != null ? dateFormat.format(task.getFecha()) : "",
                        task.getContenido(),
                        String.valueOf(task.getPrioridad()),
                        String.valueOf(task.getDuracionEstimada()),
                        String.valueOf(task.isCompletada())
                })
                .toArray(String[][]::new);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta.toFile()))) {
            // Escribir cabecera
            escritor.write("ID,Título,Fecha,Contenido,Prioridad,Duración,Completada");
            escritor.newLine();

            // Escribir datos
            for (String[] fila : datosTareas) {
                escritor.write(String.join(",", fila));
                escritor.newLine();
            }
        } catch (IOException e) {
            throw new ExporterException("Error al exportar las tareas a CSV", e);
        }

        // Uso de OpMat para imprimir los datos exportados
        System.out.println("Tareas exportadas:");
        OpMat.printToScreen(datosTareas);
    }

    @Override
    public List<Task> importar(String rutaArchivo) throws ExporterException {
        Path ruta = Path.of(rutaArchivo); // Usa la ruta proporcionada directamente
        List<Task> tareas = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(ruta.toFile()))) {
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = lector.readLine()) != null) {
                // Ignorar cabecera
                if (esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }

                String[] campos = linea.split(",");
                if (campos.length < 7) {
                    throw new ExporterException("El archivo CSV no tiene el formato esperado.");
                }

                Task tarea = new Task(
                        Integer.parseInt(campos[0]), // ID
                        campos[1],                  // Título
                        campos[2].isEmpty() ? null : dateFormat.parse(campos[2]), // Fecha
                        campos[3],                  // Contenido
                        Integer.parseInt(campos[4]), // Prioridad
                        Integer.parseInt(campos[5]), // Duración
                        Boolean.parseBoolean(campos[6]) // Completada
                );
                tareas.add(tarea);
            }
        } catch (IOException | NumberFormatException | ParseException e) {
            throw new ExporterException("Error al importar las tareas desde el archivo.", e);
        }

        return tareas;
    }

}
