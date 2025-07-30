public class Usuario {
    private String nombre;
    private double peso; // Peso en kilogramos
    private double altura; // Altura en metros

    private Usuario (String nombre, double peso, double altura) {
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public double calcularIMC() {
        return peso / (altura * altura);
    }

    // Factory method

    public static Usuario crearDesdeArray (String[] datos) {

        String nombre = datos[0];
        double peso = Double.parseDouble(datos[1]);
        double altura = Double.parseDouble(datos[2]);

        return new Usuario(nombre, peso, altura);

    }

}