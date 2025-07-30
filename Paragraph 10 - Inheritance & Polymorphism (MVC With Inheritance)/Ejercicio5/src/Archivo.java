public abstract class Archivo {
    
    private String nombre;
    private int tamanio;

    public Archivo(String nombre, int tamanio) {

        this.nombre=nombre;
        this.tamanio=tamanio;

    }

    public String getNombre() {
        return nombre;
    }
    public int getTamanio() {
        return tamanio;
    }

}
