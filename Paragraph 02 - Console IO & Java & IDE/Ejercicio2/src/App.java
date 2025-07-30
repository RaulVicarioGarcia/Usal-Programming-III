import java.io.Console;

public class App {
    public static void main(String[] args) {

        Console cons = System.console();
        int fechaActual = 2024;
        String fechaIntroducir;
        int resultado;


        if (cons == null) {

            System.out.println("No hay una consola disponible");

            return;

        }

        cons.printf("Introduce tu fecha de nacimiento: ");
        fechaIntroducir = cons.readLine();

        int fechaIntroducirEntera = Integer.parseInt(fechaIntroducir);

        resultado = fechaActual - fechaIntroducirEntera;

        cons.printf("Tu edad es: %d", resultado);

    }
}
