import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int limite = 100000; // Cambia este valor según lo necesario

        // Crear una lista de buscadores
        ArrayList<BuscadorPrimos> buscadores = new ArrayList<>();
        buscadores.add(new BuscadorNaive("Naive", limite));
        buscadores.add(new BuscadorEratostenes("Eratóstenes", limite));

        // Ejecutar cada buscador
        for (BuscadorPrimos buscador : buscadores) {
            buscador.calcular();
        }
    }
}