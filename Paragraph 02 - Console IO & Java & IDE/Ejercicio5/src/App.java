import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introduce tu nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Introduce tus apellidos: ");
        String apellidos = scanner.nextLine();
        
        int longitudNombre = nombre.length();
        int longitudApellidos = apellidos.length();
        int anchoTabla;

        if (longitudNombre > longitudApellidos) {
            anchoTabla = longitudNombre + 10;
        } else {
            anchoTabla = longitudApellidos + 10;
        }

        for (int i = 0; i < anchoTabla; i++) {
            System.out.print("-");
        }
        System.out.println();
        
        System.out.print("| Nombre: " + nombre);
        for (int i = 0; i < (anchoTabla - nombre.length() - 8); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        
        for (int i = 0; i < anchoTabla; i++) {
            System.out.print("-");
        }
        System.out.println();
        
        System.out.print("| Apellidos: " + apellidos);
        for (int i = 0; i < (anchoTabla - apellidos.length() - 11); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        
        for (int i = 0; i < anchoTabla; i++) {
            System.out.print("-");
        }
        System.out.println();
        
        System.out.println("Longitud del nombre: " + longitudNombre);
        System.out.println("Longitud de los apellidos: " + longitudApellidos);
        
        scanner.close();
    }
}
