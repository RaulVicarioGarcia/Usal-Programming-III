package model;

public class Person {

    private String nombre;
    private float altura;
    private float peso;
    private Dni dni;

    public Person() {
        this.nombre = "Desconocido";
        this.altura = 170;
        this.peso = 70;
        this.dni = null; // Inicialmente sin DNI.
    }

    public Person(Dni dni, String nombre, float altura, float peso) {
        this.dni = dni;
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
    }

    public double calcularIMC() {
        double alturaEnMetros = altura / 100.0;
        return peso / (alturaEnMetros * alturaEnMetros);
    }

    public String getNombre() {
        return nombre;
    }

    public float getAltura() {
        return altura;
    }

    public float getPeso() {
        return peso;
    }

    public Dni getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setDni(Dni dni) {
        this.dni = dni;
    }
}
