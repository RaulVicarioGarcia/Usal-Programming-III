package model;

public class Item {

    private String nombre;
    private double precios;
    private double ventas;

    public Item (String nombre) {

        this.nombre=nombre;
        this.precios=0.0;
        this.ventas=0.0;

    }

    public String getNombre() {
        return nombre;
    }
    public double getPrecios() {
        return precios;
    }
    public double getVentas() {
        return ventas;
    }
    public void setPrecios(double precios) {
        this.precios = precios;
    }
    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    public double getIngresos() {
        return precios*ventas;
    }

    @Override
    public String toString() {
        
        return ""+nombre+" | "+precios+" | "+ventas;

    }

}