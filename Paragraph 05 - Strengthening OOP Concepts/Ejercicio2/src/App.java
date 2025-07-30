import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] palabras = new String[5];

        // Solicitar 5 strings al usuario
        System.out.println("Introduce 5 palabras:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Palabra " + (i + 1) + ": ");
            palabras[i] = scanner.nextLine();
        }

        // Ordenar el array
        Arrays.sort(palabras);

        // Mostrar las palabras ordenadas
        System.out.println("\nPalabras ordenadas:");
        for (String palabra : palabras) {
            System.out.println(palabra);
        }
    }
}
