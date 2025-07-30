package modelo;

import java.util.ArrayList;

public class Almacen {

    private ArrayList<Libro> libros;
    private int ritmoDeLectura = 1;

    public Almacen() {
        this.libros = new ArrayList<>();
    }

    // CREATE: Añadir un nuevo libro
    public void nuevoLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro añadido: " + libro.getTitulo());
    }

    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public void actualizarLibro(String titulo, Libro libroActualizado) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                libros.set(i, libroActualizado);
                System.out.println("Libro actualizado: " + titulo);
                return;
            }
        }
        System.out.println("Libro no encontrado: " + titulo);
    }

    public void eliminarLibro(String titulo) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                libros.remove(i);
                System.out.println("Libro eliminado: " + titulo);
                return;
            }
        }
        System.out.println("Libro no encontrado: " + titulo);
    }

    public void marcarRitmoDeLectura(int ritmo) {
        if (ritmo > 0) {
            this.ritmoDeLectura = ritmo;
            System.out.println("Ritmo de lectura actualizado: " + ritmo + " páginas/minuto");
        } else {
            System.out.println("Ritmo no válido. Debe ser mayor que 0.");
        }
    }
}
