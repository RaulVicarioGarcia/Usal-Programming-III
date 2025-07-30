import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {

        Concesionario concesionario = new Concesionario();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Concesionario ===");
            System.out.println("1. Agregar coche");
            System.out.println("2. Listar coches");
            System.out.println("3. Actualizar coche");
            System.out.println("4. Eliminar coche");
            System.out.println("5. Salir");

            int opcion = Esdia.readInt("Introduzca una opción (1-5): ", 1, 5);

            switch (opcion) {
                case 1:
                    agregarCoche(concesionario); // Llama al método auxiliar
                    break;
                case 2:
                    concesionario.listarCoches(); // Llama al método de Concesionario
                    break;
                case 3:
                    actualizarCoche(concesionario); // Llama al método auxiliar
                    break;
                case 4:
                    eliminarCoche(concesionario); // Llama al método auxiliar
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    // Métodos auxiliares para interactuar con Concesionario
    private static void agregarCoche(Concesionario concesionario) {
        String marca = Esdia.readString_ne("Ingrese la marca del coche: ");
        String matricula = Esdia.readString_ne("Ingrese la matrícula del coche: ");
        int año = Esdia.readInt("Ingrese el año de fabricación (1950-2024): ", 1950, 2024);

        Coche coche = new Coche(marca, matricula, año);
        concesionario.nuevoCoche(coche); // Llama al método de Concesionario
    }

    private static void actualizarCoche(Concesionario concesionario) {
        String matricula = Esdia.readString_ne("Ingrese la matrícula del coche a actualizar: ");
        String nuevaMarca = Esdia.readString_ne("Ingrese la nueva marca del coche: ");
        int nuevoAño = Esdia.readInt("Ingrese el nuevo año de fabricación (1950-2024): ", 1950, 2024);

        concesionario.actualizarCoche(matricula, nuevaMarca, nuevoAño); // Llama al método de Concesionario
    }

    private static void eliminarCoche(Concesionario concesionario) {
        String matricula = Esdia.readString_ne("Ingrese la matrícula del coche a eliminar: ");
        concesionario.eliminarCoche(matricula); // Llama al método de Concesionario
    }
}
