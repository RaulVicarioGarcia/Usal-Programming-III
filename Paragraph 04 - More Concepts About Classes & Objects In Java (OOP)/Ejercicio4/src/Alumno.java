public class Alumno {
    
    private String nombre;
    private double parcial1;
    private double parcial2;
    private double finalExamen;

    public Alumno(String nombre, double parcial1, double parcial2, double finalExamen) {
        
        this.nombre = nombre;
        this.parcial1 = parcial1;
        this.parcial2 = parcial2;
        this.finalExamen = finalExamen;

    }
    public String getNombre() {
        return nombre;
    }
    public double getFinalExamen() {
        return finalExamen;
    }
    public double getParcial1() {
        return parcial1;
    }
    public double getParcial2() {
        return parcial2;
    }

    public double calcularNotaFinal() {
        return (parcial1 * 0.10) + (parcial2 * 0.10) + (finalExamen * 0.80);
    }

    public static Alumno crearDesdeArray(String[] data) {

        String nombre = data[0];
        double parcial1 = Double.parseDouble(data[1]);
        double parcial2 = Double.parseDouble(data[2]);
        double finalExamen = Double.parseDouble(data[3]);

        return new Alumno(nombre, parcial1, parcial2, finalExamen);
    }

}
