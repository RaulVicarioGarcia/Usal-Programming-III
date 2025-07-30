public class App {

public static void main(String[] args) {

    Wrapped wrapper = new Wrapped();

    int entero = wrapper.leerEntero("Ingrese un entero: ");

    float flotante = wrapper.leerFlotante("Ingrese un flotante: ");

    double doble = wrapper.leerDoble("Ingrese un doble: ");

}
}