import java.util.HashMap;

public class Biblioteca {
    
    private HashMap<String, Libro> libros;
    private HashMap<Libro, String> isbns;

    public Biblioteca() {
        this.libros = new HashMap<>();
        this.isbns = new HashMap<>();
    }

    public boolean agregarLibro(String isbn, Libro libro) {
        if (libros.containsKey(isbn)) {
            return false;
        }
        libros.put(isbn, libro);
        isbns.put(libro, isbn);
        return true;
    }

    public Libro buscarLibro(String isbn) {
        return libros.get(isbn);
    }

    public String obtenerIsbn(Libro libro) {
        return isbns.get(libro);
    }

    public boolean actualizarLibro(String isbn, String titulo, int anio, int paginas, double precio) {
        Libro libro = libros.get(isbn);
        if (libro == null) {
            return false;
        }

        if (!titulo.isEmpty()) {
            libro.setTitulo(titulo);
        }
        if (anio > 0) {
            libro.setAnioDePublicacion(anio);
        }
        if (paginas > 0) {
            libro.setNumeroDePaginas(paginas);
        }
        if (precio > 0) {
            libro.setPrecio(precio);
        }
        
        return true;
    }

    public boolean eliminarLibro(String isbn) {
        Libro libro = libros.get(isbn);
        if (libro != null) {
            isbns.remove(libro);
            libros.remove(isbn);
            return true;
        }
        return false;
    }

    public HashMap<String, Libro> obtenerTodosLosLibros() {
        return libros;
    }

    public boolean estaVacia() {
        return libros.isEmpty();
    }
}