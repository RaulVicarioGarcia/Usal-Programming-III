import java.util.ArrayList;

public class BuscadorEratostenes extends BuscadorPrimos {

    public BuscadorEratostenes(String nombre, int limite) {
        super(nombre, limite);
    }

    @Override
    protected ArrayList<Integer> calcularPrimos() {
        int limite = getLimite();
        boolean[] esCompuesto = new boolean[limite + 1];
        ArrayList<Integer> primos = new ArrayList<>();

        for (int i = 2; i <= limite; i++) {
            if (!esCompuesto[i]) {
                primos.add(i);
                for (int j = i * 2; j <= limite; j += i) {
                    esCompuesto[j] = true;
                }
            }
        }
        return primos;
    }
    
}
