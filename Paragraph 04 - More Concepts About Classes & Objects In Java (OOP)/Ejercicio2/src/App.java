public class App {
    public static void main(String[] args) throws Exception {

        if (args.length != 3) {

            System.err.println("Debes introducir 3 parametros.");

        }

        Usuario usuario = Usuario.crearDesdeArray(args);

        System.out.println("+----------------+-------+---------+---------+");
        System.out.println("| Nombre         | Peso  | Altura  | IMC     |");
        System.out.println("+----------------+-------+---------+---------+");
        System.out.printf("| %-14s | %-5.2f | %-7.2f | %-7.2f |\n", 
        usuario.getNombre(), usuario.getPeso(), usuario.getAltura(), usuario.calcularIMC());
        System.out.println("+----------------+-------+---------+---------+");

    }
}
