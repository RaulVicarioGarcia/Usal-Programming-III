package controller;

import model.Almacen;
import model.VentasModel;
import view.View;

public class Controller {
    
    private VentasModel model;
    private View view;

    public Controller(VentasModel model, View view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        char opcion;
        do {
            view.mostrarMenu();
            opcion = view.getOpcion();
            
            switch (opcion) {
                case '1': leerVentas(); break;
                case '2': leerPrecios(); break;
                case '3': calcularIngresos(); break;
                case '4': mostrarResultados(); break;
                case 'q': System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción no válida");
            }
        } while (opcion != 'q');
    }

    private void leerVentas() {
        for (int i = 0; i < 5; i++) {
            Almacen almacen = model.getAlmacenes().get(i);
            for (int j = 0; j < 2; j++) {
                model.setVenta(i, j, view.getVenta(almacen.getNombre(), 
                    almacen.getItem(j).getNombre()));
            }
        }
    }

    private void leerPrecios() {
        Almacen primerAlmacen = model.getAlmacenes().get(0);
        for (int i = 0; i < 2; i++) {
            model.setPrecio(i, view.getPrecio(primerAlmacen.getItem(i).getNombre()));
        }
    }

    private void calcularIngresos() {
        view.mostrarIngresosTotales(model.calcularIngresosTotales());
    }

    private void mostrarResultados() {
        view.mostrarResultados(model.getAlmacenes());
        view.mostrarIngresosTotales(model.calcularIngresosTotales());
    }

}
