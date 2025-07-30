import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Archivo> archivos = new ArrayList<>();

        archivos.add(new Documento("Documento1", 120));
        archivos.add(new Imagen("Imagen1", 300));
        archivos.add(new Apk("Aplicacion1", 5000));

    for (Archivo archivo : archivos) {
        if (archivo instanceof Imprimible) {
            ((Imprimible) archivo).imprimir();
        }

        if (archivo instanceof Compartible) {
            ((Compartible) archivo).compartir("WhatsApp");
        }

        if (!(archivo instanceof Imprimible) && !(archivo instanceof Compartible)) {
            System.out.println("El archivo \"" + archivo.getNombre() + "\" no es imprimible ni compartible.");
        }
    } }

}
