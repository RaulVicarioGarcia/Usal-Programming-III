import com.coti.tools.Esdia;

public class Wrapped {

    public Wrapped() {
    }

    public int leerEntero(String mensaje) {
        while (true) {
            try {
                int num = Esdia.readInt(mensaje);
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
            }
        }
    }

    public float leerFlotante(String mensaje) {
        while (true) {
            try {
                float num = Esdia.readFloat(mensaje);
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número flotante válido.");
            }
        }
    }

    public double leerDoble(String mensaje) {
        while (true) {
            try {
                double num = Esdia.readDouble(mensaje);
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número doble válido.");
            }
        }
    }
}