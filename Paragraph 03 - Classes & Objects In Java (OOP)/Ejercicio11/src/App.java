import java.util.Scanner;
import model.Person;
import model.Dni;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Person[] personas = new Person[3];

        for (int i = 0; i < personas.length; i++) {
            System.out.println("Introduce el nombre de la persona " + (i + 1) + ": ");
            String nombre = sc.nextLine();

            System.out.println("Introduce la altura en cm: ");
            float altura = sc.nextFloat();

            System.out.println("Introduce el peso en kg: ");
            float peso = sc.nextFloat();

            System.out.println("Introduce el número del DNI (sin letra): ");
            int numeroDni = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer.

            Dni dni = new Dni(numeroDni);
            personas[i] = new Person(dni, nombre, altura, peso);
        }

        // Encontrar la persona con mayor altura y peso.
        Person mayorAltura = personas[0];
        Person mayorPeso = personas[0];

        for (Person persona : personas) {
            if (persona.getAltura() > mayorAltura.getAltura()) {
                mayorAltura = persona;
            }
            if (persona.getPeso() > mayorPeso.getPeso()) {
                mayorPeso = persona;
            }
        }

        // Mostrar resultados.
        System.out.println("La persona con mayor altura es: " + mayorAltura.getNombre() +
                           " con " + mayorAltura.getAltura() + " cm.");

        System.out.println("La persona con mayor peso es: " + mayorPeso.getNombre() +
                           " con " + mayorPeso.getPeso() + " kg.");

        // Mostrar datos de las personas.
        for (Person persona : personas) {
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Altura: " + persona.getAltura() + " cm");
            System.out.println("Peso: " + persona.getPeso() + " kg");
            System.out.println("DNI: " + persona.getDni().getNumero() + persona.getDni().getLetra());
            System.out.println("IMC: " + String.format("%.2f", persona.calcularIMC()));
            System.out.println("----------------------------");
        }

        sc.close();
    }
}
