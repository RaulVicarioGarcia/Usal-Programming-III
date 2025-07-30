import modelo.Autor;
import modelo.Libro;
import modelo.Almacen;

public class App {
    public static void main(String[] args) {
        Autor autor1 = new Autor("Gabriel", "García Márquez", true);
        Autor autor2 = new Autor("Isabel", "Allende", false);

        Libro libro1 = new Libro(autor1, "Cien años de soledad", 1967, 471, 12.99);
        Libro libro2 = new Libro(autor2, "La casa de los espíritus", 1982, 490, 14.50);

        Almacen almacen = new Almacen();

        // Crear
        almacen.nuevoLibro(libro1);
        almacen.nuevoLibro(libro2);

        // Leer
        System.out.println("Lista de libros:");
        almacen.mostrarLibros();

        // Actualizar
        Libro libroActualizado = new Libro(autor1, "Cien años de soledad (Edición especial)", 1967, 471, 15.99);
        almacen.actualizarLibro("Cien años de soledad", libroActualizado);

        // Leer después de actualizar
        System.out.println("\nLista de libros actualizada:");
        almacen.mostrarLibros();

        // Eliminar
        almacen.eliminarLibro("La casa de los espíritus");

        // Leer después de eliminar
        System.out.println("\nLista de libros tras eliminación:");
        almacen.mostrarLibros();
    }
}
