import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;

public abstract class BuscadorPrimos {
    
    private String nombre;
    private int limite;
    private long tiempoRequerido;
    private int cantidadPrimos;

    public BuscadorPrimos(String nombre, int limite){

        this.nombre=nombre;
        this.limite=limite;

    }

    public int getCantidadPrimos() {
        return cantidadPrimos;
    }
    public int getLimite() {
        return limite;
    }
    public String getNombre() {
        return nombre;
    }
    public long getTiempoRequerido() {
        return tiempoRequerido;
    }

    public final void calcular() {

        Instant inicio = Instant.now();
        ArrayList<Integer> primos = calcularPrimos();
        Instant acabar = Instant.now();

        long tiempoRequerido = Duration.between(inicio, acabar).toMillis();
        cantidadPrimos = primos.size();

        System.out.println("El Buscador "+nombre+" ha tardado "+tiempoRequerido+" milisegundos en encontrar "+cantidadPrimos+" primos hasta el número "+limite);

    }

    protected abstract ArrayList<Integer> calcularPrimos();

}
