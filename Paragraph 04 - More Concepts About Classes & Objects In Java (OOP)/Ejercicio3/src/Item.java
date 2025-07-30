public class Item {
    
    private double precioKgSinIva;
    private static final double IVA = 0.21;
    private double cantidadItem;
    private String nombre;

    public Item (String nombre, double precioKgSinIva, double cantidadItem) {

        this.nombre = nombre;
        this.precioKgSinIva = precioKgSinIva;
        this.cantidadItem = cantidadItem;

    }

    public double getCantidadItem() {
        return cantidadItem;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecioKgSinIva() {
        return precioKgSinIva;
    }
    public double getPrecioKgConIva() {
        return precioKgSinIva * (1 + IVA);
    }
    public double calcularTotalConIva() {
        return cantidadItem * getPrecioKgConIva();
    }
    public void setCantidadItem(double cantidadItem) {
        this.cantidadItem = cantidadItem;
    }

    //Factory Method

    public static Item crearDesdeArray (String[] items) {

        String nombre = items[0];
        double precioSinIva = Double.parseDouble(items[1]);
        double cantidad = Double.parseDouble(items[2]);

        return new Item(nombre, precioSinIva, cantidad);

    }

}
