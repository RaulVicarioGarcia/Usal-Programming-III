import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Person p1 = new Person(null,0,0);
        Person p2 = new Person(null, 0, 0);
        Person p3 = new Person(null, 0, 0);

        System.out.println("Introduce el nombre de la primera persona: ");
        p1.setNombre(sc.nextLine());

        System.out.println("Introduce la altura: ");
        p1.setAltura(sc.nextFloat());

        System.out.println("Introduce el peso: ");
        p1.setPeso(sc.nextFloat());

        //LimpioBuffer
        sc.nextLine();

        System.out.println("Introduce el nombre de la segunda persona: ");
        p2.setNombre(sc.nextLine());

        System.out.println("Introduce la altura: ");
        p2.setAltura(sc.nextFloat());

        System.out.println("Introduce el peso: ");
        p2.setPeso(sc.nextFloat());

        //LimpioBuffer
        sc.nextLine();

        System.out.println("Introduce el nombre de la tercera persona: ");
        p3.setNombre(sc.nextLine());

        System.out.println("Introduce la altura: ");
        p3.setAltura(sc.nextFloat());

        System.out.println("Introduce el peso: ");
        p3.setPeso(sc.nextFloat());

        //LimpioBuffer
        sc.nextLine();

        if (p1.getAltura() > p2.getAltura() && p1.getAltura() > p3.getAltura()) {

            System.out.println("La persona con mayor altura es la primera."); 

        } else if (p2.getAltura() > p1.getAltura() && p2.getAltura() > p3.getAltura()) {

            System.out.println("La persona con mayor altura es la segunda."); 

        } else if (p3.getAltura() > p1.getAltura() && p3.getAltura() > p2.getAltura()) {

            System.out.println("La persona con mayor altura es la tercera.");
            
        }

        if (p1.getPeso() > p2.getPeso() && p1.getPeso() > p3.getPeso()) {

            System.out.println("La persona con mayor peso es la primera."); 

        } else if (p2.getPeso() > p1.getPeso() && p2.getPeso() > p3.getPeso()) {

            System.out.println("La persona con mayor peso es la segunda."); 

        } else if (p3.getPeso() > p1.getPeso() && p3.getPeso() > p2.getPeso()) {

            System.out.println("La persona con mayor peso es la tercera.");
            
        }

        sc.close();

    }
}
