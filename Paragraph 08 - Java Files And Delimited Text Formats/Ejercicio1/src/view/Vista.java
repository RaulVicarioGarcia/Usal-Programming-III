package view;

import java.util.Scanner;

public class Vista {

    private Scanner sc;

    public Vista() {
        sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1.- Leer los números");
        System.out.println("2.- Calcular la suma");
        System.out.println("3.- Mostrar el resultado");
        System.out.println("q.- Salir");
    }

    public String leerOpcion() {
        System.out.print("Seleccione una opción: ");
        return sc.nextLine();
    }

    public int leerNumero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = sc.nextInt();
                sc.nextLine(); // Consumir salto de línea
                return numero;
            } catch (Exception e) {
                System.out.println("Por favor, ingrese un número válido.");
                sc.nextLine(); // Limpiar entrada inválida
            }
        }
    }

    public void mostrarResultado(int suma) {
        System.out.println("El resultado de la suma es: " + suma);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
