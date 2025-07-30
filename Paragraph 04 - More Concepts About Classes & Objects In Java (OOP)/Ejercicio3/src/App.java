import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el precio por kg SIN IVA de las manzanas:");
        double precioManzanasSinIva = sc.nextDouble();

        System.out.println("Introduce el precio por kg SIN IVA de las peras:");
        double precioPerasSinIva = sc.nextDouble();

        Item manzanas = new Item("Manzanas", precioManzanasSinIva, 0);
        Item peras = new Item("Peras", precioPerasSinIva, 0);

        int cliente = 1;
        boolean continuar = true;

        while (continuar) {
            System.out.println("---------------------------------------------------");
            System.out.printf("| Cliente | %d |\n", cliente);
            System.out.println("---------------------------------------------------");

            System.out.println("Introduce la cantidad de manzanas (kg) para el cliente:");
            manzanas.setCantidadItem(sc.nextDouble());

            System.out.println("Introduce la cantidad de peras (kg) para el cliente:");
            peras.setCantidadItem(sc.nextDouble());

            double totalManzanas = manzanas.calcularTotalConIva();
            double totalPeras = peras.calcularTotalConIva();
            double totalFactura = totalManzanas + totalPeras;

            System.out.printf("| %-10s | %6.2f kg | precio Kg con IVA %6.2f | %6.2f € |\n",
                    manzanas.getNombre(), manzanas.getCantidadItem(), manzanas.getPrecioKgConIva(), totalManzanas);

            System.out.printf("| %-10s | %6.2f kg | precio Kg con IVA %6.2f | %6.2f € |\n",
                    peras.getNombre(), peras.getCantidadItem(), peras.getPrecioKgConIva(), totalPeras);

            System.out.println("---------------------------------------------------");
            System.out.printf("| Total con IVA %6.2f € |\n", totalFactura);
            System.out.println("---------------------------------------------------");

            System.out.println("¿Desea continuar con otro cliente? (s/n)");
            char respuesta = sc.next().toLowerCase().charAt(0);
            if (respuesta == 'n') {
                continuar = false;
            } else {
                cliente++;
            }
        }

        System.out.println("¡Gracias por usar el programa de la frutería!");
        sc.close();
    }
}
