package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private ArrayList<Libro> libros;

    // Creas constructor.

    public Biblioteca() {
        this.libros = new ArrayList<>();
    }

    // Crear un nuevo libro y agregarlo a la lista
    public Libro nuevoLibro(String autor, String titulo, int anoPrimeraPublicacion) {
        Libro libro = new Libro(titulo, autor, anoPrimeraPublicacion);
        for (Libro l : libros) { // Verificar duplicados
            if (l.equals(libro)) {
                return null; // No agregar si ya existe
            }
        }
        libros.add(libro);
        return libro;
    }

    // Obtener listado en formato String
    public ArrayList<String> obtenerListadoLibros() {
        ArrayList<String> listado = new ArrayList<>();
        int id = 1; 
        for (Libro libro : libros) {
            listado.add(String.format("%-5d %-30s %-30s %-20s", 
                id, 
                libro.getTitulo(), 
                libro.getAutor(), 
                libro.getAnoPrimeraPublicacion()));
            id++;
        }
        return listado;
    }

    // Obtener la lista completa de libros
    public List<Libro> getLibros() {
        return new ArrayList<>(libros); // Devolver una copia de la lista
    }

    // Establecer una nueva lista de libros
    public void setLibros(List<Libro> libros) {
        this.libros = new ArrayList<>(libros); // Actualizar con una copia
    }

    // Eliminar un libro por índice
    public boolean eliminarLibro(int indice) {
        if (indice >= 0 && indice < libros.size()) {
            libros.remove(indice);
            return true;
        }
        return false;
    }

    // Modificar un libro existente
    public boolean modificarLibroPorIndice(int indice, String nuevoTitulo, String nuevoAutor, int nuevoAno) {
        if (indice >= 0 && indice < libros.size()) {
            Libro libro = libros.get(indice);
            libro.setTitulo(nuevoTitulo);
            libro.setAutor(nuevoAutor);
            libro.setAnoPrimeraPublicacion(nuevoAno);
            return true;
        }
        return false;
    }
}
