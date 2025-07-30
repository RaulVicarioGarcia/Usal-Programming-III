import java.util.Map;
import java.util.Scanner;

public class App {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    buscarLibro();
                    break;
                case 3:
                    actualizarLibro();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 5:
                    mostrarTodosLosLibros();
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 6);
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== GESTIÓN DE BIBLIOTECA ===");
        System.out.println("1. Agregar libro");
        System.out.println("2. Buscar libro");
        System.out.println("3. Actualizar libro");
        System.out.println("4. Eliminar libro");
        System.out.println("5. Mostrar todos los libros");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarLibro() {
        System.out.println("\n=== AGREGAR LIBRO ===");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Año de publicación: ");
        int anio = scanner.nextInt();
        System.out.print("Número de páginas: ");
        int paginas = scanner.nextInt();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        Libro nuevoLibro = new Libro(titulo, anio, paginas, precio);
        if (biblioteca.agregarLibro(isbn, nuevoLibro)) {
            System.out.println("Libro agregado exitosamente");
        } else {
            System.out.println("Error: Ya existe un libro con ese ISBN");
        }
    }

    private static void buscarLibro() {
        System.out.println("\n=== BUSCAR LIBRO ===");
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();

        Libro libro = biblioteca.buscarLibro(isbn);
        if (libro != null) {
            System.out.println("\nLibro encontrado:");
            System.out.println("ISBN: " + isbn);
            System.out.println(libro);
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private static void actualizarLibro() {
        System.out.println("\n=== ACTUALIZAR LIBRO ===");
        System.out.print("Ingrese el ISBN del libro a actualizar: ");
        String isbn = scanner.nextLine();

        System.out.print("Nuevo título (Enter para mantener actual): ");
        String titulo = scanner.nextLine();
        System.out.print("Nuevo año de publicación (0 para mantener actual): ");
        int anio = scanner.nextInt();
        System.out.print("Nuevo número de páginas (0 para mantener actual): ");
        int paginas = scanner.nextInt();
        System.out.print("Nuevo precio (0 para mantener actual): ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        if (biblioteca.actualizarLibro(isbn, titulo, anio, paginas, precio)) {
            System.out.println("Libro actualizado exitosamente");
        } else {
            System.out.println("Error: Libro no encontrado");
        }
    }

    private static void eliminarLibro() {
        System.out.println("\n=== ELIMINAR LIBRO ===");
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();

        if (biblioteca.eliminarLibro(isbn)) {
            System.out.println("Libro eliminado exitosamente");
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private static void mostrarTodosLosLibros() {
        System.out.println("\n=== LISTADO DE LIBROS ===");
        if (biblioteca.estaVacia()) {
            System.out.println("No hay libros registrados");
            return;
        }

        for (Map.Entry<String, Libro> entrada : biblioteca.obtenerTodosLosLibros().entrySet()) {
            System.out.println("ISBN: " + entrada.getKey());
            System.out.println(entrada.getValue());
            System.out.println("-----------------");
        }
    }
}