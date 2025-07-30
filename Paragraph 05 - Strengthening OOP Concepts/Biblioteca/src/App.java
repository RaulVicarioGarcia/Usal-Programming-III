import com.coti.tools.Esdia;
import modelo.Almacen;
import modelo.Autor;
import modelo.Libro;

public class App {
    public static void main(String[] args) {
        Almacen almacen = null;
        boolean salir = false;

        while (!salir) {
            System.out.println("|----------------------------------------------|");
            System.out.println("|                MIS LIBROS                   |");
            System.out.println("|----------------------------------------------|");
            System.out.println("1) Nuevo almacén de libros");
            System.out.println("2) Establecer ritmo de lectura (páginas por minuto)");
            System.out.println("3) Añadir un nuevo libro al almacén");
            System.out.println("4) Mostrar información actual de libros");
            System.out.println("5) Salir (se borrará toda la información)");
            System.out.println("|----------------------------------------------|");
            System.out.print("Seleccione una opción (1-5): ");

            int opcion = Esdia.readInt("Seleccione una opción (1-5):", 1, 5);

            switch (opcion) {
                case 1:
                    int capacidad = Esdia.readInt("Introduzca la capacidad del almacén: ", 1, Integer.MAX_VALUE);
                    almacen = new Almacen(capacidad);
                    System.out.println("Nuevo almacén creado con capacidad para " + capacidad + " libros.");
                    break;

                case 2:
                    if (almacen == null) {
                        System.out.println("Primero debe crear un almacén de libros.");
                    } else {
                        int ritmo = Esdia.readInt("Introduzca el ritmo de lectura (páginas por minuto):", 1, Integer.MAX_VALUE);
                        almacen.marcarRitmoDeLectura(ritmo);
                    }
                    break;

                case 3:
                    if (almacen == null) {
                        System.out.println("Primero debe crear un almacén de libros.");
                    } else {
                        String nombreAutor = Esdia.readString_ne("Nombre del autor: ");
                        String apellidosAutor = Esdia.readString_ne("Apellidos del autor: ");
                        boolean premioPlaneta = Esdia.yesOrNo("¿El autor ha ganado el Premio Planeta? (y/n): ");

                        Autor autor = new Autor(nombreAutor, apellidosAutor, premioPlaneta);

                        String titulo = Esdia.readString_ne("Título del libro: ");
                        int anio = Esdia.readInt("Año de publicación: ", 1000, 9999);
                        int paginas = Esdia.readInt("Número de páginas: ", 1, Integer.MAX_VALUE);
                        double precio = Esdia.readDouble("Precio del libro (€): ", 0.01, Double.MAX_VALUE);

                        Libro libro = new Libro(autor, titulo, anio, paginas, precio);
                        almacen.nuevoLibro(libro);
                    }
                    break;

                case 4:
                    if (almacen == null) {
                        System.out.println("Primero debe crear un almacén de libros.");
                    } else {
                        almacen.mostrarLibros();
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa. Se borrará toda la información.");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }
    }
}