import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        int sumando1, sumando2, resultado;

        Scanner sc = new Scanner(System.in);

        System.out.println("Dame un sumando: ");
        sumando1 = sc.nextInt();

        System.out.println("Dame otro sumando: ");
        sumando2 = sc.nextInt();

        resultado = sumando1 + sumando2;

        System.out.println("El resultado es: "+resultado);

        sc.close();

    }
}
