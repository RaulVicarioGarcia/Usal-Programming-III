package model;

import java.util.ArrayList;

public class Almacen {
 
    private ArrayList<Item> items;
    private String nombre;

    public Almacen(String nombre){

        this.nombre = nombre;
        this.items = new ArrayList<>();
        items.add(new Item("Pera"));
        items.add(new Item("Manzana"));

    }

    public double getIngresosTotales() {

        double total = 0;

        for (Item item : items) {

            total += item.getIngresos();

        }

        return total;

    }

    public Item getItem(int index) { 
        return items.get(index); }
    
    public String getNombre() { 
        return nombre; }

}
