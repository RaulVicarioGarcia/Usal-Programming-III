package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Almacen;
import model.Item;

public class View {

    private Scanner scanner;

    public void VentasView() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\nMENU:");
        System.out.println("1.- Leer tabla de ventas");
        System.out.println("2.- Leer tabla de precios");
        System.out.println("3.- Calcular ingresos totales");
        System.out.println("4.- Mostrar resultados");
        System.out.println("q.- Salir");
    }

    public char getOpcion() {
        System.out.print("Seleccione una opción: ");
        return scanner.next().charAt(0);
    }

    public int getVenta(String nombreAlmacen, String nombreProducto) {
        System.out.printf("Ventas para %s, %s: ", nombreAlmacen, nombreProducto);
        return scanner.nextInt();
    }

    public double getPrecio(String nombreProducto) {
        System.out.printf("Precio para %s: ", nombreProducto);
        return scanner.nextDouble();
    }

    public void mostrarResultados(ArrayList<Almacen> almacenes) {
        System.out.println("\nResumen de ventas:");
        for (Almacen almacen : almacenes) {
            System.out.println("\n" + almacen.getNombre() + ":");
            for (int j = 0; j < 2; j++) {
                Item item = almacen.getItem(j);
                System.out.printf("%s: %d unidades - Ingresos: %.2f€\n", 
                    item.getNombre(), item.getVentas(), item.getIngresos());
            }
        }
    }

    public void mostrarIngresosTotales(double total) {
        System.out.printf("\nIngresos totales: %.2f€\n", total);
    }
    
}
