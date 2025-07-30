import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num1, num2, num3;

        System.out.println("Agrega un nuevo numero: ");
        num1 = sc.nextInt();

        System.out.println("Agrega un nuevo numero: ");
        num2 = sc.nextInt();

        System.out.println("Agrega un nuevo numero: ");
        num3 = sc.nextInt();

        if (num1 > num2 && num1 > num3) {

            System.out.println("El mayor de todos es: "+num1);

        } else if (num2 > num1 && num2 > num3) {

            System.out.println("El mayor de todos es: "+num2);

        } else if (num3 > num1 && num3 > num2) {

            System.out.println("El mayor de todos es: "+num3);

        } else {

            System.out.println("Son iguales.");

        }

        sc.close();

    }
}
