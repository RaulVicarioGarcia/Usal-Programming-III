package model;

import java.util.ArrayList;

public class VentasModel {
    
    private ArrayList<Almacen> almacenes;

    public VentasModel() {
        almacenes = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            almacenes.add(new Almacen("Almacén " + i));
        }
    }

    public void setVenta(int almacenIndex, int itemIndex, int cantidad) {
        almacenes.get(almacenIndex).getItem(itemIndex).setVentas(cantidad);
    }

    public void setPrecio(int itemIndex, double precio) {
        for (Almacen almacen : almacenes) {
            almacen.getItem(itemIndex).setPrecios(precio);
        }
    }

    public double calcularIngresosTotales() {
        double total = 0;
        for (Almacen almacen : almacenes) {
            total += almacen.getIngresosTotales();
        }
        return total;
    }

    public ArrayList<Almacen> getAlmacenes() { return almacenes; }

}
