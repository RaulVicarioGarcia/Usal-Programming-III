package controller;

import model.Suma;
import view.Vista;

public class Controller {

    private Suma suma;
    private Vista vista;

    public Controller(Suma suma, Vista vista) {
        this.suma = suma;
        this.vista = vista;
    }

    public void ejecutar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            switch (opcion) {
                case "1":
                    leerNumeros();
                    break;
                case "2":
                    calcularSuma();
                    break;
                case "3":
                    mostrarResultado();
                    break;
                case "q":
                    vista.mostrarMensaje("Saliendo del programa.");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (!opcion.equals("q"));
    }

    private void leerNumeros() {
        suma.setNumero1(vista.leerNumero("Ingrese el primer número: "));
        suma.setNumero2(vista.leerNumero("Ingrese el segundo número: "));
    }

    private void calcularSuma() {
        suma.calcularSuma();
        vista.mostrarMensaje("La suma se ha calculado correctamente.");
    }

    private void mostrarResultado() {
        vista.mostrarResultado(suma.getTotal());
    }
}
