public class Imagen extends Archivo implements Compartible {
    
    public Imagen (String nombre, int tamanio) {

        super(nombre,tamanio);

    }

    @Override
    public void compartir(String plataforma) {
        System.out.println("La imagen " + getNombre() + " está siendo compartida en la plataforma " + plataforma + ".");
    }

}
