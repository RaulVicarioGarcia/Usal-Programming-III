package view;

import com.coti.tools.Esdia;
import controller.Controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Vista {

    private Controlador controlador;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenu() {
        System.out.println("\n=== Biblioteca ===");
        System.out.println("1. Mostrar el listado de libros.");
        System.out.println("2. Añadir un libro.");
        System.out.println("3. Eliminar un libro.");
        System.out.println("4. Modificar un libro.");
        System.out.println("5. Exportar libros.");
        System.out.println("6. Salir.");
    }

    public int elegirOpcion() {
        return Esdia.readInt("Elige una opción: ", 1, 6);
    }

    public void mostrarLibros() {
        List<String> listado = controlador.obtenerListadoLibros();
        if (listado.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            System.out.println("\nListado de libros:");
            listado.forEach(System.out::println);
        }
    }

    public void anadirLibro() {
        String autor = Esdia.readString("Introduce el autor: ");
        String titulo = Esdia.readString("Introduce el título: ");
        int ano = Esdia.readInt("Introduce el año de publicación: ");
        boolean exito = controlador.anadirLibro(titulo, autor, ano);
        mostrarResultado(exito, "Libro añadido correctamente.", "Error al añadir el libro.");
    }

    public void eliminarLibro() {
        int indice = Esdia.readInt("Introduce el número del libro: ") - 1;
        boolean exito = controlador.eliminarLibro(indice);
        mostrarResultado(exito, "Libro eliminado correctamente.", "Error al eliminar el libro.");
    }

    public void modificarLibro() {
        int indice = Esdia.readInt("Introduce el número del libro a modificar: ") - 1;
        String autor = Esdia.readString("Introduce el nuevo autor: ");
        String titulo = Esdia.readString("Introduce el nuevo título: ");
        int ano = Esdia.readInt("Introduce el nuevo año de publicación: ");
        boolean exito = controlador.modificarLibro(indice, titulo, autor, ano);
        mostrarResultado(exito, "Libro modificado correctamente.", "Error al modificar el libro.");
    }

    public void exportarLibros() {
        String ruta = Esdia.readString("Introduce la ruta del archivo: ");
        String tipo = Esdia.readString("Introduce el tipo de archivo (CSV, JSON, XML): ").toUpperCase();
        Path path = Path.of(ruta);
        try {
            if (!Files.exists(path.getParent())) {
                System.out.println("La carpeta no existe: " + path.getParent());
                return;
            }
            switch (tipo) {
                case "CSV" -> {
                    String delimitador = Esdia.readString("Introduce el delimitador para el CSV: ");
                    controlador.exportarCSV(path, delimitador);
                }
                case "JSON" -> controlador.exportarJSON(path);
                case "XML" -> controlador.exportarXML(path);
                default -> System.out.println("Tipo de archivo no válido.");
        
        }} catch (IOException e) {
        System.out.println("Error de entrada/salida: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
    } }

    private void mostrarResultado(boolean exito, String mensajeExito, String mensajeError) {
        System.out.println(exito ? mensajeExito : mensajeError);
    }

    
}
