package model;

public class Person {

    private String nombre;
    private float altura;
    private float peso;

    public Person () {

        this.nombre = "Desconocido";
        this.altura = 170;
        this.peso = 70;

    }

    public Person (String nombre, float altura, float peso) {

        this.nombre=nombre;
        this.altura=altura;
        this.peso=peso;

    }

    public double calcularIMC (float altura, float peso) {

        double temp;

        temp = altura/100;

        return (peso/(temp*temp));


    }

    public float getAltura() {
     
        return altura;
    
    }

    public String getNombre() {
    
        return nombre;
    
    }

    public float getPeso() {
     
        return peso;
    
    }

    public void setAltura(float altura) {
     
        this.altura = altura;
    
    }

    public void setNombre(String nombre) {
     
        this.nombre = nombre;
    
    }

    public void setPeso(float peso) {
     
        this.peso = peso;
    
    }

}