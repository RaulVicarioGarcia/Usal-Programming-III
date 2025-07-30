import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int fechaActual = 2024;
        int fechaIntroducida;
        int resultado;

        System.out.println("Indica tu año de nacimiento: ");
        fechaIntroducida = sc.nextInt();

        resultado = fechaActual - fechaIntroducida;

        System.out.println("Su edad es: "+resultado);

        sc.close();
    
    }
}
