public class Person {

    private String nombre;
    private float altura;
    private float peso;

    public Person (String nombre, float altura, float peso) {

        this.nombre=nombre;
        this.altura=altura;
        this.peso=peso;

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