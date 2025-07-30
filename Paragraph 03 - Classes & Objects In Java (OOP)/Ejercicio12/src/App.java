public class App {
    public static void main(String[] args) {
        Rectangulo rectangulo = new Rectangulo();

        try {

            rectangulo.setLongitud(15.5f);
            rectangulo.setAnchura(10.2f);

            System.out.println("Longitud: " + rectangulo.getLongitud());
            System.out.println("Anchura: " + rectangulo.getAnchura());
            System.out.println("Área: " + rectangulo.calcularArea());
            System.out.println("Perímetro: " + rectangulo.calcularPerimetro());

            rectangulo.setLongitud(25.0f); 

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
