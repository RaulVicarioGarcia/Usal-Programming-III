package controller;

import model.Biblioteca;
import model.Exportar;
import view.Vista;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Controlador {

    private final Biblioteca biblioteca;
    private final Vista vista;

    public Controlador(Biblioteca biblioteca, Vista vista) {
        this.biblioteca = biblioteca;
        this.vista = vista;
    }

    public boolean anadirLibro(String titulo, String autor, int ano) {
        return biblioteca.nuevoLibro(autor, titulo, ano) != null;
    }

    public List<String> obtenerListadoLibros() {
        return biblioteca.obtenerListadoLibros();
    }

    public boolean eliminarLibro(int indice) {
        return biblioteca.eliminarLibro(indice);
    }

    public boolean modificarLibro(int indice, String titulo, String autor, int ano) {
        return biblioteca.modificarLibroPorIndice(indice, titulo, autor, ano);
    }

    public void exportarJSON(Path ruta) throws IOException {
        Exportar.exportarJSON(ruta, biblioteca.getLibros());
    }

    public void exportarCSV(Path ruta, String delimitador) {
        Exportar.exportarCSV(ruta, biblioteca.getLibros(), delimitador);
    }

    public void exportarXML(Path ruta) throws IOException {
        Exportar.exportarXML(ruta, biblioteca.getLibros());
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            vista.mostrarMenu();
            int opcion = vista.elegirOpcion();
            switch (opcion) {
                case 1 -> vista.mostrarLibros();
                case 2 -> vista.anadirLibro();
                case 3 -> vista.eliminarLibro();
                case 4 -> vista.modificarLibro();
                case 5 -> vista.exportarLibros();
                case 6 -> {
                    continuar = false;
                    System.out.println("Saliendo de la biblioteca.");
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
