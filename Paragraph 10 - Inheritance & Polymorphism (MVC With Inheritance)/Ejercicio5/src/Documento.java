public class Documento extends Archivo implements Imprimible, Compartible {
    
    public Documento(String nombre, int tamanio){

        super(nombre, tamanio);

    }

    @Override
    public void imprimir(){

        System.out.println("El archivo "+getNombre()+" está siendo llevado a impresión.");

    }

    @Override
    public void compartir(String plataforma) {
        System.out.println("El documento " + getNombre() + " está siendo compartido en la plataforma " + plataforma + ".");
    }

}
